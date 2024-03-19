package com.aixtor.training.liferay.employee.action;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		        "mvc.command.name=/showDateFormatForm"
				},
		service = MVCRenderCommand.class)
public class DateFormatFormMVCRenderCommand implements MVCRenderCommand {

	@Reference
	DepartmentLocalService departmentLocalService;

	@Reference
	DesignationLocalService designationLocalService;

	@Reference
	BranchLocalService branchLocalService;

	@Reference
	EmployeeLocalService employeeLocalService;

	private static final Log log = LogFactoryUtil.getLog(DateFormatFormMVCRenderCommand.class);

	List<EmployeeAllDetailsModel> employeeListofjoin = null;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		String fromdatestr = ParamUtil.getString(renderRequest, AixtorEmployeeWebConstant.FROM_DATE);
		String todatestr = ParamUtil.getString(renderRequest, AixtorEmployeeWebConstant.T0_DATE);
		renderRequest.setAttribute(AixtorEmployeeWebConstant.FROM_DATE, fromdatestr);
		renderRequest.setAttribute(AixtorEmployeeWebConstant.T0_DATE, todatestr);
		if (!fromdatestr.isEmpty() && !todatestr.isEmpty()) {
			Date fromdate = null;
			Date todate = null;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(AixtorEmployeeWebConstant.SIMPLE_DATE_FORMAT);
				fromdate = dateFormat.parse(fromdatestr + AixtorEmployeeWebConstant.FROM_DATE_PARSE_STRING);
				todate = dateFormat.parse(todatestr + AixtorEmployeeWebConstant.TO_DATE_PARSE_STRING);
			} catch (ParseException e) {
				log.info("Error in catch " + e.getMessage());
				e.printStackTrace();
			}

			DynamicQuery dynamicQuery = employeeLocalService.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.between(AixtorEmployeeWebConstant.CREATE_DATE, fromdate, todate));

			List<Employee> listofemployee = employeeLocalService.dynamicQuery(dynamicQuery);

			employeeListofjoin = new ArrayList<EmployeeAllDetailsModel>();
			try {
				for (Employee employee : listofemployee) {
					EmployeeAllDetailsModel employeeAllDetailsModel = new EmployeeAllDetailsModel();
					employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
					employeeAllDetailsModel.setFirstName(employee.getFirstName());
					employeeAllDetailsModel.setLastName(employee.getLastName());
					employeeAllDetailsModel.setEmail(employee.getEmail());
					employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
					employeeAllDetailsModel.setDepartmentName(departmentLocalService
							.findByGetDepartmentNameById(employee.getDepartmentId()).getDepartmentName());
					employeeAllDetailsModel.setBranchName(
							branchLocalService.findByGetbranchNameById(employee.getBranchId()).getBranchName());
					employeeAllDetailsModel.setDesignationName(designationLocalService
							.findByGetdesignationNameById(employee.getDesignationId()).getDesignationName());
					employeeAllDetailsModel.setAddress(employee.getAddress());

					employeeListofjoin.add(employeeAllDetailsModel);
				}
				if (employeeListofjoin == null || employeeListofjoin.isEmpty()) {
				    renderRequest.setAttribute(AixtorEmployeeWebConstant.NO_DATA_FOUND, AixtorEmployeeWebConstant.NO_RECORD_BETWEEN_THIS_DATE);
				}else {
					renderRequest.setAttribute(AixtorEmployeeWebConstant.EMPLOYEE_LIST, employeeListofjoin);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return AixtorEmployeeWebConstant.VIEW_JSP_PAGE;
	}
}
