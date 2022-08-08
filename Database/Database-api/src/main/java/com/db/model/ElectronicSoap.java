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

package com.db.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.db.service.http.ElectronicServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ElectronicSoap implements Serializable {

	public static ElectronicSoap toSoapModel(Electronic model) {
		ElectronicSoap soapModel = new ElectronicSoap();

		soapModel.setId(model.getId());
		soapModel.setName(model.getName());
		soapModel.setEtype(model.getEtype());
		soapModel.setPrice(model.getPrice());
		soapModel.setCount(model.getCount());
		soapModel.setInstock(model.isInstock());
		soapModel.setArchive(model.isArchive());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ElectronicSoap[] toSoapModels(Electronic[] models) {
		ElectronicSoap[] soapModels = new ElectronicSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ElectronicSoap[][] toSoapModels(Electronic[][] models) {
		ElectronicSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ElectronicSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ElectronicSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ElectronicSoap[] toSoapModels(List<Electronic> models) {
		List<ElectronicSoap> soapModels = new ArrayList<ElectronicSoap>(
			models.size());

		for (Electronic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ElectronicSoap[soapModels.size()]);
	}

	public ElectronicSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getEtype() {
		return _etype;
	}

	public void setEtype(long etype) {
		_etype = etype;
	}

	public long getPrice() {
		return _price;
	}

	public void setPrice(long price) {
		_price = price;
	}

	public int getCount() {
		return _count;
	}

	public void setCount(int count) {
		_count = count;
	}

	public boolean getInstock() {
		return _instock;
	}

	public boolean isInstock() {
		return _instock;
	}

	public void setInstock(boolean instock) {
		_instock = instock;
	}

	public boolean getArchive() {
		return _archive;
	}

	public boolean isArchive() {
		return _archive;
	}

	public void setArchive(boolean archive) {
		_archive = archive;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _id;
	private String _name;
	private long _etype;
	private long _price;
	private int _count;
	private boolean _instock;
	private boolean _archive;
	private String _description;

}