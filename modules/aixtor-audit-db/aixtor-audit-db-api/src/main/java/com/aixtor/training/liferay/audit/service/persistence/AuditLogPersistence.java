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

import com.aixtor.training.liferay.audit.exception.NoSuchAuditLogException;
import com.aixtor.training.liferay.audit.model.AuditLog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the audit log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditLogUtil
 * @generated
 */
@ProviderType
public interface AuditLogPersistence extends BasePersistence<AuditLog> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditLogUtil} to access the audit log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching audit logs
	 */
	public java.util.List<AuditLog> findByUuid(String uuid);

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
	public java.util.List<AuditLog> findByUuid(String uuid, int start, int end);

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
	public java.util.List<AuditLog> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public java.util.List<AuditLog> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where uuid = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public AuditLog[] findByUuid_PrevAndNext(
			long auditLogId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Removes all the audit logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching audit logs
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByUUID_G(String uuid, long groupId)
		throws NoSuchAuditLogException;

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the audit log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the audit log that was removed
	 */
	public AuditLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchAuditLogException;

	/**
	 * Returns the number of audit logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching audit logs
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching audit logs
	 */
	public java.util.List<AuditLog> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public java.util.List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public AuditLog[] findByUuid_C_PrevAndNext(
			long auditLogId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Removes all the audit logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching audit logs
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the audit log where auditLogId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByGetAuditDataByAuditLogId(long auditLogId)
		throws NoSuchAuditLogException;

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByAuditLogId(long auditLogId);

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByAuditLogId(
		long auditLogId, boolean useFinderCache);

	/**
	 * Removes the audit log where auditLogId = &#63; from the database.
	 *
	 * @param auditLogId the audit log ID
	 * @return the audit log that was removed
	 */
	public AuditLog removeByGetAuditDataByAuditLogId(long auditLogId)
		throws NoSuchAuditLogException;

	/**
	 * Returns the number of audit logs where auditLogId = &#63;.
	 *
	 * @param auditLogId the audit log ID
	 * @return the number of matching audit logs
	 */
	public int countByGetAuditDataByAuditLogId(long auditLogId);

	/**
	 * Returns all the audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the matching audit logs
	 */
	public java.util.List<AuditLog> findByGetAuditDataByAction(String action);

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
	public java.util.List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end);

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
	public java.util.List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public java.util.List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByGetAuditDataByAction_First(
			String action,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByAction_First(
		String action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByGetAuditDataByAction_Last(
			String action,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByAction_Last(
		String action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where action = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public AuditLog[] findByGetAuditDataByAction_PrevAndNext(
			long auditLogId, String action,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Removes all the audit logs where action = &#63; from the database.
	 *
	 * @param action the action
	 */
	public void removeByGetAuditDataByAction(String action);

	/**
	 * Returns the number of audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the number of matching audit logs
	 */
	public int countByGetAuditDataByAction(String action);

	/**
	 * Returns all the audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching audit logs
	 */
	public java.util.List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName);

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
	public java.util.List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end);

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
	public java.util.List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public java.util.List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByGetAuditDataByModuleName_First(
			String moduleName,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByModuleName_First(
		String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	public AuditLog findByGetAuditDataByModuleName_Last(
			String moduleName,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	public AuditLog fetchByGetAuditDataByModuleName_Last(
		String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

	/**
	 * Returns the audit logs before and after the current audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param auditLogId the primary key of the current audit log
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public AuditLog[] findByGetAuditDataByModuleName_PrevAndNext(
			long auditLogId, String moduleName,
			com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
				orderByComparator)
		throws NoSuchAuditLogException;

	/**
	 * Removes all the audit logs where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	public void removeByGetAuditDataByModuleName(String moduleName);

	/**
	 * Returns the number of audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the number of matching audit logs
	 */
	public int countByGetAuditDataByModuleName(String moduleName);

	/**
	 * Caches the audit log in the entity cache if it is enabled.
	 *
	 * @param auditLog the audit log
	 */
	public void cacheResult(AuditLog auditLog);

	/**
	 * Caches the audit logs in the entity cache if it is enabled.
	 *
	 * @param auditLogs the audit logs
	 */
	public void cacheResult(java.util.List<AuditLog> auditLogs);

	/**
	 * Creates a new audit log with the primary key. Does not add the audit log to the database.
	 *
	 * @param auditLogId the primary key for the new audit log
	 * @return the new audit log
	 */
	public AuditLog create(long auditLogId);

	/**
	 * Removes the audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log that was removed
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public AuditLog remove(long auditLogId) throws NoSuchAuditLogException;

	public AuditLog updateImpl(AuditLog auditLog);

	/**
	 * Returns the audit log with the primary key or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	public AuditLog findByPrimaryKey(long auditLogId)
		throws NoSuchAuditLogException;

	/**
	 * Returns the audit log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log, or <code>null</code> if a audit log with the primary key could not be found
	 */
	public AuditLog fetchByPrimaryKey(long auditLogId);

	/**
	 * Returns all the audit logs.
	 *
	 * @return the audit logs
	 */
	public java.util.List<AuditLog> findAll();

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
	public java.util.List<AuditLog> findAll(int start, int end);

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
	public java.util.List<AuditLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator);

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
	public java.util.List<AuditLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the audit logs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of audit logs.
	 *
	 * @return the number of audit logs
	 */
	public int countAll();

}