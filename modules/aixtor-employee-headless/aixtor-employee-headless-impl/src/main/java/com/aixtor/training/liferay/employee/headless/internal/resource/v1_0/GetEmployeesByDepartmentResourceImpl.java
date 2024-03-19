package com.aixtor.training.liferay.employee.headless.internal.resource.v1_0;

import com.aixtor.training.liferay.employee.headless.dto.v1_0.Department;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.Employee;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.EmployeeDetaByDepartmentRequest;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.EmployeeDetaByDepartmentResponse;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.Status;
import com.aixtor.training.liferay.employee.headless.resource.v1_0.GetEmployeesByDepartmentResource;

import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author shani.patel
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/get-employees-by-department.properties", scope = ServiceScope.PROTOTYPE, service = GetEmployeesByDepartmentResource.class)
public class GetEmployeesByDepartmentResourceImpl extends BaseGetEmployeesByDepartmentResourceImpl {

	public static Log LOGGER = LogFactoryUtil.getLog(GetEmployeesByDepartmentResourceImpl.class);

	@Reference
	DepartmentLocalService departmentLocalService;

	@Reference
	BranchLocalService branchLocalService; 
	
	@Reference
	EmployeeLocalService employeeLocalService;
	
	@Reference
	DesignationLocalService designationLocalService;
	
	@Reference
	UserLocalService userLocalService;
	
	Department[] departmentList = new Department[1];
	
	@Override
	public EmployeeDetaByDepartmentResponse getEmployeesByDepartment(
			EmployeeDetaByDepartmentRequest employeeDetaByDepartmentRequest) throws Exception {

//		LOGGER.info("DEPARTMENT Called");

		Long departmentId = employeeDetaByDepartmentRequest.getDepartmentId();
		String departmentName = employeeDetaByDepartmentRequest.getDepartmentName();

		EmployeeDetaByDepartmentResponse employeeDetaByDepartmentResponse = new EmployeeDetaByDepartmentResponse();

		if ((departmentId == null || departmentId == 0L) && (departmentName == null || departmentName.isEmpty())) {
		    Status status = new Status();
//		    LOGGER.info("if callled");
		    status.setCode("Failed");
		    status.setMessage("Both branchId and branchName are missing");
		    employeeDetaByDepartmentResponse.setStatus(status);
		    return employeeDetaByDepartmentResponse;
		}

		if (departmentId == null || departmentId == 0L) {
			com.aixtor.training.liferay.employee.service.builder.model.Department department = departmentLocalService.findByGetDepartmentNameByName(departmentName);
			if (department == null) {
//				LOGGER.info("Department not found for departmentName: " + departmentName);
				Status status = new Status();
				status.setCode("Failed");
				status.setMessage("Department not found for departmentName: " + departmentName);
				employeeDetaByDepartmentResponse.setStatus(status);
				return employeeDetaByDepartmentResponse;
			}
			departmentId = department.getDepartmentId();
			
		} else if (departmentName == null && departmentId != null) {
			
			com.aixtor.training.liferay.employee.service.builder.model.Department department = departmentLocalService.findByGetDepartmentNameById(departmentId);
			
			if (department == null) {
				LOGGER.info("else if callled");
				Status status = new Status();
				status.setCode("Failed");
				status.setMessage("Department not found for departmentId: " + departmentId);
				employeeDetaByDepartmentResponse.setStatus(status);
				return employeeDetaByDepartmentResponse;
			}
			departmentName = department.getDepartmentName();
		}
		
		
		List<com.aixtor.training.liferay.employee.service.builder.model.Employee>  empList=employeeLocalService.findByFetchDepartmentId(departmentId);
				
		List<EmployeeAllDetailsModel> employeeAllDetailsModels= new ArrayList<EmployeeAllDetailsModel>();	
		
			for(com.aixtor.training.liferay.employee.service.builder.model.Employee employee:empList) {
				EmployeeAllDetailsModel employeeAllDetailsModel=new EmployeeAllDetailsModel();
				 try {					 
					 
				 employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
				 employeeAllDetailsModel.setEmployeeName(employee.getFirstName()+" "+employee.getLastName());
				 employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
				 employeeAllDetailsModel.setEmail(employee.getEmail());
				 employeeAllDetailsModel.setDepartmentId(String.valueOf(employee.getDepartmentId()));
				 
				 User user = userLocalService.getUser(Long.parseLong(departmentLocalService.getDepartment(departmentId).getDepartmentHead()));
				 employeeAllDetailsModel.setDepartmentHead(user.getFullName());
				 employeeAllDetailsModel.setDepartmentName(departmentLocalService.findByGetDepartmentNameById(employee.getDepartmentId()).getDepartmentName());
				 employeeAllDetailsModel.setDesignationName(designationLocalService.findByGetdesignationNameById(employee.getDesignationId()).getDesignationName());
				 if(employee.getBranchId()>0)
					 employeeAllDetailsModel.setBranchName(branchLocalService.findByGetbranchNameById(employee.getBranchId()).getBranchName());
					 employeeAllDetailsModels.add(employeeAllDetailsModel);
				 }catch(Exception e) {
					 LOGGER.info("\n\n\nError Message is: dc"+e.getMessage());
				 }
			}
			System.out.println("employeeAllDetailsMdoels" +employeeAllDetailsModels.toString());
			Status status = new Status();
			
			
			Employee[] emparray = new Employee[employeeAllDetailsModels.size()];
//			Department[] departmentList = new Department[employeeAllDetailsModels.size()];
			
			int i=0;
			for(EmployeeAllDetailsModel employeeAllDetailsModel: employeeAllDetailsModels) {
				try {
					
					Employee employee=new Employee();
				
					employee.setEmployeeId(Long.parseLong(employeeAllDetailsModel.getEmployeeId()));
					employee.setEmployeeName(employeeAllDetailsModel.getEmployeeName());
					employee.setMobile(employeeAllDetailsModel.getMobileNumber());
					employee.setEmail(employeeAllDetailsModel.getEmail());
					employee.setDesignationName(employeeAllDetailsModel.getDesignationName());
					employee.setDepartmentName(employeeAllDetailsModel.getDepartmentName());
					employee.setBranchName(employeeAllDetailsModel.getBranchName());
					
					com.aixtor.training.liferay.employee.headless.dto.v1_0.Department department=new com.aixtor.training.liferay.employee.headless.dto.v1_0.Department();
					department.setDepartmentId(Long.valueOf(employeeAllDetailsModel.getDepartmentId()));
					department.setDepartmentHead(employeeAllDetailsModel.getDepartmentHead());
					department.setDepartmentName(employeeAllDetailsModel.getDepartmentName());
					
					
					status.setCode("Success");
					status.setMessage("Employee Data Fetch Successfully...");
					
					emparray[i] = employee;
					departmentList[0] = department;
				}catch(Exception e) {
					status.setCode("Failed");
					status.setMessage("Employee Data Failed while Fetch Details");
					LOGGER.info(e.getMessage());
				}
				i += 1;
			}
		
	    employeeDetaByDepartmentResponse.setEmployees(emparray);
		employeeDetaByDepartmentResponse.setDepartments(departmentList);
		employeeDetaByDepartmentResponse.setStatus(status);
		employeeDetaByDepartmentResponse.setTotalEmployee(Long.valueOf(employeeAllDetailsModels.size()));

		return employeeDetaByDepartmentResponse;
	}
}