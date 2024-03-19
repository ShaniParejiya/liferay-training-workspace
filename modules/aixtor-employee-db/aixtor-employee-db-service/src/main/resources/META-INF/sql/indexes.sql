create index IX_708AEE72 on AIXTOR_DB_Branch (branchName[$COLUMN_LENGTH:75$]);
create index IX_5DFD7125 on AIXTOR_DB_Branch (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_5B05EDC2 on AIXTOR_DB_City (stateId);
create index IX_187A4AE on AIXTOR_DB_City (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_8E4DDBD2 on AIXTOR_DB_Department (departmentName[$COLUMN_LENGTH:75$]);
create index IX_8FD18A55 on AIXTOR_DB_Department (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_C91F5408 on AIXTOR_DB_Designation (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_17FB570E on AIXTOR_DB_Employee (branchId);
create index IX_8534A53E on AIXTOR_DB_Employee (departmentId);
create index IX_206F45F1 on AIXTOR_DB_Employee (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_96E34793 on AIXTOR_DB_State (countryId);
create index IX_127374E2 on AIXTOR_DB_State (uuid_[$COLUMN_LENGTH:75$], companyId);