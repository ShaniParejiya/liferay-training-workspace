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

import com.aixtor.training.liferay.model.Department;
import com.aixtor.training.liferay.model.DepartmentModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Department service. Represents a row in the &quot;AIXTOR_Department&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DepartmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DepartmentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DepartmentImpl
 * @generated
 */
@JSON(strict = true)
public class DepartmentModelImpl
	extends BaseModelImpl<Department> implements DepartmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a department model instance should use the <code>Department</code> interface instead.
	 */
	public static final String TABLE_NAME = "AIXTOR_Department";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"departmentId", Types.BIGINT},
		{"departmentName", Types.VARCHAR}, {"departmentHead", Types.VARCHAR},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("departmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("departmentName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("departmentHead", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AIXTOR_Department (uuid_ VARCHAR(75) null,departmentId LONG not null primary key,departmentName VARCHAR(75) null,departmentHead VARCHAR(75) null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table AIXTOR_Department";

	public static final String ORDER_BY_JPQL =
		" ORDER BY department.departmentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AIXTOR_Department.departmentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEPARTMENTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public DepartmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _departmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDepartmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _departmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Department.class;
	}

	@Override
	public String getModelClassName() {
		return Department.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Department, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Department, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Department, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Department)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Department, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Department, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Department)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Department, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Department, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Department, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Department, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Department, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Department, Object>>();
		Map<String, BiConsumer<Department, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Department, ?>>();

		attributeGetterFunctions.put("uuid", Department::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Department, String>)Department::setUuid);
		attributeGetterFunctions.put(
			"departmentId", Department::getDepartmentId);
		attributeSetterBiConsumers.put(
			"departmentId",
			(BiConsumer<Department, Long>)Department::setDepartmentId);
		attributeGetterFunctions.put(
			"departmentName", Department::getDepartmentName);
		attributeSetterBiConsumers.put(
			"departmentName",
			(BiConsumer<Department, String>)Department::setDepartmentName);
		attributeGetterFunctions.put(
			"departmentHead", Department::getDepartmentHead);
		attributeSetterBiConsumers.put(
			"departmentHead",
			(BiConsumer<Department, String>)Department::setDepartmentHead);
		attributeGetterFunctions.put("companyId", Department::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<Department, Long>)Department::setCompanyId);
		attributeGetterFunctions.put("userId", Department::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Department, Long>)Department::setUserId);
		attributeGetterFunctions.put("userName", Department::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<Department, String>)Department::setUserName);
		attributeGetterFunctions.put("createDate", Department::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<Department, Date>)Department::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", Department::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Department, Date>)Department::setModifiedDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getDepartmentId() {
		return _departmentId;
	}

	@Override
	public void setDepartmentId(long departmentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_departmentId = departmentId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDepartmentId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("departmentId"));
	}

	@JSON
	@Override
	public String getDepartmentName() {
		if (_departmentName == null) {
			return "";
		}
		else {
			return _departmentName;
		}
	}

	@Override
	public void setDepartmentName(String departmentName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_departmentName = departmentName;
	}

	@JSON
	@Override
	public String getDepartmentHead() {
		if (_departmentHead == null) {
			return "";
		}
		else {
			return _departmentHead;
		}
	}

	@Override
	public void setDepartmentHead(String departmentHead) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_departmentHead = departmentHead;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Department.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Department.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Department toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Department>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DepartmentImpl departmentImpl = new DepartmentImpl();

		departmentImpl.setUuid(getUuid());
		departmentImpl.setDepartmentId(getDepartmentId());
		departmentImpl.setDepartmentName(getDepartmentName());
		departmentImpl.setDepartmentHead(getDepartmentHead());
		departmentImpl.setCompanyId(getCompanyId());
		departmentImpl.setUserId(getUserId());
		departmentImpl.setUserName(getUserName());
		departmentImpl.setCreateDate(getCreateDate());
		departmentImpl.setModifiedDate(getModifiedDate());

		departmentImpl.resetOriginalValues();

		return departmentImpl;
	}

	@Override
	public Department cloneWithOriginalValues() {
		DepartmentImpl departmentImpl = new DepartmentImpl();

		departmentImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		departmentImpl.setDepartmentId(
			this.<Long>getColumnOriginalValue("departmentId"));
		departmentImpl.setDepartmentName(
			this.<String>getColumnOriginalValue("departmentName"));
		departmentImpl.setDepartmentHead(
			this.<String>getColumnOriginalValue("departmentHead"));
		departmentImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		departmentImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		departmentImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		departmentImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		departmentImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));

		return departmentImpl;
	}

	@Override
	public int compareTo(Department department) {
		int value = 0;

		if (getDepartmentId() < department.getDepartmentId()) {
			value = -1;
		}
		else if (getDepartmentId() > department.getDepartmentId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Department)) {
			return false;
		}

		Department department = (Department)object;

		long primaryKey = department.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Department> toCacheModel() {
		DepartmentCacheModel departmentCacheModel = new DepartmentCacheModel();

		departmentCacheModel.uuid = getUuid();

		String uuid = departmentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			departmentCacheModel.uuid = null;
		}

		departmentCacheModel.departmentId = getDepartmentId();

		departmentCacheModel.departmentName = getDepartmentName();

		String departmentName = departmentCacheModel.departmentName;

		if ((departmentName != null) && (departmentName.length() == 0)) {
			departmentCacheModel.departmentName = null;
		}

		departmentCacheModel.departmentHead = getDepartmentHead();

		String departmentHead = departmentCacheModel.departmentHead;

		if ((departmentHead != null) && (departmentHead.length() == 0)) {
			departmentCacheModel.departmentHead = null;
		}

		departmentCacheModel.companyId = getCompanyId();

		departmentCacheModel.userId = getUserId();

		departmentCacheModel.userName = getUserName();

		String userName = departmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			departmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			departmentCacheModel.createDate = createDate.getTime();
		}
		else {
			departmentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			departmentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			departmentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return departmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Department, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Department, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Department, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Department)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Department>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Department.class, ModelWrapper.class);

	}

	private String _uuid;
	private long _departmentId;
	private String _departmentName;
	private String _departmentHead;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Department, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Department)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("departmentId", _departmentId);
		_columnOriginalValues.put("departmentName", _departmentName);
		_columnOriginalValues.put("departmentHead", _departmentHead);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("departmentId", 2L);

		columnBitmasks.put("departmentName", 4L);

		columnBitmasks.put("departmentHead", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Department _escapedModel;

}