package com.aixtor.training.liferay.employee.headless.internal.resource.v1_0;

import com.aixtor.training.liferay.employee.headless.dto.v1_0.Branch;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.Employee;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.EmployeeDataRequest;

import com.aixtor.training.liferay.employee.headless.dto.v1_0.EmployeeDataResponse;
import com.aixtor.training.liferay.employee.headless.dto.v1_0.Status;
import com.aixtor.training.liferay.employee.headless.resource.v1_0.GetEmployeesByBranchResource;
import com.aixtor.training.liferay.employee.service.builder.model.City;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.model.Designation;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.model.State;
import com.aixtor.training.liferay.employee.service.builder.service.BranchLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.CityLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.aixtor.training.liferay.employee.service.builder.service.StateLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.service.CountryLocalService;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author shani.patel
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/get-employees-by-branch.properties", scope = ServiceScope.PROTOTYPE, service = GetEmployeesByBranchResource.class)
public class GetEmployeesByBranchResourceImpl extends BaseGetEmployeesByBranchResourceImpl {

	public static Log LOGGER = LogFactoryUtil.getLog(GetEmployeesByBranchResourceImpl.class);
	@Reference
	EmployeeLocalService employeeLocalService;

	@Reference
	BranchLocalService branchLocalService;

	@Reference
	DesignationLocalService designationLocalService;

	@Reference
	DepartmentLocalService departmentLocalService;

	@Reference
	CityLocalService cityLocalService;

	@Reference
	StateLocalService stateLocalService;

	@Reference
	CountryLocalService countryLocalService;

	Branch[] branchList = new Branch[1];

	@Override
	public EmployeeDataResponse getEmployeesByBranch(EmployeeDataRequest employeeDataRequest) throws Exception {

		LOGGER.info("Response Called");

		Long branchId = employeeDataRequest.getBranchId();
		String branchName = employeeDataRequest.getBranchName();

		EmployeeDataResponse employeeDataResponse = new EmployeeDataResponse();
		Status status = new Status();

		if ((branchId == null || branchId == 0L) && (branchName == null || branchName.isEmpty())) {
			// Both branchId and branchName are missing
			LOGGER.info("Both branchId and branchName are missing in the request");
			status.setCode("Failed");
			status.setMessage("Both branchId and branchName are missing");
			employeeDataResponse.setStatus(status);
			return employeeDataResponse;
		}

		com.aixtor.training.liferay.employee.service.builder.model.Branch branch = null;

		if (branchId == null || branchId == 0L) {
			// If branchId is missing or 0, try to fetch it using branchName
			branch = branchLocalService.findByfetchBranchNameByBranchId(branchName);
			if (branch == null) {
				// If branchId is still not found, return error response
				LOGGER.info("Branch not found for branchName: " + branchName);
				status.setCode("Failed");
				status.setMessage("Branch not found for branchName: " + branchName);
				employeeDataResponse.setStatus(status);
				return employeeDataResponse;
			}
			branchId = branch.getBranchId();
		} else if (branchName == null || branchName.isEmpty()) {
			// If branchId is provided but branchName is missing or empty,
			// try to fetch branchName using branchId
			branch = branchLocalService.findByGetbranchNameById(branchId);
			if (branch == null) {
				// If branchName is still not found, return error response
				LOGGER.info("Branch not found for branchId: " + branchId);
				status.setCode("Failed");
				status.setMessage("Branch not found for branchId: " + branchId);
				employeeDataResponse.setStatus(status);
				return employeeDataResponse;
			}
			branchName = branch.getBranchName();
		}

		List<com.aixtor.training.liferay.employee.service.builder.model.Employee> empList = employeeLocalService
				.findByfetchBranchId(branchId);

		List<EmployeeAllDetailsModel> employeeAllDetailsModels = new ArrayList<>();

		for (com.aixtor.training.liferay.employee.service.builder.model.Employee employee : empList) {
			EmployeeAllDetailsModel employeeAllDetailsModel = new EmployeeAllDetailsModel();
			try {
				Country country = countryLocalService
						.getCountry(branchLocalService.getBranch(employee.getBranchId()).getCountryId());
				employeeAllDetailsModel.setEmployeeId(String.valueOf(employee.getEmployeeId()));
				employeeAllDetailsModel.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
				employeeAllDetailsModel.setMobileNumber(String.valueOf(employee.getMobileNumber()));
				employeeAllDetailsModel.setEmail(employee.getEmail());
				employeeAllDetailsModel.setDesignationName(
						designationLocalService.getDesignation(employee.getDesignationId()).getDesignationName());
				employeeAllDetailsModel.setDepartmentName(
						departmentLocalService.getDepartment(employee.getDepartmentId()).getDepartmentName());
				employeeAllDetailsModel.setBranchId(employee.getBranchId());
				employeeAllDetailsModel
						.setBranchName(branchLocalService.getBranch(employee.getBranchId()).getBranchName());
				employeeAllDetailsModel.setCity(cityLocalService
						.getCity(branchLocalService.getBranch(employee.getBranchId()).getCityId()).getCityName());
				employeeAllDetailsModel.setState(stateLocalService
						.getState(branchLocalService.getBranch(employee.getBranchId()).getStateId()).getStateName());
				employeeAllDetailsModel.setCountry(country.getName());
				employeeAllDetailsModels.add(employeeAllDetailsModel);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}

		Employee[] empArray = new Employee[employeeAllDetailsModels.size()];

		int i = 0;
		for (EmployeeAllDetailsModel employeeAllDetailsModel : employeeAllDetailsModels) {
			try {
				Employee employee = new Employee();
				employee.setEmployeeId(Long.parseLong(employeeAllDetailsModel.getEmployeeId()));
				employee.setEmployeeName(employeeAllDetailsModel.getEmployeeName());
				employee.setMobile(employeeAllDetailsModel.getMobileNumber());
				employee.setEmail(employeeAllDetailsModel.getEmail());
				employee.setDesignationName(employeeAllDetailsModel.getDesignationName());
				employee.setDepartmentName(employeeAllDetailsModel.getDepartmentName());
				employee.setBranchName(employeeAllDetailsModel.getBranchName());

				Branch branchResponse = new Branch();
				branchResponse.setBranchId(employeeAllDetailsModel.getBranchId());
				branchResponse.setBranchName(employeeAllDetailsModel.getBranchName());
				branchResponse.setCity(employeeAllDetailsModel.getCity());
				branchResponse.setState(employeeAllDetailsModel.getState());
				branchResponse.setCountry(employeeAllDetailsModel.getCountry());

				empArray[i] = employee;
				branchList[0] = branchResponse;
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
			i += 1;
		}

//		    if (empArray.length > 0 && branchList.length>0) {
//		        status.setCode("Success");
//		        status.setMessage("Employee Data Fetch Successfully...");
//		    } else {
//		        status.setCode("Failed");
//		        status.setMessage("No Employee Data Found");
//		    }
		
		if (empArray.length > 0 && branchList.length > 0) {
			status.setCode("Success");
			status.setMessage("Employee Data Fetch Successfully...");
		} else {
			status.setCode("Failed");
			if (empArray.length == 0) {
				status.setMessage("No Employee Data Found");
			} else {
				status.setMessage("Branch not found for branchName: " + branchName);
			}
		}

		employeeDataResponse.setEmployees(empArray);
		employeeDataResponse.setBranchs(branchList);
		employeeDataResponse.setStatus(status);
		employeeDataResponse.setTotalEmployee(Long.valueOf(employeeAllDetailsModels.size()));

		return employeeDataResponse;
	}
}