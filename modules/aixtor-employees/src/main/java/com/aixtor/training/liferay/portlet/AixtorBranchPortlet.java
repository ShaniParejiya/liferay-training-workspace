package com.aixtor.training.liferay.portlet;

import com.aixtor.training.liferay.constants.AixtorBranchConstant;
import com.aixtor.training.liferay.employee.service.builder.model.Branch;
import com.aixtor.training.liferay.employee.service.builder.model.City;
import com.aixtor.training.liferay.employee.service.builder.model.State;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.CityLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.StateLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CountryLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AixtorBranch", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/branch/branch.jsp",
		"javax.portlet.name=" + AixtorBranchConstant.AIXTOR_BRANCH, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },

		service = Portlet.class)
public class AixtorBranchPortlet extends MVCPortlet {

	@Reference
	BranchLocalService branchLocalService;

	@Reference
	StateLocalService stateLocalService;

	@Reference
	CityLocalService cityLocalService;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<Country> countries = CountryLocalServiceUtil.getCountries(-1, -1);
		renderRequest.setAttribute(AixtorBranchConstant.COUNTRY_LIST, countries);
		super.render(renderRequest, renderResponse);
	}
	
	public List<State> getStateListByCountryId(long countryId) {
		List<State> states = stateLocalService.findBycountryId(countryId);
		return states;
	}
	
	public List<City> getCityListByStateId(long stateId){
		List<City> cities = cityLocalService.findBystateId(stateId);
		return cities;
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		String commandValue = ParamUtil.getString(resourceRequest, AixtorBranchConstant.COMMAND);
		if(commandValue.equals(AixtorBranchConstant.SEARCH_STATE_BY_COUNTRY)) {

			long countryId = ParamUtil.getLong(resourceRequest, AixtorBranchConstant.COUNTRY_ID);
			List<State> stateList = getStateListByCountryId(countryId);
			
			JSONObject jsonobj =  JSONFactoryUtil.createJSONObject();
			PrintWriter printout=resourceResponse.getWriter();
			
		    jsonobj.put("data", stateList);
		    jsonobj.put("status", "success");
		    printout.write(jsonobj.toJSONString());
		    printout.close();
		}
		else if(commandValue.equals(AixtorBranchConstant.SEARCH_CITY_BY_STATE)) {
			
			long stateId = ParamUtil.getLong(resourceRequest, AixtorBranchConstant.STATE_ID);
			List<City> cityList = getCityListByStateId(stateId);
			JSONObject jsonobj =  JSONFactoryUtil.createJSONObject();
			PrintWriter printout=resourceResponse.getWriter();
			
		    jsonobj.put("data", cityList);
		    jsonobj.put("status", "success");
		    printout.write(jsonobj.toJSONString());
		    printout.close();
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	@ProcessAction(name = "branchDetails")
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		String branchName = ParamUtil.getString(actionRequest,AixtorBranchConstant.BRANCH_NAME);
		Long countryId=ParamUtil.getLong(actionRequest, AixtorBranchConstant.COUNTRY_NAME);
		Long stateId = ParamUtil.getLong(actionRequest, AixtorBranchConstant.STATE_NAME);
		Long cityId = ParamUtil.getLong(actionRequest, AixtorBranchConstant.CITY_NAME);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Branch branch = branchLocalService.createBranch(CounterLocalServiceUtil.increment(Branch.class.getName()));
		
		branch.setCompanyId(themeDisplay.getCompanyId());
		branch.setUserName(themeDisplay.getUser().getFullName());
		branch.setCreateDate(new Date());
		branch.setModifiedDate(new Date());
		branch.setUserId(themeDisplay.getUserId());
		branch.setBranchName(branchName);
		branch.setCountryId(countryId);
		branch.setStateId(stateId);
		branch.setCityId(cityId);
		branchLocalService.addBranch(branch);
	}
}
