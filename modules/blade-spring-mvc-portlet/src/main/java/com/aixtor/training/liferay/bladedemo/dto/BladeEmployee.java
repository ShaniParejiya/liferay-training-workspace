package com.aixtor.training.liferay.bladedemo.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotBlank;

public class BladeEmployee implements Serializable{

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String mobileNumber;
	
	@NotBlank
	private BigInteger departmentId;
	
	@NotBlank
	private BigInteger branchId;
	
	@NotBlank
	private BigInteger designationId;
	
	@NotBlank
	private String address;

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

	public BigInteger getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(BigInteger departmentId) {
		this.departmentId = departmentId;
	}

	public BigInteger getBranchId() {
		return branchId;
	}

	public void setBranchId(BigInteger branchId) {
		this.branchId = branchId;
	}

	public BigInteger getDesignationId() {
		return designationId;
	}

	public void setDesignationId(BigInteger designationId) {
		this.designationId = designationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "BladeEmployee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", departmentId=" + departmentId + ", branchId=" + branchId
				+ ", designationId=" + designationId + ", address=" + address + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + ", getMobileNumber()="
				+ getMobileNumber() + ", getDepartmentId()=" + getDepartmentId() + ", getBranchId()=" + getBranchId()
				+ ", getDesignationId()=" + getDesignationId() + ", getAddress()=" + getAddress() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public BladeEmployee(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String mobileNumber, @NotBlank BigInteger departmentId, @NotBlank BigInteger branchId,
			@NotBlank BigInteger designationId, @NotBlank String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.departmentId = departmentId;
		this.branchId = branchId;
		this.designationId = designationId;
		this.address = address;
	}

	public BladeEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
