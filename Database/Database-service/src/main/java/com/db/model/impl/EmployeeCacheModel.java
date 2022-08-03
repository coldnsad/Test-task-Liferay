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

package com.db.model.impl;

import com.db.model.Employee;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if (id == employeeCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{id=");
		sb.append(id);
		sb.append(", lastname=");
		sb.append(lastname);
		sb.append(", firstname=");
		sb.append(firstname);
		sb.append(", patronymic=");
		sb.append(patronymic);
		sb.append(", birthdate=");
		sb.append(birthdate);
		sb.append(", gender=");
		sb.append(gender);
		sb.append(", positionTypesId=");
		sb.append(positionTypesId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		employeeImpl.setId(id);

		if (lastname == null) {
			employeeImpl.setLastname("");
		}
		else {
			employeeImpl.setLastname(lastname);
		}

		if (firstname == null) {
			employeeImpl.setFirstname("");
		}
		else {
			employeeImpl.setFirstname(firstname);
		}

		if (patronymic == null) {
			employeeImpl.setPatronymic("");
		}
		else {
			employeeImpl.setPatronymic(patronymic);
		}

		if (birthdate == Long.MIN_VALUE) {
			employeeImpl.setBirthdate(null);
		}
		else {
			employeeImpl.setBirthdate(new Date(birthdate));
		}

		if (gender == null) {
			employeeImpl.setGender("");
		}
		else {
			employeeImpl.setGender(gender);
		}

		employeeImpl.setPositionTypesId(positionTypesId);

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		lastname = objectInput.readUTF();
		firstname = objectInput.readUTF();
		patronymic = objectInput.readUTF();
		birthdate = objectInput.readLong();
		gender = objectInput.readUTF();

		positionTypesId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		if (lastname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastname);
		}

		if (firstname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstname);
		}

		if (patronymic == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(patronymic);
		}

		objectOutput.writeLong(birthdate);

		if (gender == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(gender);
		}

		objectOutput.writeLong(positionTypesId);
	}

	public long id;
	public String lastname;
	public String firstname;
	public String patronymic;
	public long birthdate;
	public String gender;
	public long positionTypesId;

}