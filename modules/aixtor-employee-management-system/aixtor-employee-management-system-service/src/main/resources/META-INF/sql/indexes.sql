create index IX_449A2E34 on AIXTOR_Branch (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_3A1CC413 on AIXTOR_City (stateId);
create index IX_6AFE597D on AIXTOR_City (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_309B27E4 on AIXTOR_Department (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_41896859 on AIXTOR_Designation (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_D2D0FB40 on AIXTOR_Employee (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_B97983A2 on AIXTOR_State (countryId);
create index IX_D7D359F3 on AIXTOR_State (uuid_[$COLUMN_LENGTH:75$], companyId);