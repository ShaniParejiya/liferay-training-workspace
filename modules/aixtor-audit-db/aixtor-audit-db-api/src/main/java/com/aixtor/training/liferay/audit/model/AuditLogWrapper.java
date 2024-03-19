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

package com.aixtor.training.liferay.audit.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuditLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditLog
 * @generated
 */
public class AuditLogWrapper
	extends BaseModelWrapper<AuditLog>
	implements AuditLog, ModelWrapper<AuditLog> {

	public AuditLogWrapper(AuditLog auditLog) {
		super(auditLog);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("auditLogId", getAuditLogId());
		attributes.put("action", getAction());
		attributes.put("moduleName", getModuleName());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long auditLogId = (Long)attributes.get("auditLogId");

		if (auditLogId != null) {
			setAuditLogId(auditLogId);
		}

		String action = (String)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		Long createdBy = (Long)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Long modifiedBy = (Long)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public AuditLog cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the action of this audit log.
	 *
	 * @return the action of this audit log
	 */
	@Override
	public String getAction() {
		return model.getAction();
	}

	/**
	 * Returns the audit log ID of this audit log.
	 *
	 * @return the audit log ID of this audit log
	 */
	@Override
	public long getAuditLogId() {
		return model.getAuditLogId();
	}

	/**
	 * Returns the company ID of this audit log.
	 *
	 * @return the company ID of this audit log
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this audit log.
	 *
	 * @return the create date of this audit log
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the created by of this audit log.
	 *
	 * @return the created by of this audit log
	 */
	@Override
	public long getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the group ID of this audit log.
	 *
	 * @return the group ID of this audit log
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified by of this audit log.
	 *
	 * @return the modified by of this audit log
	 */
	@Override
	public long getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this audit log.
	 *
	 * @return the modified date of this audit log
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the module name of this audit log.
	 *
	 * @return the module name of this audit log
	 */
	@Override
	public String getModuleName() {
		return model.getModuleName();
	}

	/**
	 * Returns the primary key of this audit log.
	 *
	 * @return the primary key of this audit log
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this audit log.
	 *
	 * @return the user ID of this audit log
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this audit log.
	 *
	 * @return the user name of this audit log
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this audit log.
	 *
	 * @return the user uuid of this audit log
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this audit log.
	 *
	 * @return the uuid of this audit log
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action of this audit log.
	 *
	 * @param action the action of this audit log
	 */
	@Override
	public void setAction(String action) {
		model.setAction(action);
	}

	/**
	 * Sets the audit log ID of this audit log.
	 *
	 * @param auditLogId the audit log ID of this audit log
	 */
	@Override
	public void setAuditLogId(long auditLogId) {
		model.setAuditLogId(auditLogId);
	}

	/**
	 * Sets the company ID of this audit log.
	 *
	 * @param companyId the company ID of this audit log
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this audit log.
	 *
	 * @param createDate the create date of this audit log
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the created by of this audit log.
	 *
	 * @param createdBy the created by of this audit log
	 */
	@Override
	public void setCreatedBy(long createdBy) {
		model.setCreatedBy(createdBy);
	}

	/**
	 * Sets the group ID of this audit log.
	 *
	 * @param groupId the group ID of this audit log
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified by of this audit log.
	 *
	 * @param modifiedBy the modified by of this audit log
	 */
	@Override
	public void setModifiedBy(long modifiedBy) {
		model.setModifiedBy(modifiedBy);
	}

	/**
	 * Sets the modified date of this audit log.
	 *
	 * @param modifiedDate the modified date of this audit log
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the module name of this audit log.
	 *
	 * @param moduleName the module name of this audit log
	 */
	@Override
	public void setModuleName(String moduleName) {
		model.setModuleName(moduleName);
	}

	/**
	 * Sets the primary key of this audit log.
	 *
	 * @param primaryKey the primary key of this audit log
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this audit log.
	 *
	 * @param userId the user ID of this audit log
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this audit log.
	 *
	 * @param userName the user name of this audit log
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this audit log.
	 *
	 * @param userUuid the user uuid of this audit log
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this audit log.
	 *
	 * @param uuid the uuid of this audit log
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected AuditLogWrapper wrap(AuditLog auditLog) {
		return new AuditLogWrapper(auditLog);
	}

}