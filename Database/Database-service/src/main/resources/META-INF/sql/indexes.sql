create index IX_BCA0410A on db_ElectroType (name[$COLUMN_LENGTH:100$]);

create index IX_4924AB13 on db_ElectroTypes (name[$COLUMN_LENGTH:75$]);

create index IX_14687702 on db_Electronic (name[$COLUMN_LENGTH:150$]);

create index IX_CBAFAD96 on db_Employee (firstname[$COLUMN_LENGTH:100$]);

create index IX_7BE102C2 on db_Employee_PositionTypes (companyId);
create index IX_AE430A3E on db_Employee_PositionTypes (positionId);

create index IX_DD6CB149 on db_PositionType (name[$COLUMN_LENGTH:100$]);