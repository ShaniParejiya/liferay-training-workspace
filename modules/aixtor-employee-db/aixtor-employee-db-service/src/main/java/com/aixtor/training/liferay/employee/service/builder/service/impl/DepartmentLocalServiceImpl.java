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

import com.aixtor.training.liferay.employee.service.builder.exception.NoSuchDepartmentException;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.service.base.DepartmentLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.aixtor.training.liferay.employee.service.builder.model.Department",
	service = AopService.class
)
public class DepartmentLocalServiceImpl extends DepartmentLocalServiceBaseImpl {
	
	public Department findByGetDepartmentNameById(long departmentId) {
		
		try {
			return departmentPersistence.findByGetDepartmentNameById(departmentId);
		} catch (NoSuchDepartmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Department findByGetDepartmentNameByName(String departmentName) {
		try {
			return departmentPersistence.findByGetDepartmentNameByName(departmentName);
		} catch (NoSuchDepartmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}