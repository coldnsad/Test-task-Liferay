create index IX_CBAFAD96 on db_Employee (firstname[$COLUMN_LENGTH:75$]);

create index IX_7BE102C2 on db_Employee_PositionTypes (companyId);
create index IX_AE430A3E on db_Employee_PositionTypes (positionId);

create index IX_DD6CB149 on db_PositionType (name[$COLUMN_LENGTH:75$]);