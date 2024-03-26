package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;


import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.adaptive.media.exception.AMRuntimeException.IOException;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
