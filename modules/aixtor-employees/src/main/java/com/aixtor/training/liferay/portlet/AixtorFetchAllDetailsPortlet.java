//package com.aixtor.training.liferay.portlet;
//
//import com.aixtor.training.liferay.constants.AixtorFetchAllDetailsConstant;
//import com.aixtor.training.liferay.model.Branch;
//import com.aixtor.training.liferay.model.Department;
//import com.aixtor.training.liferay.model.Designation;
//import com.aixtor.training.liferay.model.Employee;
//import com.aixtor.training.liferay.model.EmployeeAllDetailsModel;
//import com.aixtor.training.liferay.model.GetJoinEmployeeModel;
//import com.aixtor.training.liferay.service.BranchLocalService;
//import com.aixtor.training.liferay.service.DepartmentLocalService;
//import com.aixtor.training.liferay.service.DesignationLocalService;
//import com.aixtor.training.liferay.service.EmployeeLocalService;
//import com.liferay.portal.kernel.dao.orm.Criterion;
//import com.liferay.portal.kernel.dao.orm.DynamicQuery;
//import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
//import com.liferay.portal.kernel.dao.orm.Property;
//import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
//import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
//import com.liferay.portal.kernel.dao.orm.Session;
//import com.liferay.portal.kernel.dao.orm.SessionFactory;
//import com.liferay.portal.kernel.exception.SystemException;
//import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
//import com.liferay.portal.kernel.util.ParamUtil;
//import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
//import com.liferay.util.dao.orm.CustomSQL;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.portlet.ActionRequest;
//import javax.portlet.ActionResponse;
//import javax.portlet.Portlet;
//import javax.portlet.PortletException;
//import javax.portlet.ProcessAction;
//import javax.portlet.RenderRequest;
//import javax.portlet.RenderResponse;
//import javax.validation.Validator;
//
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//
//@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
//		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
//		"javax.portlet.display-name=AixtorFetchAllDetails", "javax.portlet.init-param.template-path=/",
//		"javax.portlet.init-param.view-template=/html/getAllDetails/view.jsp",
//		"javax.portlet.name=" + AixtorFetchAllDetailsConstant.AIXTOR_GETALLDETAILS,
//		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user" },
//
//		service = Portlet.class)
//public class AixtorFetchAllDetailsPortlet extends MVCPortlet {
//
//	@Reference
//	DepartmentLocalService departmentLocalService;
//
//	@Reference
//	DesignationLocalService designationLocalService;
//
//	@Reference
//	BranchLocalService branchLocalService;
//
//	@Reference
//	EmployeeLocalService employeeLocalService;
//
//	@Override
//	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
//			throws IOException, PortletException {
//
//		String searchKeyword = ParamUtil.getString(renderRequest, AixtorFetchAllDetailsConstant.SEARCH_INPUT);
//		String fromdatestr = ParamUtil.getString(renderRequest, AixtorFetchAllDetailsConstant.FROM_DATE);
//		String todatestr = ParamUtil.getString(renderRequest, AixtorFetchAllDetailsConstant.T0_DATE);
//		renderRequest.setAttribute(AixtorFetchAllDetailsConstant.SEARCH_INPUT, searchKeyword);
//		renderRequest.setAttribute(AixtorFetchAllDetailsConstant.FROM_DATE, fromdatestr);
//		renderRequest.setAttribute(AixtorFetchAllDetailsConstant.T0_DATE, todatestr);
//		List<EmployeeAllDetailsModel> employeeList;
//
//		
//		if (!searchKeyword.isEmpty()) {
//			employeeList = employeeLocalService.getEmployeeAllDetails(searchKeyword);
//			renderRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST, employeeList);
//		} else if (!fromdatestr.isEmpty() && !todatestr.isEmpty()) {
//			Date fromdate = null;
//			Date todate = null;
//			try {
//				SimpleDateFormat dateFormat = new SimpleDateFormat(AixtorFetchAllDetailsConstant.SIMPLE_DATE_FORMAT);
//				fromdate = dateFormat.parse(fromdatestr + " 00:00:00.000000");
//				todate = dateFormat.parse(todatestr + " 23:59:00.000000");
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//
//			DynamicQuery dynamicQuery = employeeLocalService.dynamicQuery();
//			dynamicQuery
//					.add(RestrictionsFactoryUtil.between(AixtorFetchAllDetailsConstant.CREATE_DATE, fromdate, todate));
//
//			List<Employee> listofemployee = employeeLocalService.dynamicQuery(dynamicQuery);
//
//			List<EmployeeAllDetailsModel> employeeListofjoin = new ArrayList<EmployeeAllDetailsModel>();
//
//			for (Employee employee : listofemployee) {
//				EmployeeAllDetailsModel employeeAllDetailsModel = new EmployeeAllDetailsModel();
//				employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
//				employeeAllDetailsModel.setFirstName(employee.getFirstName());
//				employeeAllDetailsModel.setLastName(employee.getLastName());
//				employeeAllDetailsModel.setEmail(employee.getEmail());
//				employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
//				employeeAllDetailsModel.setDepartmentName(
//						departmentLocalService.findByGetDepartmentNameById(employee.getDepartmentId()));
//				employeeAllDetailsModel
//						.setBranchName(branchLocalService.findByGetbranchNameById(employee.getBranchId()));
//				employeeAllDetailsModel.setDesignationName(
//						designationLocalService.findByGetdesignationNameById(employee.getDesignationId()));
//				employeeAllDetailsModel.setAddress(employee.getAddress());
//
//				employeeListofjoin.add(employeeAllDetailsModel);
//			}
//			renderRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST, employeeListofjoin);
//		} else {
//			renderRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST,employeeLocalService.getEmployeeDetails());
//		}
//		super.render(renderRequest, renderResponse);
//	}
//
//	@ProcessAction(name = "filterURL")
//	@Override
//	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
//			throws IOException, PortletException {
//
//		// task2
////		String searchKeyword = ParamUtil.getString(actionRequest, AixtorFetchAllDetailsConstant.SEARCH_INPUT);
////		String fromdatestr = ParamUtil.getString(actionRequest, AixtorFetchAllDetailsConstant.FROM_DATE);
////		String todatestr = ParamUtil.getString(actionRequest, AixtorFetchAllDetailsConstant.T0_DATE);
////		actionRequest.setAttribute("search",searchKeyword);
////		actionRequest.setAttribute("fromDate",fromdatestr);
////		actionRequest.setAttribute("toDate",todatestr);
////		List<EmployeeAllDetailsModel> employeelist;
//////		actionRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST, employeelist);
////
////		if (!searchKeyword.isEmpty()) {
////			employeelist = employeeLocalService.getEmployeeAllDetails(searchKeyword);
////			actionRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST, employeelist);
////		} else if (!fromdatestr.isEmpty() && !todatestr.isEmpty()) {
////			Date fromdate = null;
////			Date todate = null;
////			try {
////				SimpleDateFormat dateFormat = new SimpleDateFormat(AixtorFetchAllDetailsConstant.SIMPLE_DATE_FORMAT);
////				fromdate = dateFormat.parse(fromdatestr + " 00:00:00.000000");
////				todate = dateFormat.parse(todatestr + " 23:59:00.000000");
////			} catch (ParseException e) {
////				e.printStackTrace();
////			}
////
////			DynamicQuery dynamicQuery = employeeLocalService.dynamicQuery();
////			dynamicQuery
////					.add(RestrictionsFactoryUtil.between(AixtorFetchAllDetailsConstant.CREATE_DATE, fromdate, todate));
////
////			List<Employee> listofemployee = employeeLocalService.dynamicQuery(dynamicQuery);
////
////			List<EmployeeAllDetailsModel> employeeListofjoin = new ArrayList<EmployeeAllDetailsModel>();
////
////			for (Employee employee : listofemployee) {
////				EmployeeAllDetailsModel employeeAllDetailsModel = new EmployeeAllDetailsModel();
////				employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
////				employeeAllDetailsModel.setFirstName(employee.getFirstName());
////				employeeAllDetailsModel.setLastName(employee.getLastName());
////				employeeAllDetailsModel.setEmail(employee.getEmail());
////				employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
////				employeeAllDetailsModel.setDepartmentName(
////						departmentLocalService.findByGetDepartmentNameById(employee.getDepartmentId()));
////				employeeAllDetailsModel
////						.setBranchName(branchLocalService.findByGetbranchNameById(employee.getBranchId()));
////				employeeAllDetailsModel.setDesignationName(
////						designationLocalService.findByGetdesignationNameById(employee.getDesignationId()));
////				employeeAllDetailsModel.setAddress(employee.getAddress());
////
////				employeeListofjoin.add(employeeAllDetailsModel);
////			}
////			actionRequest.setAttribute(AixtorFetchAllDetailsConstant.EMPLOYEE_LIST, employeeListofjoin);
////		} else {
////			String errorForm = "Please Select SearchKeyword or FromDate And ToDate";
////			actionRequest.setAttribute("validaiton", errorForm);
////		}
////		
//	}
//}
//
////just try to implement like query using dynamicQuery
//// List<Employee> list = employeeLocalService.getEmployeeAllDetails();
//// System.out.println("searchKeyword" + searchKeyword);
//// DynamicQuery dynamicQuery = employeeLocalService.dynamicQuery();
//// Property property = PropertyFactoryUtil.forName("firstName");
//// dynamicQuery.add(property.like("%" + searchKeyword + "%"));
//// List<Employee> employees = employeeLocalService.dynamicQuery(dynamicQuery);
//// actionRequest.setAttribute("employeeList", employees);