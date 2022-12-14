create table db_ElectroEmployee (
	id_ LONG not null primary key,
	employeeId LONG,
	etypeId LONG
);

create table db_ElectroType (
	id_ LONG not null primary key,
	name VARCHAR(100) null
);

create table db_ElectroTypes (
	id_ LONG not null primary key,
	name VARCHAR(75) null
);

create table db_Electronic (
	id_ LONG not null primary key,
	name VARCHAR(150) null,
	etype LONG,
	price LONG,
	count INTEGER,
	instock BOOLEAN,
	archive BOOLEAN,
	description TEXT null
);

create table db_Employee (
	id_ LONG not null primary key,
	lastname VARCHAR(100) null,
	firstname VARCHAR(100) null,
	patronymic VARCHAR(100) null,
	birthdate DATE null,
	gender VARCHAR(75) null,
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
	name VARCHAR(100) null
);

create table db_Purchase (
	id_ LONG not null primary key,
	electronicId LONG,
	employeeId LONG,
	purchaseDate DATE null,
	purchaseTypeId LONG
);

create table db_PurchaseType (
	id_ LONG not null primary key,
	name VARCHAR(75) null
);