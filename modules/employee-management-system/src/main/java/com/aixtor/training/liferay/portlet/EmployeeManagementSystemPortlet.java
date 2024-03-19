package com.aixtor.training.liferay.portlet;

import com.aixtor.training.liferay.constants.EmployeeManagementSystemConstants;

import com.aixtor.training.liferay.constants.EmployeeManagementSystemPortletKeys;
import com.aixtor.training.liferay.model.Employee;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author shani.patel
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeManagementSystem",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/employee-registration.jsp",
		"javax.portlet.name=" + EmployeeManagementSystemPortletKeys.EMPLOYEEMANAGEMENTSYSTEM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
service = Portlet.class)
public class EmployeeManagementSystemPortlet extends MVCPortlet {

	private List<Employee> empDataList = new ArrayList<>();
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String pathValue = ParamUtil.getString(renderRequest,EmployeeManagementSystemConstants.MVC_PATH,"/");
		
		if(pathValue.equals(EmployeeManagementSystemConstants.REGISTRATION)) {
			renderRequest.setAttribute(EmployeeManagementSystemConstants.COUNTRY_LIST, CountryStateList.getCountries());
			include(EmployeeManagementSystemConstants.REGISTRATION_PAGE, renderRequest, renderResponse);
		}
		else {
			renderRequest.setAttribute(EmployeeManagementSystemConstants.EMPLOYEE_LIST,empDataList);
			include(EmployeeManagementSystemConstants.VIEW_PAGE, renderRequest, renderResponse);
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		String countryName = ParamUtil.getString(resourceRequest, EmployeeManagementSystemConstants.COUNTRY_DROPDOWN);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for (String item : CountryStateList.getStates(countryName)) {
			jsonArray.put(item);
		}
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put(EmployeeManagementSystemConstants.ITEMS, jsonArray);
		resourceResponse.setContentType("application/json");
		PrintWriter writer = resourceResponse.getWriter();
		writer.print(jsonObject.toString());
		writer.flush();
		writer.close();

		super.serveResource(resourceRequest, resourceResponse);
	}
	

	public void submitForm(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		String Name = ParamUtil.getString(actionRequest, EmployeeManagementSystemConstants.NAME);
		String Email = ParamUtil.getString(actionRequest, EmployeeManagementSystemConstants.EMAIL);
		String Country = ParamUtil.getString(actionRequest,EmployeeManagementSystemConstants.COUNTRY);
		String State = ParamUtil.getString(actionRequest, EmployeeManagementSystemConstants.STATE);
		
		Employee emp= new Employee(Name, Email, Country, State);
		
		empDataList.add(emp);
		
		actionResponse.sendRedirect(EmployeeManagementSystemConstants.REGISTRATION_FORM_REDIRECT_LINK);
	}

}