package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.audit.api.AixtorAuditApi;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.portlet.AixtorEmployeeWebPortlet;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.persistence.EmployeePersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
	       "mvc.command.name=/deleteRecordById"
	    },
	    service = MVCActionCommand.class
	)
public class DeleteEmployeeFormMVCActionCommand extends BaseMVCActionCommand {
	
	@Reference
	EmployeeLocalService employeeLocalService; 
	
	@Reference
	AixtorAuditApi aixtorAuditApi;
	
	private static final Log log = LogFactoryUtil.getLog(DeleteEmployeeFormMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		log.info("Delete  Action Called");
		Long employeeId = ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.EMPLOYEE_ID);
		try {
			
			employeeLocalService.deleteEmployee(employeeId);
		} catch (PortalException e) {
			log.info("Error in Delete Employee Process.!!!!!!!!!!!!!!!!\n\n\n");
			e.printStackTrace();
		}
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		aixtorAuditApi.addAuditLog("Delete", themeDisplay,AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB);
		actionRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeLocalService.getEmployeeDetails());	
	}	
}
