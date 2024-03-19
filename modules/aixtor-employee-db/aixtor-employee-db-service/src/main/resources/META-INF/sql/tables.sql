create table AIXTOR_DB_Branch (
	uuid_ VARCHAR(75) null,
	branchId LONG not null primary key,
	branchName VARCHAR(75) null,
	countryId LONG,
	stateId LONG,
	cityId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table AIXTOR_DB_City (
	uuid_ VARCHAR(75) null,
	cityId LONG not null primary key,
	cityName VARCHAR(75) null,
	stateId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table AIXTOR_DB_Department (
	uuid_ VARCHAR(75) null,
	departmentId LONG not null primary key,
	departmentName VARCHAR(75) null,
	departmentHead VARCHAR(75) null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table AIXTOR_DB_Designation (
	uuid_ VARCHAR(75) null,
	designationId LONG not null primary key,
	designationName VARCHAR(75) null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table AIXTOR_DB_Employee (
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	email VARCHAR(75) null,
	mobileNumber LONG,
	departmentId LONG,
	branchId LONG,
	designationId LONG,
	address VARCHAR(75) null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table AIXTOR_DB_State (
	uuid_ VARCHAR(75) null,
	stateId LONG not null primary key,
	stateName VARCHAR(75) null,
	countryId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);