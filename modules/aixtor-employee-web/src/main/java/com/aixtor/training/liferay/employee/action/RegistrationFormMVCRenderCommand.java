package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.Branch;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.model.Designation;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

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
	       "mvc.command.name=/register"
	    },
	    service = MVCRenderCommand.class
	)
public class RegistrationFormMVCRenderCommand implements MVCRenderCommand{
	@Reference
	DesignationLocalService designationLocalService;

	@Reference
	BranchLocalService branchLocalService;
	
	@Reference
	DepartmentLocalService departmentLocalService;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		List<Department> departmentsList = departmentLocalService.getDepartments(-1, -1);
		List<Designation> designationList = designationLocalService.getDesignations(-1, -1);
		List<Branch> branchList = branchLocalService.getBranches(-1, -1);
		
		renderRequest.setAttribute(AixtorEmployeeWebConstant.DESIGNATION_LIST, designationList);
		renderRequest.setAttribute(AixtorEmployeeWebConstant.DEPARTMENT_LIST, departmentsList);
		renderRequest.setAttribute(AixtorEmployeeWebConstant.BRANCH_LIST, branchList);
		return AixtorEmployeeWebConstant.EMPLOYEE_JSP_PAGE;
	}

}
