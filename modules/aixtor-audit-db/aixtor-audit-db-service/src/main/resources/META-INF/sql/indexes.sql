create index IX_CB900162 on AIXTOR_DB_AuditLog (action[$COLUMN_LENGTH:75$]);
create index IX_CA690103 on AIXTOR_DB_AuditLog (moduleName[$COLUMN_LENGTH:75$]);
create index IX_C0ADF12C on AIXTOR_DB_AuditLog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DD18D2AE on AIXTOR_DB_AuditLog (uuid_[$COLUMN_LENGTH:75$], groupId);