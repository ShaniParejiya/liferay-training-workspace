package com.aixtor.training.liferay.portlet;


import com.aixtor.training.liferay.constants.AixtorEmployeeConstant;




import com.aixtor.training.liferay.constants.AixtorEmployeesPortletKeys;
import com.aixtor.training.liferay.employee.service.builder.exception.NoSuchDepartmentException;
import com.aixtor.training.liferay.employee.service.builder.model.Branch;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.model.Designation;
import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.persistence.EmployeePersistence;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author shani.patel
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=category.news",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeDetails",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/employee/view.jsp",
		"javax.portlet.name=" + AixtorEmployeesPortletKeys.AIXTOR_EMPLOYEES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
service = Portlet.class)
public class AixtorEmployeesPortlet extends MVCPortlet {

	@Reference
	DepartmentLocalService departmentLocalService;

	@Reference
	DesignationLocalService designationLocalService;

	@Reference
	BranchLocalService branchLocalService;

	@Reference
	EmployeeLocalService employeeLocalService;

	@Reference
	private EmployeePersistence employeePersistence;
	
//	@Reference
//	AuditTransactionApi auditTransactionApi;
	
	private static final Log log = LogFactoryUtil.getLog(AixtorLoginPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
			System.out.println("wdededdede===========================");
		String paramValue = ParamUtil.getString(renderRequest, AixtorEmployeeConstant.RENDER_PAGE);

		log.info("ParamValue " + paramValue);
		List<Department> departmentsList = departmentLocalService.getDepartments(-1, -1);
		List<Designation> designationList = designationLocalService.getDesignations(-1, -1);
		List<Branch> branchList = branchLocalService.getBranches(-1, -1);

		renderRequest.setAttribute(AixtorEmployeeConstant.DESIGNATION_LIST, designationList);
		renderRequest.setAttribute(AixtorEmployeeConstant.DEPARTMENT_LIST, departmentsList);
		renderRequest.setAttribute(AixtorEmployeeConstant.BRANCH_LIST, branchList);

		List<EmployeeAllDetailsModel> employeeList;

		if (paramValue.equals(AixtorEmployeeConstant.SHOW_REGISTRATION_FORM)) {
			include(AixtorEmployeeConstant.EMPLOYEE_JSP_PAGE, renderRequest, renderResponse);
		} else if (paramValue.equals(AixtorEmployeeConstant.SHOW_DATE_FORMAT_FORM)) {
			String fromdatestr = ParamUtil.getString(renderRequest, AixtorEmployeeConstant.FROM_DATE);
			String todatestr = ParamUtil.getString(renderRequest, AixtorEmployeeConstant.T0_DATE);
			renderRequest.setAttribute(AixtorEmployeeConstant.FROM_DATE, fromdatestr);
			renderRequest.setAttribute(AixtorEmployeeConstant.T0_DATE, todatestr);
			if (!fromdatestr.isEmpty() && !todatestr.isEmpty()) {
				Date fromdate = null;
				Date todate = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat(AixtorEmployeeConstant.SIMPLE_DATE_FORMAT);
					fromdate = dateFormat.parse(fromdatestr + AixtorEmployeeConstant.FROM_DATE_PARSE_STRING);
					todate = dateFormat.parse(todatestr + AixtorEmployeeConstant.TO_DATE_PARSE_STRING);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				DynamicQuery dynamicQuery = employeeLocalService.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.between(AixtorEmployeeConstant.CREATE_DATE, fromdate, todate));

				List<Employee> listofemployee = employeeLocalService.dynamicQuery(dynamicQuery);

				List<EmployeeAllDetailsModel> employeeListofjoin = new ArrayList<EmployeeAllDetailsModel>();

				for (Employee employee : listofemployee) {
					EmployeeAllDetailsModel employeeAllDetailsModel = new EmployeeAllDetailsModel();
					employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
					employeeAllDetailsModel.setFirstName(employee.getFirstName());
					employeeAllDetailsModel.setLastName(employee.getLastName());
					employeeAllDetailsModel.setEmail(employee.getEmail());
					employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
					employeeAllDetailsModel.setDepartmentName(departmentLocalService.findByGetDepartmentNameById(employee.getDepartmentId()).getDepartmentName());
					employeeAllDetailsModel.setBranchName(branchLocalService.findByGetbranchNameById(employee.getBranchId()).getBranchName());
					employeeAllDetailsModel.setDesignationName(designationLocalService.findByGetdesignationNameById(employee.getDesignationId()).getDesignationName());
					employeeAllDetailsModel.setAddress(employee.getAddress());

					employeeListofjoin.add(employeeAllDetailsModel);
				}
				
				System.out.println("employeeListofjoin" + employeeListofjoin);
				renderRequest.setAttribute(AixtorEmployeeConstant.EMPLOYEE_LIST, employeeListofjoin);
				super.render(renderRequest, renderResponse);
			}
		} else if (paramValue.equals(AixtorEmployeeConstant.GET_SEARCH_INPUT_FORM)) {
			String searchKeyword = ParamUtil.getString(renderRequest, AixtorEmployeeConstant.SEARCH_INPUT);
			renderRequest.setAttribute(AixtorEmployeeConstant.SEARCH_INPUT, searchKeyword);
			if (!searchKeyword.isEmpty()) {
				employeeList = employeeLocalService.getEmployeeAllDetails(searchKeyword);
				renderRequest.setAttribute(AixtorEmployeeConstant.EMPLOYEE_LIST, employeeList);
			} else {
				employeeList = employeeLocalService.getEmployeeDetails();
				renderRequest.setAttribute(AixtorEmployeeConstant.EMPLOYEE_LIST, employeeList);
			}
			super.render(renderRequest, renderResponse);
		}else if (paramValue.equals("ShowEmployeeDataById")) {
			log.info("ShowEmployeeDataById");
			
			Long employeeId=ParamUtil.getLong(renderRequest, "employeeId");
			log.info(employeeId);
			
			try {
			  Employee employeeListById=employeeLocalService.getEmployee(employeeId);
			  
			  EmployeeAllDetailsModel employeeAllDetailsModel=new EmployeeAllDetailsModel();
			  
			  employeeAllDetailsModel.setEmployeeId(String.valueOf(employeeListById.getEmployeeId()));
			  employeeAllDetailsModel.setFirstName(employeeListById.getFirstName());
			  employeeAllDetailsModel.setLastName(employeeListById.getLastName());
			  employeeAllDetailsModel.setEmail(employeeListById.getEmail());
			  employeeAllDetailsModel.setMobileNumber(String.valueOf(employeeListById.getMobileNumber()));
			  employeeAllDetailsModel.setDepartmentName(departmentLocalService.findByGetDepartmentNameById(employeeListById.getDepartmentId()).getDepartmentName());
			  employeeAllDetailsModel.setBranchName(branchLocalService.findByGetbranchNameById(employeeListById.getBranchId()).getBranchName());
			  employeeAllDetailsModel.setDesignationName(designationLocalService.findByGetdesignationNameById(employeeListById.getDesignationId()).getDesignationName());
			  employeeAllDetailsModel.setAddress(employeeListById.getAddress());
			  renderRequest.setAttribute("departmentId",employeeListById.getDepartmentId());
			  renderRequest.setAttribute("designationId",employeeListById.getDesignationId());
			  renderRequest.setAttribute("branchId",employeeListById.getBranchId());
			  
			  log.info("employeeAllDetailsModel ==" +employeeAllDetailsModel.toString());
			  renderRequest.setAttribute("employeeListById",employeeAllDetailsModel );
			  include("/html/employee/employee.jsp", renderRequest, renderResponse);
			  log.info("employeeListById" + employeeListById);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("this is called");
			renderRequest.setAttribute(AixtorEmployeeConstant.EMPLOYEE_LIST, employeeLocalService.getEmployeeDetails());
			super.render(renderRequest, renderResponse);
		}
	}	
	
