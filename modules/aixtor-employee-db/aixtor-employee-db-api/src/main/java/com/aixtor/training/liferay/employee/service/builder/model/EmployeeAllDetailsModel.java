package com.aixtor.training.liferay.employee.service.builder.model;

public class EmployeeAllDetailsModel {

	public String employeeId;
	public String firstName;
	public String lastName;
	public String email;
	public String mobileNumber;
	public String address;
	public String departmentName;
	public String designationName;
	public String branchName;
	
	//for api
	public String EmployeeName;
	public String State;
	public String City;
	public String Country;
	
	//for department api
	public String departmentId;
	public String departmentHead;
	
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Long branchId;
	
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentHead() {
		return departmentHead;
	}
	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}
	@Override
	public String toString() {
		return "EmployeeAllDetailsModel [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", departmentName=" + departmentName + ", designationName=" + designationName + ", branchName="
				+ branchName + ", EmployeeName=" + EmployeeName + ", State=" + State + ", City=" + City + ", Country="
				+ Country + ", departmentId=" + departmentId + ", departmentHead=" + departmentHead + ", branchId="
				+ branchId + ", getCountry()=" + getCountry() + ", getEmployeeName()=" + getEmployeeName()
				+ ", getState()=" + getState() + ", getCity()=" + getCity() + ", getBranchId()=" + getBranchId()
				+ ", getEmployeeId()=" + getEmployeeId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getEmail()=" + getEmail() + ", getMobileNumber()=" + getMobileNumber()
				+ ", getAddress()=" + getAddress() + ", getDepartmentName()=" + getDepartmentName()
				+ ", getDesignationName()=" + getDesignationName() + ", getBranchName()=" + getBranchName()
				+ ", getDepartmentId()=" + getDepartmentId() + ", getDepartmentHead()=" + getDepartmentHead()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public EmployeeAllDetailsModel(String employeeId, String firstName, String lastName, String email,
			String mobileNumber, String address, String departmentName, String designationName, String branchName,
			String employeeName, String state, String city, String country, String departmentId, String departmentHead,
			Long branchId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.departmentName = departmentName;
		this.designationName = designationName;
		this.branchName = branchName;
		EmployeeName = employeeName;
		State = state;
		City = city;
		Country = country;
		this.departmentId = departmentId;
		this.departmentHead = departmentHead;
		this.branchId = branchId;
	}
	public EmployeeAllDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
