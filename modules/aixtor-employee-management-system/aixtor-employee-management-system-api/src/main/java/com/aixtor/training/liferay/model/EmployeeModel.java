/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.aixtor.training.liferay.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Employee service. Represents a row in the &quot;AIXTOR_Employee&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.aixtor.training.liferay.model.impl.EmployeeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.aixtor.training.liferay.model.impl.EmployeeImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
@ProviderType
public interface EmployeeModel
	extends BaseModel<Employee>, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a employee model instance should use the {@link Employee} interface instead.
	 */

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this employee.
	 *
	 * @return the uuid of this employee
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this employee.
	 *
	 * @param uuid the uuid of this employee
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the employee ID of this employee.
	 *
	 * @return the employee ID of this employee
	 */
	public long getEmployeeId();

	/**
	 * Sets the employee ID of this employee.
	 *
	 * @param employeeId the employee ID of this employee
	 */
	public void setEmployeeId(long employeeId);

	/**
	 * Returns the first name of this employee.
	 *
	 * @return the first name of this employee
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this employee.
	 *
	 * @param firstName the first name of this employee
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the last name of this employee.
	 *
	 * @return the last name of this employee
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this employee.
	 *
	 * @param lastName the last name of this employee
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the email of this employee.
	 *
	 * @return the email of this employee
	 */
	@AutoEscape
	public String getEmail();

	/**
	 * Sets the email of this employee.
	 *
	 * @param email the email of this employee
	 */
	public void setEmail(String email);

	/**
	 * Returns the mobile number of this employee.
	 *
	 * @return the mobile number of this employee
	 */
	public long getMobileNumber();

	/**
	 * Sets the mobile number of this employee.
	 *
	 * @param mobileNumber the mobile number of this employee
	 */
	public void setMobileNumber(long mobileNumber);

	/**
	 * Returns the department ID of this employee.
	 *
	 * @return the department ID of this employee
	 */
	public long getDepartmentId();

	/**
	 * Sets the department ID of this employee.
	 *
	 * @param departmentId the department ID of this employee
	 */
	public void setDepartmentId(long departmentId);

	/**
	 * Returns the branch ID of this employee.
	 *
	 * @return the branch ID of this employee
	 */
	public long getBranchId();

	/**
	 * Sets the branch ID of this employee.
	 *
	 * @param branchId the branch ID of this employee
	 */
	public void setBranchId(long branchId);

	/**
	 * Returns the designation ID of this employee.
	 *
	 * @return the designation ID of this employee
	 */
	public long getDesignationId();

	/**
	 * Sets the designation ID of this employee.
	 *
	 * @param designationId the designation ID of this employee
	 */
	public void setDesignationId(long designationId);

	/**
	 * Returns the address of this employee.
	 *
	 * @return the address of this employee
	 */
	@AutoEscape
	public String getAddress();

	/**
	 * Sets the address of this employee.
	 *
	 * @param address the address of this employee
	 */
	public void setAddress(String address);

	/**
	 * Returns the company ID of this employee.
	 *
	 * @return the company ID of this employee
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this employee.
	 *
	 * @param companyId the company ID of this employee
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this employee.
	 *
	 * @return the user ID of this employee
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this employee.
	 *
	 * @param userId the user ID of this employee
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this employee.
	 *
	 * @return the user uuid of this employee
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this employee.
	 *
	 * @param userUuid the user uuid of this employee
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this employee.
	 *
	 * @return the user name of this employee
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this employee.
	 *
	 * @param userName the user name of this employee
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this employee.
	 *
	 * @return the create date of this employee
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this employee.
	 *
	 * @param createDate the create date of this employee
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this employee.
	 *
	 * @return the modified date of this employee
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this employee.
	 *
	 * @param modifiedDate the modified date of this employee
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	@Override
	public Employee cloneWithOriginalValues();

}