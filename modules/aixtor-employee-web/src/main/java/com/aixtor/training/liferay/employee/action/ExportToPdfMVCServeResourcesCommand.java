package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.aixtor.training.liferay.employee.util.ExportToPdfEmployeeDetails;

import com.itextpdf.text.pdf.PdfWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
		"mvc.command.name=/exportTOPdf"
		},
service = MVCResourceCommand.class)
public class ExportToPdfMVCServeResourcesCommand implements MVCResourceCommand{

	@Reference
	EmployeeLocalService employeeLocalService;
	
	private static final Log log = LogFactoryUtil.getLog(ExportToPdfMVCServeResourcesCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		ExportToPdfEmployeeDetails exportToPdfEmployeeDetails=new ExportToPdfEmployeeDetails();
		
		log.info("ServeResources Command Called :::::::::::::::::::::::::::::::::::::::");
		
		List<EmployeeAllDetailsModel> employeeList;
		
		String searchKeyword = ParamUtil.getString(resourceRequest,AixtorEmployeeWebConstant.SEARCH_INPUT);
		
		log.info("searchKeyword ====== :::::" + searchKeyword);
		
		resourceRequest.setAttribute(AixtorEmployeeWebConstant.SEARCH_INPUT, searchKeyword);
		PdfWriter pdfWriter = null;
		if (!searchKeyword.trim().isEmpty()) {
			employeeList = employeeLocalService.getEmployeeAllDetails(searchKeyword);
			if (employeeList == null || employeeList.isEmpty()) {
				resourceRequest.setAttribute(AixtorEmployeeWebConstant.NO_DATA_FOUND,AixtorEmployeeWebConstant.NO_EMPLOYEE_DATA_FOUND);
			} else {
				HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
				try {
					exportToPdfEmployeeDetails.exportToPdf(employeeList, resourceResponse, resourceRequest, httpServletResponse, pdfWriter);
				} catch (IOException e) {
					log.error("error in catch ===> "+ e.getMessage());
				}
				resourceRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);
			}
		} else {
			employeeList = employeeLocalService.getEmployeeDetails();	
			HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
			try {
				exportToPdfEmployeeDetails.exportToPdf(employeeList, resourceResponse, resourceRequest, httpServletResponse, pdfWriter);
			} catch (IOException e) {
				log.error("error in else part ==> " + e.getMessage());
//				e.printStackTrace();
			}
			resourceRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeList);	
		}
		return false;
	}
	

	
    
}
