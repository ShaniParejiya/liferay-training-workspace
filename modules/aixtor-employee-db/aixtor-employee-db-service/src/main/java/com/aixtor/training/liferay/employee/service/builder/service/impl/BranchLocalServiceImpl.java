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

import com.aixtor.training.liferay.employee.service.builder.exception.NoSuchBranchException;
import com.aixtor.training.liferay.employee.service.builder.model.Branch;
import com.aixtor.training.liferay.employee.service.builder.service.base.BranchLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.aixtor.training.liferay.employee.service.builder.model.Branch",
	service = AopService.class
)
public class BranchLocalServiceImpl extends BranchLocalServiceBaseImpl {
	
	public Branch findByGetbranchNameById(long branchId) {
		
		return branchPersistence.fetchByGetbranchNameById(branchId);
	}
	
	
	public Branch findByfetchBranchNameByBranchId(String branchName) {
		
		try {
			return branchPersistence.findByfetchBranchNameByBranchId(branchName);
		} catch (NoSuchBranchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}