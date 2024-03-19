package com.aixtor.training.liferay.bladedemo.controller;

import com.aixtor.training.liferay.bladedemo.dto.BladeEmployee;

import com.aixtor.training.liferay.employee.service.builder.model.Branch;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.model.Designation;
import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalServiceUtil;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalServiceUtil;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalServiceUtil;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author shani.patel
 */
@Controller
@RequestMapping("VIEW")
public class UserController {
	
	@RenderMapping
	public String prepareView(Model model) {
		
		try {
			
//			DesignationServiceUtil.getService().;
			System.out.println("render called ");
			List<Designation> designationList=DesignationLocalServiceUtil.getService().getDesignations(-1, -1);
			List<Employee> employeeList=EmployeeLocalServiceUtil.getService().getEmployees(-1, -1);
			List<Branch> branchList=BranchLocalServiceUtil.getService().getBranches(-1, -1);
			List<Department> departmentList=DepartmentLocalServiceUtil.getService().getDepartments(-1, -1);
			model.addAttribute("designation", designationList);
			model.addAttribute("departments", departmentList);
			model.addAttribute("branch", branchList);
			
		}catch(Exception e) {
			System.out.println("error in catch ==== " + e.getMessage());
		}
		return "employee-registration";
	}
	
	@ActionMapping(value = "handleEmployee")
    public void handleEmployee(@ModelAttribute("insertEmployeeDetails") BladeEmployee bladeEmployee,
    		ActionRequest actionRequest, ActionResponse actionResponse) {
		
		
		System.out.println("Action Called");
		try {
			ThemeDisplay themeDisplay=(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			Employee employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.increment(Employee.class.getName()));
			
			employee.setCompanyId(themeDisplay.getCompanyId());
			employee.setUserName(themeDisplay.getUser().getFullName());
			employee.setCreateDate(new Date());
			employee.setModifiedDate(new Date());
			employee.setUserId(themeDisplay.getUserId());
			
			employee.setFirstName(bladeEmployee.getFirstName());
			employee.setLastName(bladeEmployee.getLastName());
			employee.setEmail(bladeEmployee.getEmail());
			employee.setAddress(bladeEmployee.getAddress());
			employee.setMobileNumber(Long.valueOf(bladeEmployee.getMobileNumber()));
			employee.setBranchId(bladeEmployee.getBranchId().longValue());
			employee.setDepartmentId(bladeEmployee.getDepartmentId().longValue());
			employee.setDesignationId(bladeEmployee.getDesignationId().longValue());
			System.out.println("employee list is ======= " + employee);
			
			EmployeeLocalServiceUtil.addEmployee(employee);
		}catch (Exception e) {
			System.out.println("error in insert == " + e.getMessage());
		}
				
		
		
    }
}