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

package com.aixtor.training.liferay.audit.model.impl;

import com.aixtor.training.liferay.audit.model.AuditLog;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditLogCacheModel
	implements CacheModel<AuditLog>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuditLogCacheModel)) {
			return false;
		}

		AuditLogCacheModel auditLogCacheModel = (AuditLogCacheModel)object;

		if (auditLogId == auditLogCacheModel.auditLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", auditLogId=");
		sb.append(auditLogId);
		sb.append(", action=");
		sb.append(action);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", groupId=");
		sb.append(groupId);
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
	public AuditLog toEntityModel() {
		AuditLogImpl auditLogImpl = new AuditLogImpl();

		if (uuid == null) {
			auditLogImpl.setUuid("");
		}
		else {
			auditLogImpl.setUuid(uuid);
		}

		auditLogImpl.setAuditLogId(auditLogId);

		if (action == null) {
			auditLogImpl.setAction("");
		}
		else {
			auditLogImpl.setAction(action);
		}

		if (moduleName == null) {
			auditLogImpl.setModuleName("");
		}
		else {
			auditLogImpl.setModuleName(moduleName);
		}

		auditLogImpl.setCreatedBy(createdBy);
		auditLogImpl.setModifiedBy(modifiedBy);
		auditLogImpl.setGroupId(groupId);
		auditLogImpl.setCompanyId(companyId);
		auditLogImpl.setUserId(userId);

		if (userName == null) {
			auditLogImpl.setUserName("");
		}
		else {
			auditLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			auditLogImpl.setCreateDate(null);
		}
		else {
			auditLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			auditLogImpl.setModifiedDate(null);
		}
		else {
			auditLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		auditLogImpl.resetOriginalValues();

		return auditLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		auditLogId = objectInput.readLong();
		action = objectInput.readUTF();
		moduleName = objectInput.readUTF();

		createdBy = objectInput.readLong();

		modifiedBy = objectInput.readLong();

		groupId = objectInput.readLong();

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

		objectOutput.writeLong(auditLogId);

		if (action == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(action);
		}

		if (moduleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		objectOutput.writeLong(createdBy);

		objectOutput.writeLong(modifiedBy);

		objectOutput.writeLong(groupId);

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
	public long auditLogId;
	public String action;
	public String moduleName;
	public long createdBy;
	public long modifiedBy;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;

}