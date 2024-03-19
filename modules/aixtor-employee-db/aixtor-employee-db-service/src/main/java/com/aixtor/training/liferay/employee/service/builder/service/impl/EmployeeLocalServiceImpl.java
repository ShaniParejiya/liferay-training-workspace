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

package com.aixtor.training.liferay.employee.service.builder.service.impl;

import com.aixtor.training.liferay.employee.service.builder.model.Employee;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.base.EmployeeLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.aixtor.training.liferay.employee.service.builder.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
	
	
	public List<EmployeeAllDetailsModel> getEmployeeAllDetails(String keyWordValue){
		return employeeFinder.getEmployeeAllDetails(keyWordValue);
	}
	
	public List<EmployeeAllDetailsModel> getEmployeeDetails(){
		return employeeFinder.getEmployeeDetails();
	}
	
	public List<Employee> findByfetchBranchId(long branchId){
		
		return employeePersistence.findByfetchBranchId(branchId);
	}
	
	public List<Employee> findByFetchDepartmentId(long departmentId){
		return employeePersistence.findByFetchDepartmentId(departmentId);
	}
}