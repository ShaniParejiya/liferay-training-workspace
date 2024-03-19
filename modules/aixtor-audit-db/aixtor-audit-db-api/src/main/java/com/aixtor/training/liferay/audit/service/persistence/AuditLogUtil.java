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

package com.aixtor.training.liferay.audit.service.persistence;

import com.aixtor.training.liferay.audit.model.AuditLog;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the audit log service. This utility wraps <code>com.aixtor.training.liferay.audit.service.persistence.impl.AuditLogPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditLogPersistence
 * @generated
 */
public class AuditLogUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AuditLog auditLog) {
		getPersistence().clearCache(auditLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AuditLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditLog update(AuditLog auditLog) {
		return getPersistence().update(auditLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditLog update(
		AuditLog auditLog, ServiceContext serviceContext) {

		return getPersistence().update(auditLog, serviceContext);
	}

	/**
	 * Returns all the audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching audit logs
	 */
	public static List<AuditLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the audit logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of matching audit logs
	 */
	public static List<AuditLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the audit logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByUuid_First(
			String uuid, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUuid_First(
		String uuid, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByUuid_Last(
			String uuid, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUuid_Last(
		String uuid, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where uuid = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog[] findByUuid_PrevAndNext(
			long auditLogId, String uuid,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_PrevAndNext(
			auditLogId, uuid, orderByComparator);
	}

	/**
	 * Removes all the audit logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching audit logs
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByUUID_G(String uuid, long groupId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the audit log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the audit log that was removed
	 */
	public static AuditLog removeByUUID_G(String uuid, long groupId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of audit logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching audit logs
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching audit logs
	 */
	public static List<AuditLog> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of matching audit logs
	 */
	public static List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog[] findByUuid_C_PrevAndNext(
			long auditLogId, String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByUuid_C_PrevAndNext(
			auditLogId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the audit logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching audit logs
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the audit log where auditLogId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByGetAuditDataByAuditLogId(long auditLogId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByAuditLogId(auditLogId);
	}

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByAuditLogId(long auditLogId) {
		return getPersistence().fetchByGetAuditDataByAuditLogId(auditLogId);
	}

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByAuditLogId(
		long auditLogId, boolean useFinderCache) {

		return getPersistence().fetchByGetAuditDataByAuditLogId(
			auditLogId, useFinderCache);
	}

	/**
	 * Removes the audit log where auditLogId = &#63; from the database.
	 *
	 * @param auditLogId the audit log ID
	 * @return the audit log that was removed
	 */
	public static AuditLog removeByGetAuditDataByAuditLogId(long auditLogId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().removeByGetAuditDataByAuditLogId(auditLogId);
	}

	/**
	 * Returns the number of audit logs where auditLogId = &#63;.
	 *
	 * @param auditLogId the audit log ID
	 * @return the number of matching audit logs
	 */
	public static int countByGetAuditDataByAuditLogId(long auditLogId) {
		return getPersistence().countByGetAuditDataByAuditLogId(auditLogId);
	}

	/**
	 * Returns all the audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByAction(String action) {
		return getPersistence().findByGetAuditDataByAction(action);
	}

	/**
	 * Returns a range of all the audit logs where action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param action the action
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end) {

		return getPersistence().findByGetAuditDataByAction(action, start, end);
	}

	/**
	 * Returns an ordered range of all the audit logs where action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param action the action
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findByGetAuditDataByAction(
			action, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit logs where action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param action the action
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGetAuditDataByAction(
			action, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByGetAuditDataByAction_First(
			String action, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByAction_First(
			action, orderByComparator);
	}

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByAction_First(
		String action, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByGetAuditDataByAction_First(
			action, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByGetAuditDataByAction_Last(
			String action, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByAction_Last(
			action, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByAction_Last(
		String action, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByGetAuditDataByAction_Last(
			action, orderByComparator);
	}

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where action = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog[] findByGetAuditDataByAction_PrevAndNext(
			long auditLogId, String action,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByAction_PrevAndNext(
			auditLogId, action, orderByComparator);
	}

	/**
	 * Removes all the audit logs where action = &#63; from the database.
	 *
	 * @param action the action
	 */
	public static void removeByGetAuditDataByAction(String action) {
		getPersistence().removeByGetAuditDataByAction(action);
	}

	/**
	 * Returns the number of audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the number of matching audit logs
	 */
	public static int countByGetAuditDataByAction(String action) {
		return getPersistence().countByGetAuditDataByAction(action);
	}

	/**
	 * Returns all the audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName) {

		return getPersistence().findByGetAuditDataByModuleName(moduleName);
	}

	/**
	 * Returns a range of all the audit logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end) {

		return getPersistence().findByGetAuditDataByModuleName(
			moduleName, start, end);
	}

	/**
	 * Returns an ordered range of all the audit logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findByGetAuditDataByModuleName(
			moduleName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit logs
	 */
	public static List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGetAuditDataByModuleName(
			moduleName, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByGetAuditDataByModuleName_First(
			String moduleName, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByModuleName_First(
			moduleName, orderByComparator);
	}

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByModuleName_First(
		String moduleName, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByGetAuditDataByModuleName_First(
			moduleName, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public static AuditLog findByGetAuditDataByModuleName_Last(
			String moduleName, OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByModuleName_Last(
			moduleName, orderByComparator);
	}

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public static AuditLog fetchByGetAuditDataByModuleName_Last(
		String moduleName, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().fetchByGetAuditDataByModuleName_Last(
			moduleName, orderByComparator);
	}

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog[] findByGetAuditDataByModuleName_PrevAndNext(
			long auditLogId, String moduleName,
			OrderByComparator<AuditLog> orderByComparator)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByGetAuditDataByModuleName_PrevAndNext(
			auditLogId, moduleName, orderByComparator);
	}

	/**
	 * Removes all the audit logs where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	public static void removeByGetAuditDataByModuleName(String moduleName) {
		getPersistence().removeByGetAuditDataByModuleName(moduleName);
	}

	/**
	 * Returns the number of audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the number of matching audit logs
	 */
	public static int countByGetAuditDataByModuleName(String moduleName) {
		return getPersistence().countByGetAuditDataByModuleName(moduleName);
	}

	/**
	 * Caches the audit log in the entity cache if it is enabled.
	 *
	 * @param auditLog the audit log
	 */
	public static void cacheResult(AuditLog auditLog) {
		getPersistence().cacheResult(auditLog);
	}

	/**
	 * Caches the audit logs in the entity cache if it is enabled.
	 *
	 * @param auditLogs the audit logs
	 */
	public static void cacheResult(List<AuditLog> auditLogs) {
		getPersistence().cacheResult(auditLogs);
	}

	/**
	 * Creates a new audit log with the primary key. Does not add the audit log to the database.
	 *
	 * @param auditLogId the primary key for the new audit log
	 * @return the new audit log
	 */
	public static AuditLog create(long auditLogId) {
		return getPersistence().create(auditLogId);
	}

	/**
	 * Removes the audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log that was removed
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog remove(long auditLogId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().remove(auditLogId);
	}

	public static AuditLog updateImpl(AuditLog auditLog) {
		return getPersistence().updateImpl(auditLog);
	}

	/**
	 * Returns the audit log with the primary key or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public static AuditLog findByPrimaryKey(long auditLogId)
		throws com.aixtor.training.liferay.audit.exception.
			NoSuchAuditLogException {

		return getPersistence().findByPrimaryKey(auditLogId);
	}

	/**
	 * Returns the audit log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log, or <code>null</code> if a audit log with the primary key could not be found
	 */
	public static AuditLog fetchByPrimaryKey(long auditLogId) {
		return getPersistence().fetchByPrimaryKey(auditLogId);
	}

	/**
	 * Returns all the audit logs.
	 *
	 * @return the audit logs
	 */
	public static List<AuditLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of audit logs
	 */
	public static List<AuditLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit logs
	 */
	public static List<AuditLog> findAll(
		int start, int end, OrderByComparator<AuditLog> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of audit logs
	 */
	public static List<AuditLog> findAll(
		int start, int end, OrderByComparator<AuditLog> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the audit logs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of audit logs.
	 *
	 * @return the number of audit logs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuditLogPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AuditLogPersistence _persistence;

}