package com.aixtor.training.liferay.portlet;

import com.aixtor.training.liferay.constants.CityTrackerPortletKeys;
import com.aixtor.training.liferay.employees.Employee;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CityTracker",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/view.jsp",
		"javax.portlet.name=" + CityTrackerPortletKeys.CITYTRACKER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },

		service = Portlet.class)
public class CityTrackerPortlet extends MVCPortlet {
	
	
	 private List<Employee> empDataList = new ArrayList<>();
	 
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		

		String pathValue = ParamUtil.getString(renderRequest,"mvcPath","/");
		if(pathValue.equals("/html/registration.jsp")) {
			renderRequest.setAttribute("country", CountryStateList.getCountries());
		}
		else {
			renderRequest.setAttribute("empDataList",empDataList);
		}
		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		System.out.println("servresources called ::: ");
		String countryName = ParamUtil.getString(resourceRequest, "countryDropdown");
//	     resourceRequest.setAttribute("state", CountryStateList.getStates(countryName));
	     
		System.out.println(CountryStateList.getStates(countryName));
		
		 JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
	        for (String item : CountryStateList.getStates(countryName)) {
	            jsonArray.put(item);
	        }
		 JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	        jsonObject.put("items", jsonArray);

	        resourceResponse.setContentType("application/json");
	        PrintWriter writer = resourceResponse.getWriter();
	        writer.print(jsonObject.toString());
	        writer.flush();
	        writer.close();
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	@ProcessAction(name = "submitForm")
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		System.out.println("Action Called::::");
		
		String Name = ParamUtil.getString(actionRequest, "name");
		String Email = ParamUtil.getString(actionRequest, "email");
		String Country = ParamUtil.getString(actionRequest, "country");
		String State = ParamUtil.getString(actionRequest, "state");
		
		Employee emp= new Employee(Name, Email, Country, State);
		System.out.println("emp is new = " +emp.toString());
		empDataList.add(emp);
		
		actionResponse.sendRedirect("");
	}
}