	public void deleteRecordById(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.info("Delete  Action Called");
		Long employeeId = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.EMPLOYEE_ID);
		try {
			employeeLocalService.deleteEmployee(employeeId);
		} catch (PortalException e) {
			e.printStackTrace();
		}
//		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		actionRequest.setAttribute(AixtorEmployeeConstant.EMPLOYEE_LIST, employeeLocalService.getEmployeeDetails());
//		auditTransactionApi.AuditLocalService("Delete", themeDisplay.getPortletDisplay().getId(), themeDisplay);
	}
	
	public void getEmployeeDetails(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.info("insertDetails  Action Called");
		String firstName = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.FIRST_NAME);
		String lastName = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.LAST_NAME);
		String email = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.EMAIL);
		Long mobileNumber = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.MOBILE_NUMBER);
		Long departmentHead = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.DEPARTMENT_HEAD);
		Long branch = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.BRANCH);

		Long designationName = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.DESIGNATION_NAME);
		String address = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.ADDRESS);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Employee employee = employeeLocalService
				.createEmployee(CounterLocalServiceUtil.increment(Employee.class.getName()));

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

		employeeLocalService.addEmployee(employee);
		System.out.println("Update Action Called");
		
		SessionMessages.add(actionRequest, "successKey", "Inserted Successfully Record");
	}
	
	public void updateEmployeeRecords(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.info("update Action Called");
		Long employeeId=ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.EMPLOYEE_ID);
		String firstName = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.FIRST_NAME);
		String lastName = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.LAST_NAME);
		String email = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.EMAIL);
		Long mobileNumber = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.MOBILE_NUMBER);
		Long departmentHead = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.DEPARTMENT_HEAD);
		Long branch = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.BRANCH);

		System.out.println("update all details below");
		System.out.println(employeeId + "" + firstName + " " + lastName + " " + email + " " + mobileNumber + " " + departmentHead + " " + branch);
		Long designationName = ParamUtil.getLong(actionRequest, AixtorEmployeeConstant.DESIGNATION_NAME);
		String address = ParamUtil.getString(actionRequest, AixtorEmployeeConstant.ADDRESS);

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
