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

package com.aixtor.training.liferay.audit.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditLogLocalService
 * @generated
 */
public class AuditLogLocalServiceWrapper
	implements AuditLogLocalService, ServiceWrapper<AuditLogLocalService> {

	public AuditLogLocalServiceWrapper() {
		this(null);
	}

	public AuditLogLocalServiceWrapper(
		AuditLogLocalService auditLogLocalService) {

		_auditLogLocalService = auditLogLocalService;
	}

	/**
	 * Adds the audit log to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditLog the audit log
	 * @return the audit log that was added
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog addAuditLog(
		com.aixtor.training.liferay.audit.model.AuditLog auditLog) {

		return _auditLogLocalService.addAuditLog(auditLog);
	}

	/**
	 * Creates a new audit log with the primary key. Does not add the audit log to the database.
	 *
	 * @param auditLogId the primary key for the new audit log
	 * @return the new audit log
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog createAuditLog(
		long auditLogId) {

		return _auditLogLocalService.createAuditLog(auditLogId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the audit log from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditLog the audit log
	 * @return the audit log that was removed
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog deleteAuditLog(
		com.aixtor.training.liferay.audit.model.AuditLog auditLog) {

		return _auditLogLocalService.deleteAuditLog(auditLog);
	}

	/**
	 * Deletes the audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log that was removed
	 * @throws PortalException if a audit log with the primary key could not be found
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog deleteAuditLog(
			long auditLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.deleteAuditLog(auditLogId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _auditLogLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _auditLogLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditLogLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.aixtor.training.liferay.audit.model.impl.AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _auditLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.aixtor.training.liferay.audit.model.impl.AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _auditLogLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _auditLogLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog fetchAuditLog(
		long auditLogId) {

		return _auditLogLocalService.fetchAuditLog(auditLogId);
	}

	/**
	 * Returns the audit log matching the UUID and group.
	 *
	 * @param uuid the audit log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching audit log, or <code>null</code> if a matching audit log could not be found
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog
		fetchAuditLogByUuidAndGroupId(String uuid, long groupId) {

		return _auditLogLocalService.fetchAuditLogByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _auditLogLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the audit log with the primary key.
	 *
	 * @param auditLogId the primary key of the audit log
	 * @return the audit log
	 * @throws PortalException if a audit log with the primary key could not be found
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog getAuditLog(
			long auditLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.getAuditLog(auditLogId);
	}

	/**
	 * Returns the audit log matching the UUID and group.
	 *
	 * @param uuid the audit log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching audit log
	 * @throws PortalException if a matching audit log could not be found
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog
			getAuditLogByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.getAuditLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.aixtor.training.liferay.audit.model.impl.AuditLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @return the range of audit logs
	 */
	@Override
	public java.util.List<com.aixtor.training.liferay.audit.model.AuditLog>
		getAuditLogs(int start, int end) {

		return _auditLogLocalService.getAuditLogs(start, end);
	}

	/**
	 * Returns all the audit logs matching the UUID and company.
	 *
	 * @param uuid the UUID of the audit logs
	 * @param companyId the primary key of the company
	 * @return the matching audit logs, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.aixtor.training.liferay.audit.model.AuditLog>
		getAuditLogsByUuidAndCompanyId(String uuid, long companyId) {

		return _auditLogLocalService.getAuditLogsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of audit logs matching the UUID and company.
	 *
	 * @param uuid the UUID of the audit logs
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of audit logs
	 * @param end the upper bound of the range of audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching audit logs, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.aixtor.training.liferay.audit.model.AuditLog>
		getAuditLogsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.aixtor.training.liferay.audit.model.AuditLog>
					orderByComparator) {

		return _auditLogLocalService.getAuditLogsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of audit logs.
	 *
	 * @return the number of audit logs
	 */
	@Override
	public int getAuditLogsCount() {
		return _auditLogLocalService.getAuditLogsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _auditLogLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _auditLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditLogLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the audit log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuditLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param auditLog the audit log
	 * @return the audit log that was updated
	 */
	@Override
	public com.aixtor.training.liferay.audit.model.AuditLog updateAuditLog(
		com.aixtor.training.liferay.audit.model.AuditLog auditLog) {

		return _auditLogLocalService.updateAuditLog(auditLog);
	}

	@Override
	public AuditLogLocalService getWrappedService() {
		return _auditLogLocalService;
	}

	@Override
	public void setWrappedService(AuditLogLocalService auditLogLocalService) {
		_auditLogLocalService = auditLogLocalService;
	}

	private AuditLogLocalService _auditLogLocalService;

}