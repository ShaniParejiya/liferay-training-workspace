package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;


import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.aixtor.training.liferay.employee.util.ExportToExcelEmployeeDetails;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
		"mvc.command.name=/exportTOExcel"
		},
service = MVCResourceCommand.class)
public class ExportToExcelMVCServeResourcesCommmand implements MVCResourceCommand {

	@Reference
	EmployeeLocalService employeeLocalService;
	
	private static final Log log = LogFactoryUtil.getLog(ExportToExcelMVCServeResourcesCommmand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		ExportToExcelEmployeeDetails exportToExcelEmployeeData=new ExportToExcelEmployeeDetails();
		
		List<EmployeeAllDetailsModel> employeeList;
		
		String searchKeyword = ParamUtil.getString(resourceRequest,AixtorEmployeeWebConstant.SEARCH_INPUT);
		
		log.info("searchKeyword ====== :::::" + searchKeyword);
		
		resourceRequest.setAttribute(AixtorEmployeeWebConstant.SEARCH_INPUT, searchKeyword);
		
		if (!searchKeyword.trim().isEmpty()) {
			
			employeeList = employeeLocalService.getEmployeeAllDetails(searchKeyword);

			if (employeeList == null || employeeList.isEmpty()) {
				resourceRequest.setAttribute(AixtorEmployeeWebConstant.NO_DATA_FOUND,
						AixtorEmployeeWebConstant.NO_EMPLOYEE_DATA_FOUND);
			} else {
				try {
					HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
					exportToExcelEmployeeData.exportToExcel(employeeList, resourceResponse, resourceRequest, httpServletResponse);
					resourceRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);
				} catch (java.io.IOException e) {
					log.info("Error in Catch == > " + e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			try {
				employeeList = employeeLocalService.getEmployeeDetails();	
				HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
				exportToExcelEmployeeData.exportToExcel(employeeList, resourceResponse, resourceRequest, httpServletResponse);
				resourceRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);
			} catch (java.io.IOException e) {
				log.info("Error in Catch == > " + e.getMessage());
				e.printStackTrace();
			}	
		}
		return false;
	}
}
