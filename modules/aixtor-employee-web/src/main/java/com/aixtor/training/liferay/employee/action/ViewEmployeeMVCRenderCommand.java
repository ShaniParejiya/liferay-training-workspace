package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;


import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;



@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
	       "mvc.command.name=/"
	    },
	    service = MVCRenderCommand.class
	)
public class ViewEmployeeMVCRenderCommand implements MVCRenderCommand{

	@Reference
	EmployeeLocalService employeeLocalService;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException {
		renderRequest.setAttribute(AixtorEmployeeWebConstant.TOTAL_EMPLOYEES, employeeLocalService.getEmployeesCount());
		renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_ALL_DETAILS_LIST, employeeLocalService.getEmployees(-1, -1));
		renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeLocalService.getEmployeeDetails());
		return AixtorEmployeeWebConstant.VIEW_JSP_PAGE;
	}
}
