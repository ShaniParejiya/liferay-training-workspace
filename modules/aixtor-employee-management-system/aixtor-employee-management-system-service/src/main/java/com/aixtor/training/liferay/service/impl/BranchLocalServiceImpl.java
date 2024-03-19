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

package com.aixtor.training.liferay.service.impl;

import com.aixtor.training.liferay.exception.NoSuchBranchException;
import com.aixtor.training.liferay.model.Branch;
import com.aixtor.training.liferay.service.base.BranchLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.aixtor.training.liferay.model.Branch",
	service = AopService.class
)
public class BranchLocalServiceImpl extends BranchLocalServiceBaseImpl {
	
	public Branch findByGetbranchNameById(long branchId) {
		try {
			return branchPersistence.findByGetbranchNameById(branchId);
		} catch (NoSuchBranchException e) {
			e.printStackTrace();
		}
		return null;
	}
}