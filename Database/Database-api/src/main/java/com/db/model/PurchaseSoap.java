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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.db.service.http.PurchaseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class PurchaseSoap implements Serializable {

	public static PurchaseSoap toSoapModel(Purchase model) {
		PurchaseSoap soapModel = new PurchaseSoap();

		soapModel.setId(model.getId());
		soapModel.setElectronicId(model.getElectronicId());
		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setPurchaseDate(model.getPurchaseDate());
		soapModel.setPurchaseTypeId(model.getPurchaseTypeId());

		return soapModel;
	}

	public static PurchaseSoap[] toSoapModels(Purchase[] models) {
		PurchaseSoap[] soapModels = new PurchaseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PurchaseSoap[][] toSoapModels(Purchase[][] models) {
		PurchaseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PurchaseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PurchaseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PurchaseSoap[] toSoapModels(List<Purchase> models) {
		List<PurchaseSoap> soapModels = new ArrayList<PurchaseSoap>(
			models.size());

		for (Purchase model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PurchaseSoap[soapModels.size()]);
	}

	public PurchaseSoap() {
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

	public long getElectronicId() {
		return _electronicId;
	}

	public void setElectronicId(long electronicId) {
		_electronicId = electronicId;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public Date getPurchaseDate() {
		return _purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		_purchaseDate = purchaseDate;
	}

	public long getPurchaseTypeId() {
		return _purchaseTypeId;
	}

	public void setPurchaseTypeId(long purchaseTypeId) {
		_purchaseTypeId = purchaseTypeId;
	}

	private long _id;
	private long _electronicId;
	private long _employeeId;
	private Date _purchaseDate;
	private long _purchaseTypeId;

}