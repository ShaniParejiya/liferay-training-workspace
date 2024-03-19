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

package com.aixtor.training.liferay.model.impl;

import com.aixtor.training.liferay.model.Branch;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Branch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BranchCacheModel implements CacheModel<Branch>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BranchCacheModel)) {
			return false;
		}

		BranchCacheModel branchCacheModel = (BranchCacheModel)object;

		if (branchId == branchCacheModel.branchId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, branchId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", branchId=");
		sb.append(branchId);
		sb.append(", branchName=");
		sb.append(branchName);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", stateId=");
		sb.append(stateId);
		sb.append(", cityId=");
		sb.append(cityId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Branch toEntityModel() {
		BranchImpl branchImpl = new BranchImpl();

		if (uuid == null) {
			branchImpl.setUuid("");
		}
		else {
			branchImpl.setUuid(uuid);
		}

		branchImpl.setBranchId(branchId);

		if (branchName == null) {
			branchImpl.setBranchName("");
		}
		else {
			branchImpl.setBranchName(branchName);
		}

		branchImpl.setCountryId(countryId);
		branchImpl.setStateId(stateId);
		branchImpl.setCityId(cityId);
		branchImpl.setCompanyId(companyId);
		branchImpl.setUserId(userId);

		if (userName == null) {
			branchImpl.setUserName("");
		}
		else {
			branchImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			branchImpl.setCreateDate(null);
		}
		else {
			branchImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			branchImpl.setModifiedDate(null);
		}
		else {
			branchImpl.setModifiedDate(new Date(modifiedDate));
		}

		branchImpl.resetOriginalValues();

		return branchImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		branchId = objectInput.readLong();
		branchName = objectInput.readUTF();

		countryId = objectInput.readLong();

		stateId = objectInput.readLong();

		cityId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(branchId);

		if (branchName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(branchName);
		}

		objectOutput.writeLong(countryId);

		objectOutput.writeLong(stateId);

		objectOutput.writeLong(cityId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public String uuid;
	public long branchId;
	public String branchName;
	public long countryId;
	public long stateId;
	public long cityId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;

}