package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
	       "mvc.command.name=/getSearchInputForm"
	    },
	    service = MVCRenderCommand.class
	)
public class SearchInputFormMVCRenderCommand implements MVCRenderCommand {

	@Reference
	EmployeeLocalService employeeLocalService;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String searchKeyword = ParamUtil.getString(renderRequest, AixtorEmployeeWebConstant.SEARCH_INPUT);
//		String searchKeyword = ParamUtil.getString(renderRequest, AixtorEmployeeWebConstant.SEARCH_INPUT).replaceAll(" ", "");

		List<EmployeeAllDetailsModel> employeeList;
		
		renderRequest.setAttribute(AixtorEmployeeWebConstant.SEARCH_INPUT, searchKeyword);
		if (!searchKeyword.trim().isEmpty()) {
			employeeList = employeeLocalService.getEmployeeAllDetails(searchKeyword);
			
			if (employeeList == null || employeeList.isEmpty()) {
			    renderRequest.setAttribute(AixtorEmployeeWebConstant.NO_DATA_FOUND, AixtorEmployeeWebConstant.NO_EMPLOYEE_DATA_FOUND);
			} else {
			    renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);
			}
		} else {
			employeeList = employeeLocalService.getEmployeeDetails();
			renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);
		}
		return AixtorEmployeeWebConstant.VIEW_JSP_PAGE;
	}
}
