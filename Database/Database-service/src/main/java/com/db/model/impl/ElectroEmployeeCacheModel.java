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

import com.db.model.ElectroEmployee;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ElectroEmployee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ElectroEmployeeCacheModel
	implements CacheModel<ElectroEmployee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ElectroEmployeeCacheModel)) {
			return false;
		}

		ElectroEmployeeCacheModel electroEmployeeCacheModel =
			(ElectroEmployeeCacheModel)object;

		if (id == electroEmployeeCacheModel.id) {
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
		StringBundler sb = new StringBundler(7);

		sb.append("{id=");
		sb.append(id);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", etypeId=");
		sb.append(etypeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ElectroEmployee toEntityModel() {
		ElectroEmployeeImpl electroEmployeeImpl = new ElectroEmployeeImpl();

		electroEmployeeImpl.setId(id);
		electroEmployeeImpl.setEmployeeId(employeeId);
		electroEmployeeImpl.setEtypeId(etypeId);

		electroEmployeeImpl.resetOriginalValues();

		return electroEmployeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		employeeId = objectInput.readLong();

		etypeId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(etypeId);
	}

	public long id;
	public long employeeId;
	public long etypeId;

}