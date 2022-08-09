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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ElectroEmployee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ElectroEmployee
 * @generated
 */
public class ElectroEmployeeWrapper
	extends BaseModelWrapper<ElectroEmployee>
	implements ElectroEmployee, ModelWrapper<ElectroEmployee> {

	public ElectroEmployeeWrapper(ElectroEmployee electroEmployee) {
		super(electroEmployee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("etypeId", getEtypeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Long etypeId = (Long)attributes.get("etypeId");

		if (etypeId != null) {
			setEtypeId(etypeId);
		}
	}

	/**
	 * Returns the employee ID of this electro employee.
	 *
	 * @return the employee ID of this electro employee
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the etype ID of this electro employee.
	 *
	 * @return the etype ID of this electro employee
	 */
	@Override
	public long getEtypeId() {
		return model.getEtypeId();
	}

	/**
	 * Returns the ID of this electro employee.
	 *
	 * @return the ID of this electro employee
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the primary key of this electro employee.
	 *
	 * @return the primary key of this electro employee
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the employee ID of this electro employee.
	 *
	 * @param employeeId the employee ID of this electro employee
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the etype ID of this electro employee.
	 *
	 * @param etypeId the etype ID of this electro employee
	 */
	@Override
	public void setEtypeId(long etypeId) {
		model.setEtypeId(etypeId);
	}

	/**
	 * Sets the ID of this electro employee.
	 *
	 * @param id the ID of this electro employee
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the primary key of this electro employee.
	 *
	 * @param primaryKey the primary key of this electro employee
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ElectroEmployeeWrapper wrap(ElectroEmployee electroEmployee) {
		return new ElectroEmployeeWrapper(electroEmployee);
	}

}