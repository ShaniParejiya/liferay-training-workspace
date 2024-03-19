package com.aixtor.training.liferay.model;

public class EmployeeAllDetailsModel {

//	public long uuid_;
	public String employeeId;
	public String firstName;
	public String lastName;
	public String email;
	public String mobileNumber;
	public String address;
	public String departmentName;
	public String designationName;
	public String branchName;

	/*
	 * public long getUuid() { return uuid_; } public void setUuid_(long uuid) {
	 * this.uuid_ = uuid_; }
	 */
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
	@Override
	public String toString() {
		return "EmployeeAllDetailsModel [ employeeId=" + employeeId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", departmentName=" + departmentName + ", designationName=" + designationName
				+ ", branchName=" + branchName + ", getEmployeeId()=" + getEmployeeId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getMobileNumber()=" + getMobileNumber() + ", getAddress()=" + getAddress()
				+ ", getDepartmentName()=" + getDepartmentName() + ", getDesignationName()=" + getDesignationName()
				+ ", getBranchName()=" + getBranchName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public EmployeeAllDetailsModel(long uuid_, String employeeId, String firstName, String lastName, String email,
			String mobileNumber, String address, String departmentName, String designationName, String branchName) {
		super();
//		this.uuid_ = uuid_;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.departmentName = departmentName;
		this.designationName = designationName;
		this.branchName = branchName;
	}
	public EmployeeAllDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
