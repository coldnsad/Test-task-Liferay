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

import com.db.model.Electronic;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Electronic in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ElectronicCacheModel
	implements CacheModel<Electronic>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ElectronicCacheModel)) {
			return false;
		}

		ElectronicCacheModel electronicCacheModel =
			(ElectronicCacheModel)object;

		if (id == electronicCacheModel.id) {
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
		StringBundler sb = new StringBundler(17);

		sb.append("{id=");
		sb.append(id);
		sb.append(", name=");
		sb.append(name);
		sb.append(", etype=");
		sb.append(etype);
		sb.append(", price=");
		sb.append(price);
		sb.append(", count=");
		sb.append(count);
		sb.append(", instock=");
		sb.append(instock);
		sb.append(", archive=");
		sb.append(archive);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Electronic toEntityModel() {
		ElectronicImpl electronicImpl = new ElectronicImpl();

		electronicImpl.setId(id);

		if (name == null) {
			electronicImpl.setName("");
		}
		else {
			electronicImpl.setName(name);
		}

		electronicImpl.setEtype(etype);
		electronicImpl.setPrice(price);
		electronicImpl.setCount(count);
		electronicImpl.setInstock(instock);
		electronicImpl.setArchive(archive);

		if (description == null) {
			electronicImpl.setDescription("");
		}
		else {
			electronicImpl.setDescription(description);
		}

		electronicImpl.resetOriginalValues();

		return electronicImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		name = objectInput.readUTF();

		etype = objectInput.readLong();

		price = objectInput.readLong();

		count = objectInput.readInt();

		instock = objectInput.readBoolean();

		archive = objectInput.readBoolean();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(etype);

		objectOutput.writeLong(price);

		objectOutput.writeInt(count);

		objectOutput.writeBoolean(instock);

		objectOutput.writeBoolean(archive);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long id;
	public String name;
	public long etype;
	public long price;
	public int count;
	public boolean instock;
	public boolean archive;
	public String description;

}