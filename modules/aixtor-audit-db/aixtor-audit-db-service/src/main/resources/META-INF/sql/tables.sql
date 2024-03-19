create table AIXTOR_DB_AuditLog (
	uuid_ VARCHAR(75) null,
	auditLogId LONG not null primary key,
	action VARCHAR(75) null,
	moduleName VARCHAR(75) null,
	createdBy LONG,
	modifiedBy LONG,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);