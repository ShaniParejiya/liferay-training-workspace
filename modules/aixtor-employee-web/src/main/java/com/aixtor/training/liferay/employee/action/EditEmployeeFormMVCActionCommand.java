package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + AixtorEmployeeWebPortletKeys.AIXTOREMPLOYEEWEB,
	       "mvc.command.name=/updateEmployeeRecords"
	    },
	    service = MVCActionCommand.class
	)
public class EditEmployeeFormMVCActionCommand extends BaseMVCActionCommand{

	@Reference
	EmployeeLocalService employeeLocalService;
	
	private static final Log log = LogFactoryUtil.getLog(EditEmployeeFormMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

//		log.info("update Action Called");
		Long employeeId=ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.EMPLOYEE_ID);
		String firstName = ParamUtil.getString(actionRequest, AixtorEmployeeWebConstant.FIRST_NAME);
		String lastName = ParamUtil.getString(actionRequest, AixtorEmployeeWebConstant.LAST_NAME);
		String email = ParamUtil.getString(actionRequest, AixtorEmployeeWebConstant.EMAIL);
		Long mobileNumber = ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.MOBILE_NUMBER);
		Long departmentHead = ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.DEPARTMENT_HEAD);
		Long branch = ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.BRANCH);

//		System.out.println("update all details below");
//		System.out.println(employeeId + "" + firstName + " " + lastName + " " + email + " " + mobileNumber + " " + departmentHead + " " + branch);
		Long designationName = ParamUtil.getLong(actionRequest, AixtorEmployeeWebConstant.DESIGNATION_NAME);
		String address = ParamUtil.getString(actionRequest, AixtorEmployeeWebConstant.ADDRESS);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Employee employee;
		try {
			employee = employeeLocalService.getEmployee(employeeId);
			employee.setCompanyId(themeDisplay.getCompanyId());
			employee.setUserName(themeDisplay.getUser().getFullName());
			employee.setCreateDate(new Date());
			employee.setModifiedDate(new Date());
			employee.setUserId(themeDisplay.getUserId());

			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
			employee.setMobileNumber(mobileNumber);
			employee.setDepartmentId(departmentHead);
			employee.setDesignationId(designationName);
			employee.setBranchId(branch);
			employee.setAddress(address);
			employeeLocalService.updateEmployee(employee);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
}
