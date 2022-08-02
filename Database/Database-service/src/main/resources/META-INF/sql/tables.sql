create table db_Employee (
	id_ LONG not null primary key,
	firstname VARCHAR(75) null,
	lastname VARCHAR(75) null,
	patronymic VARCHAR(75) null,
	birthdate DATE null,
	gender BOOLEAN,
	positionTypesId LONG
);

create table db_Employee_PositionTypes (
	companyId LONG not null,
	employeeId LONG not null,
	positionId LONG not null,
	primary key (employeeId, positionId)
);

create table db_PositionType (
	id_ LONG not null primary key,
	name VARCHAR(75) null
);