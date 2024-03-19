package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.portlet.AixtorEmployeeWebPortlet;
import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
	       "mvc.command.name=/ShowEmployeeDataById"
	    },
	    service = MVCRenderCommand.class
	)
public class ShowEditEmployeeFormMVCRenderCommand implements MVCRenderCommand {

	@Reference
	EmployeeLocalService employeeLocalService;
	
	@Reference
	DepartmentLocalService departmentLocalService;

	@Reference
	DesignationLocalService designationLocalService;

	@Reference
	BranchLocalService branchLocalService;
	
	private static final Log log = LogFactoryUtil.getLog(AixtorEmployeeWebPortlet.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		Long employeeId=ParamUtil.getLong(renderRequest, "employeeId");
		try {
		  renderRequest.setAttribute(AixtorEmployeeWebConstant.DEPARTMENT_LIST, departmentLocalService.getDepartments(-1, -1));
		  renderRequest.setAttribute(AixtorEmployeeWebConstant.BRANCH_LIST, branchLocalService.getBranches(-1, -1));
		  renderRequest.setAttribute(AixtorEmployeeWebConstant.DESIGNATION_LIST, designationLocalService.getDesignations(-1, -1));
		  renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST_BY_ID,employeeLocalService.getEmployee(employeeId) );
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return AixtorEmployeeWebConstant.EMPLOYEE_JSP_PAGE;
	}
}
