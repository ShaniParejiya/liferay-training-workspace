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

package com.aixtor.training.liferay.audit.service.persistence.impl;

import com.aixtor.training.liferay.audit.exception.NoSuchAuditLogException;
import com.aixtor.training.liferay.audit.model.AuditLog;
import com.aixtor.training.liferay.audit.model.AuditLogTable;
import com.aixtor.training.liferay.audit.model.impl.AuditLogImpl;
import com.aixtor.training.liferay.audit.model.impl.AuditLogModelImpl;
import com.aixtor.training.liferay.audit.service.persistence.AuditLogPersistence;
import com.aixtor.training.liferay.audit.service.persistence.AuditLogUtil;
import com.aixtor.training.liferay.audit.service.persistence.impl.constants.AIXTOR_DBPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the audit log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {AuditLogPersistence.class, BasePersistence.class})
public class AuditLogPersistenceImpl
	extends BasePersistenceImpl<AuditLog> implements AuditLogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AuditLogUtil</code> to access the audit log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AuditLogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching audit logs
	 */
	@Override
	public List<AuditLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AuditLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<AuditLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<AuditLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<AuditLog> list = null;

		if (useFinderCache) {
			list = (List<AuditLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditLog auditLog : list) {
					if (!uuid.equals(auditLog.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<AuditLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByUuid_First(
			String uuid, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByUuid_First(uuid, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUuid_First(
		String uuid, OrderByComparator<AuditLog> orderByComparator) {

		List<AuditLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByUuid_Last(
			String uuid, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByUuid_Last(uuid, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUuid_Last(
		String uuid, OrderByComparator<AuditLog> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AuditLog> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AuditLog[] findByUuid_PrevAndNext(
			long auditLogId, String uuid,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		uuid = Objects.toString(uuid, "");

		AuditLog auditLog = findByPrimaryKey(auditLogId);

		Session session = null;

		try {
			session = openSession();

			AuditLog[] array = new AuditLogImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, auditLog, uuid, orderByComparator, true);

			array[1] = auditLog;

			array[2] = getByUuid_PrevAndNext(
				session, auditLog, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditLog getByUuid_PrevAndNext(
		Session session, AuditLog auditLog, String uuid,
		OrderByComparator<AuditLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AUDITLOG_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(auditLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AuditLog auditLog :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(auditLog);
		}
	}

	/**
	 * Returns the number of audit logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"auditLog.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(auditLog.uuid IS NULL OR auditLog.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByUUID_G(String uuid, long groupId)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByUUID_G(uuid, groupId);

		if (auditLog == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAuditLogException(sb.toString());
		}

		return auditLog;
	}

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the audit log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof AuditLog) {
			AuditLog auditLog = (AuditLog)result;

			if (!Objects.equals(uuid, auditLog.getUuid()) ||
				(groupId != auditLog.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<AuditLog> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					AuditLog auditLog = list.get(0);

					result = auditLog;

					cacheResult(auditLog);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AuditLog)result;
		}
	}

	/**
	 * Removes the audit log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the audit log that was removed
	 */
	@Override
	public AuditLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchAuditLogException {

		AuditLog auditLog = findByUUID_G(uuid, groupId);

		return remove(auditLog);
	}

	/**
	 * Returns the number of audit logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"auditLog.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(auditLog.uuid IS NULL OR auditLog.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"auditLog.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching audit logs
	 */
	@Override
	public List<AuditLog> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<AuditLog> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<AuditLog> list = null;

		if (useFinderCache) {
			list = (List<AuditLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditLog auditLog : list) {
					if (!uuid.equals(auditLog.getUuid()) ||
						(companyId != auditLog.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<AuditLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AuditLog findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the first audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AuditLog> orderByComparator) {

		List<AuditLog> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AuditLog findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the last audit log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AuditLog> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AuditLog> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AuditLog[] findByUuid_C_PrevAndNext(
			long auditLogId, String uuid, long companyId,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		uuid = Objects.toString(uuid, "");

		AuditLog auditLog = findByPrimaryKey(auditLogId);

		Session session = null;

		try {
			session = openSession();

			AuditLog[] array = new AuditLogImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, auditLog, uuid, companyId, orderByComparator, true);

			array[1] = auditLog;

			array[2] = getByUuid_C_PrevAndNext(
				session, auditLog, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditLog getByUuid_C_PrevAndNext(
		Session session, AuditLog auditLog, String uuid, long companyId,
		OrderByComparator<AuditLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_AUDITLOG_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(auditLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AuditLog auditLog :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(auditLog);
		}
	}

	/**
	 * Returns the number of audit logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"auditLog.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(auditLog.uuid IS NULL OR auditLog.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"auditLog.companyId = ?";

	private FinderPath _finderPathFetchByGetAuditDataByAuditLogId;
	private FinderPath _finderPathCountByGetAuditDataByAuditLogId;

	/**
	 * Returns the audit log where auditLogId = &#63; or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByGetAuditDataByAuditLogId(long auditLogId)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByGetAuditDataByAuditLogId(auditLogId);

		if (auditLog == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("auditLogId=");
			sb.append(auditLogId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAuditLogException(sb.toString());
		}

		return auditLog;
	}

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByAuditLogId(long auditLogId) {
		return fetchByGetAuditDataByAuditLogId(auditLogId, true);
	}

	/**
	 * Returns the audit log where auditLogId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditLogId the audit log ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByAuditLogId(
		long auditLogId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {auditLogId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByGetAuditDataByAuditLogId, finderArgs, this);
		}

		if (result instanceof AuditLog) {
			AuditLog auditLog = (AuditLog)result;

			if (auditLogId != auditLog.getAuditLogId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			sb.append(_FINDER_COLUMN_GETAUDITDATABYAUDITLOGID_AUDITLOGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(auditLogId);

				List<AuditLog> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByGetAuditDataByAuditLogId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {auditLogId};
							}

							_log.warn(
								"AuditLogPersistenceImpl.fetchByGetAuditDataByAuditLogId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AuditLog auditLog = list.get(0);

					result = auditLog;

					cacheResult(auditLog);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AuditLog)result;
		}
	}

	/**
	 * Removes the audit log where auditLogId = &#63; from the database.
	 *
	 * @param auditLogId the audit log ID
	 * @return the audit log that was removed
	 */
	@Override
	public AuditLog removeByGetAuditDataByAuditLogId(long auditLogId)
		throws NoSuchAuditLogException {

		AuditLog auditLog = findByGetAuditDataByAuditLogId(auditLogId);

		return remove(auditLog);
	}

	/**
	 * Returns the number of audit logs where auditLogId = &#63;.
	 *
	 * @param auditLogId the audit log ID
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByGetAuditDataByAuditLogId(long auditLogId) {
		FinderPath finderPath = _finderPathCountByGetAuditDataByAuditLogId;

		Object[] finderArgs = new Object[] {auditLogId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			sb.append(_FINDER_COLUMN_GETAUDITDATABYAUDITLOGID_AUDITLOGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(auditLogId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_GETAUDITDATABYAUDITLOGID_AUDITLOGID_2 =
			"auditLog.auditLogId = ?";

	private FinderPath _finderPathWithPaginationFindByGetAuditDataByAction;
	private FinderPath _finderPathWithoutPaginationFindByGetAuditDataByAction;
	private FinderPath _finderPathCountByGetAuditDataByAction;

	/**
	 * Returns all the audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the matching audit logs
	 */
	@Override
	public List<AuditLog> findByGetAuditDataByAction(String action) {
		return findByGetAuditDataByAction(
			action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end) {

		return findByGetAuditDataByAction(action, start, end, null);
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
	@Override
	public List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return findByGetAuditDataByAction(
			action, start, end, orderByComparator, true);
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
	@Override
	public List<AuditLog> findByGetAuditDataByAction(
		String action, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		action = Objects.toString(action, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByGetAuditDataByAction;
				finderArgs = new Object[] {action};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGetAuditDataByAction;
			finderArgs = new Object[] {action, start, end, orderByComparator};
		}

		List<AuditLog> list = null;

		if (useFinderCache) {
			list = (List<AuditLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditLog auditLog : list) {
					if (!action.equals(auditLog.getAction())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			boolean bindAction = false;

			if (action.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_3);
			}
			else {
				bindAction = true;

				sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAction) {
					queryPos.add(action);
				}

				list = (List<AuditLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByGetAuditDataByAction_First(
			String action, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByGetAuditDataByAction_First(
			action, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("action=");
		sb.append(action);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the first audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByAction_First(
		String action, OrderByComparator<AuditLog> orderByComparator) {

		List<AuditLog> list = findByGetAuditDataByAction(
			action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByGetAuditDataByAction_Last(
			String action, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByGetAuditDataByAction_Last(
			action, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("action=");
		sb.append(action);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the last audit log in the ordered set where action = &#63;.
	 *
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByAction_Last(
		String action, OrderByComparator<AuditLog> orderByComparator) {

		int count = countByGetAuditDataByAction(action);

		if (count == 0) {
			return null;
		}

		List<AuditLog> list = findByGetAuditDataByAction(
			action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AuditLog[] findByGetAuditDataByAction_PrevAndNext(
			long auditLogId, String action,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		action = Objects.toString(action, "");

		AuditLog auditLog = findByPrimaryKey(auditLogId);

		Session session = null;

		try {
			session = openSession();

			AuditLog[] array = new AuditLogImpl[3];

			array[0] = getByGetAuditDataByAction_PrevAndNext(
				session, auditLog, action, orderByComparator, true);

			array[1] = auditLog;

			array[2] = getByGetAuditDataByAction_PrevAndNext(
				session, auditLog, action, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditLog getByGetAuditDataByAction_PrevAndNext(
		Session session, AuditLog auditLog, String action,
		OrderByComparator<AuditLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AUDITLOG_WHERE);

		boolean bindAction = false;

		if (action.isEmpty()) {
			sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_3);
		}
		else {
			bindAction = true;

			sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAction) {
			queryPos.add(action);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(auditLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit logs where action = &#63; from the database.
	 *
	 * @param action the action
	 */
	@Override
	public void removeByGetAuditDataByAction(String action) {
		for (AuditLog auditLog :
				findByGetAuditDataByAction(
					action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(auditLog);
		}
	}

	/**
	 * Returns the number of audit logs where action = &#63;.
	 *
	 * @param action the action
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByGetAuditDataByAction(String action) {
		action = Objects.toString(action, "");

		FinderPath finderPath = _finderPathCountByGetAuditDataByAction;

		Object[] finderArgs = new Object[] {action};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			boolean bindAction = false;

			if (action.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_3);
			}
			else {
				bindAction = true;

				sb.append(_FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAction) {
					queryPos.add(action);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_2 =
		"auditLog.action = ?";

	private static final String _FINDER_COLUMN_GETAUDITDATABYACTION_ACTION_3 =
		"(auditLog.action IS NULL OR auditLog.action = '')";

	private FinderPath _finderPathWithPaginationFindByGetAuditDataByModuleName;
	private FinderPath
		_finderPathWithoutPaginationFindByGetAuditDataByModuleName;
	private FinderPath _finderPathCountByGetAuditDataByModuleName;

	/**
	 * Returns all the audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching audit logs
	 */
	@Override
	public List<AuditLog> findByGetAuditDataByModuleName(String moduleName) {
		return findByGetAuditDataByModuleName(
			moduleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end) {

		return findByGetAuditDataByModuleName(moduleName, start, end, null);
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
	@Override
	public List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		OrderByComparator<AuditLog> orderByComparator) {

		return findByGetAuditDataByModuleName(
			moduleName, start, end, orderByComparator, true);
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
	@Override
	public List<AuditLog> findByGetAuditDataByModuleName(
		String moduleName, int start, int end,
		OrderByComparator<AuditLog> orderByComparator, boolean useFinderCache) {

		moduleName = Objects.toString(moduleName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByGetAuditDataByModuleName;
				finderArgs = new Object[] {moduleName};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByGetAuditDataByModuleName;
			finderArgs = new Object[] {
				moduleName, start, end, orderByComparator
			};
		}

		List<AuditLog> list = null;

		if (useFinderCache) {
			list = (List<AuditLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditLog auditLog : list) {
					if (!moduleName.equals(auditLog.getModuleName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_AUDITLOG_WHERE);

			boolean bindModuleName = false;

			if (moduleName.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindModuleName) {
					queryPos.add(moduleName);
				}

				list = (List<AuditLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByGetAuditDataByModuleName_First(
			String moduleName, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByGetAuditDataByModuleName_First(
			moduleName, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("moduleName=");
		sb.append(moduleName);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the first audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByModuleName_First(
		String moduleName, OrderByComparator<AuditLog> orderByComparator) {

		List<AuditLog> list = findByGetAuditDataByModuleName(
			moduleName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log
	 * @throws NoSuchAuditLogException if a matching audit log could not be found
	 */
	@Override
	public AuditLog findByGetAuditDataByModuleName_Last(
			String moduleName, OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByGetAuditDataByModuleName_Last(
			moduleName, orderByComparator);

		if (auditLog != null) {
			return auditLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("moduleName=");
		sb.append(moduleName);

		sb.append("}");

		throw new NoSuchAuditLogException(sb.toString());
	}

	/**
	 * Returns the last audit log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public AuditLog fetchByGetAuditDataByModuleName_Last(
		String moduleName, OrderByComparator<AuditLog> orderByComparator) {

		int count = countByGetAuditDataByModuleName(moduleName);

		if (count == 0) {
			return null;
		}

		List<AuditLog> list = findByGetAuditDataByModuleName(
			moduleName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AuditLog[] findByGetAuditDataByModuleName_PrevAndNext(
			long auditLogId, String moduleName,
			OrderByComparator<AuditLog> orderByComparator)
		throws NoSuchAuditLogException {

		moduleName = Objects.toString(moduleName, "");

		AuditLog auditLog = findByPrimaryKey(auditLogId);

		Session session = null;

		try {
			session = openSession();

			AuditLog[] array = new AuditLogImpl[3];

			array[0] = getByGetAuditDataByModuleName_PrevAndNext(
				session, auditLog, moduleName, orderByComparator, true);

			array[1] = auditLog;

			array[2] = getByGetAuditDataByModuleName_PrevAndNext(
				session, auditLog, moduleName, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditLog getByGetAuditDataByModuleName_PrevAndNext(
		Session session, AuditLog auditLog, String moduleName,
		OrderByComparator<AuditLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AUDITLOG_WHERE);

		boolean bindModuleName = false;

		if (moduleName.isEmpty()) {
			sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_3);
		}
		else {
			bindModuleName = true;

			sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AuditLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindModuleName) {
			queryPos.add(moduleName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(auditLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit logs where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	@Override
	public void removeByGetAuditDataByModuleName(String moduleName) {
		for (AuditLog auditLog :
				findByGetAuditDataByModuleName(
					moduleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(auditLog);
		}
	}

	/**
	 * Returns the number of audit logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the number of matching audit logs
	 */
	@Override
	public int countByGetAuditDataByModuleName(String moduleName) {
		moduleName = Objects.toString(moduleName, "");

		FinderPath finderPath = _finderPathCountByGetAuditDataByModuleName;

		Object[] finderArgs = new Object[] {moduleName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUDITLOG_WHERE);

			boolean bindModuleName = false;

			if (moduleName.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				sb.append(_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindModuleName) {
					queryPos.add(moduleName);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_2 =
			"auditLog.moduleName = ?";

	private static final String
		_FINDER_COLUMN_GETAUDITDATABYMODULENAME_MODULENAME_3 =
			"(auditLog.moduleName IS NULL OR auditLog.moduleName = '')";

	public AuditLogPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(AuditLog.class);

		setModelImplClass(AuditLogImpl.class);
		setModelPKClass(long.class);

		setTable(AuditLogTable.INSTANCE);
	}

	/**
	 * Caches the audit log in the entity cache if it is enabled.
	 *
	 * @param auditLog the audit log
	 */
	@Override
	public void cacheResult(AuditLog auditLog) {
		entityCache.putResult(
			AuditLogImpl.class, auditLog.getPrimaryKey(), auditLog);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {auditLog.getUuid(), auditLog.getGroupId()}, auditLog);

		finderCache.putResult(
			_finderPathFetchByGetAuditDataByAuditLogId,
			new Object[] {auditLog.getAuditLogId()}, auditLog);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the audit logs in the entity cache if it is enabled.
	 *
	 * @param auditLogs the audit logs
	 */
	@Override
	public void cacheResult(List<AuditLog> auditLogs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (auditLogs.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AuditLog auditLog : auditLogs) {
			if (entityCache.getResult(
					AuditLogImpl.class, auditLog.getPrimaryKey()) == null) {

				cacheResult(auditLog);
			}
		}
	}

	/**
	 * Clears the cache for all audit logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditLogImpl.class);

		finderCache.clearCache(AuditLogImpl.class);
	}

	/**
	 * Clears the cache for the audit log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditLog auditLog) {
		entityCache.removeResult(AuditLogImpl.class, auditLog);
	}

	@Override
	public void clearCache(List<AuditLog> auditLogs) {
		for (AuditLog auditLog : auditLogs) {
			entityCache.removeResult(AuditLogImpl.class, auditLog);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AuditLogImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AuditLogImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AuditLogModelImpl auditLogModelImpl) {

		Object[] args = new Object[] {
			auditLogModelImpl.getUuid(), auditLogModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, auditLogModelImpl);

		args = new Object[] {auditLogModelImpl.getAuditLogId()};

		finderCache.putResult(
			_finderPathCountByGetAuditDataByAuditLogId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByGetAuditDataByAuditLogId, args,
			auditLogModelImpl);
	}

	/**
	 * Creates a new audit log with the primary key. Does not add the audit log to the database.
	 *
	 * @param auditLogId the primary key for the new audit log
	 * @return the new audit log
	 */
	@Override
	public AuditLog create(long auditLogId) {
		AuditLog auditLog = new AuditLogImpl();

		auditLog.setNew(true);
		auditLog.setPrimaryKey(auditLogId);

		String uuid = _portalUUID.generate();

		auditLog.setUuid(uuid);

		auditLog.setCompanyId(CompanyThreadLocal.getCompanyId());

		return auditLog;
	}

	/**
	 * Removes the audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log that was removed
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	@Override
	public AuditLog remove(long auditLogId) throws NoSuchAuditLogException {
		return remove((Serializable)auditLogId);
	}

	/**
	 * Removes the audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit log
	 * @return the audit log that was removed
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	@Override
	public AuditLog remove(Serializable primaryKey)
		throws NoSuchAuditLogException {

		Session session = null;

		try {
			session = openSession();

			AuditLog auditLog = (AuditLog)session.get(
				AuditLogImpl.class, primaryKey);

			if (auditLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(auditLog);
		}
		catch (NoSuchAuditLogException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected AuditLog removeImpl(AuditLog auditLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditLog)) {
				auditLog = (AuditLog)session.get(
					AuditLogImpl.class, auditLog.getPrimaryKeyObj());
			}

			if (auditLog != null) {
				session.delete(auditLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (auditLog != null) {
			clearCache(auditLog);
		}

		return auditLog;
	}

	@Override
	public AuditLog updateImpl(AuditLog auditLog) {
		boolean isNew = auditLog.isNew();

		if (!(auditLog instanceof AuditLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(auditLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(auditLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in auditLog proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AuditLog implementation " +
					auditLog.getClass());
		}

		AuditLogModelImpl auditLogModelImpl = (AuditLogModelImpl)auditLog;

		if (Validator.isNull(auditLog.getUuid())) {
			String uuid = _portalUUID.generate();

			auditLog.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (auditLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				auditLog.setCreateDate(date);
			}
			else {
				auditLog.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!auditLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				auditLog.setModifiedDate(date);
			}
			else {
				auditLog.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(auditLog);
			}
			else {
				auditLog = (AuditLog)session.merge(auditLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AuditLogImpl.class, auditLogModelImpl, false, true);

		cacheUniqueFindersCache(auditLogModelImpl);

		if (isNew) {
			auditLog.setNew(false);
		}

		auditLog.resetOriginalValues();

		return auditLog;
	}

	/**
	 * Returns the audit log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit log
	 * @return the audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	@Override
	public AuditLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditLogException {

		AuditLog auditLog = fetchByPrimaryKey(primaryKey);

		if (auditLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return auditLog;
	}

	/**
	 * Returns the audit log with the primary key or throws a <code>NoSuchAuditLogException</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log
	 * @throws NoSuchAuditLogException if a audit log with the primary key could not be found
	 */
	@Override
	public AuditLog findByPrimaryKey(long auditLogId)
		throws NoSuchAuditLogException {

		return findByPrimaryKey((Serializable)auditLogId);
	}

	/**
	 * Returns the audit log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log, or <code>null</code> if a audit log with the primary key could not be found
	 */
	@Override
	public AuditLog fetchByPrimaryKey(long auditLogId) {
		return fetchByPrimaryKey((Serializable)auditLogId);
	}

	/**
	 * Returns all the audit logs.
	 *
	 * @return the audit logs
	 */
	@Override
	public List<AuditLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AuditLog> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<AuditLog> findAll(
		int start, int end, OrderByComparator<AuditLog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<AuditLog> findAll(
		int start, int end, OrderByComparator<AuditLog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<AuditLog> list = null;

		if (useFinderCache) {
			list = (List<AuditLog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AUDITLOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITLOG;

				sql = sql.concat(AuditLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AuditLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the audit logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditLog auditLog : findAll()) {
			remove(auditLog);
		}
	}

	/**
	 * Returns the number of audit logs.
	 *
	 * @return the number of audit logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AUDITLOG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "auditLogId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AUDITLOG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AuditLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit log persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathFetchByGetAuditDataByAuditLogId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByGetAuditDataByAuditLogId",
			new String[] {Long.class.getName()}, new String[] {"auditLogId"},
			true);

		_finderPathCountByGetAuditDataByAuditLogId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGetAuditDataByAuditLogId",
			new String[] {Long.class.getName()}, new String[] {"auditLogId"},
			false);

		_finderPathWithPaginationFindByGetAuditDataByAction = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGetAuditDataByAction",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"action"}, true);

		_finderPathWithoutPaginationFindByGetAuditDataByAction = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGetAuditDataByAction", new String[] {String.class.getName()},
			new String[] {"action"}, true);

		_finderPathCountByGetAuditDataByAction = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGetAuditDataByAction",
			new String[] {String.class.getName()}, new String[] {"action"},
			false);

		_finderPathWithPaginationFindByGetAuditDataByModuleName =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByGetAuditDataByModuleName",
				new String[] {
					String.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				},
				new String[] {"moduleName"}, true);

		_finderPathWithoutPaginationFindByGetAuditDataByModuleName =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByGetAuditDataByModuleName",
				new String[] {String.class.getName()},
				new String[] {"moduleName"}, true);

		_finderPathCountByGetAuditDataByModuleName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGetAuditDataByModuleName",
			new String[] {String.class.getName()}, new String[] {"moduleName"},
			false);

		_setAuditLogUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAuditLogUtilPersistence(null);

		entityCache.removeCache(AuditLogImpl.class.getName());
	}

	private void _setAuditLogUtilPersistence(
		AuditLogPersistence auditLogPersistence) {

		try {
			Field field = AuditLogUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, auditLogPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = AIXTOR_DBPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AIXTOR_DBPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AIXTOR_DBPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_AUDITLOG =
		"SELECT auditLog FROM AuditLog auditLog";

	private static final String _SQL_SELECT_AUDITLOG_WHERE =
		"SELECT auditLog FROM AuditLog auditLog WHERE ";

	private static final String _SQL_COUNT_AUDITLOG =
		"SELECT COUNT(auditLog) FROM AuditLog auditLog";

	private static final String _SQL_COUNT_AUDITLOG_WHERE =
		"SELECT COUNT(auditLog) FROM AuditLog auditLog WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "auditLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AuditLog exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AuditLog exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AuditLogPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private PortalUUID _portalUUID;

}