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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeWrapper
	extends BaseModelWrapper<Employee>
	implements Employee, ModelWrapper<Employee> {

	public EmployeeWrapper(Employee employee) {
		super(employee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("firstname", getFirstname());
		attributes.put("lastname", getLastname());
		attributes.put("patronymic", getPatronymic());
		attributes.put("birthdate", getBirthdate());
		attributes.put("gender", getGender());
		attributes.put("positionTypesId", getPositionTypesId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String firstname = (String)attributes.get("firstname");

		if (firstname != null) {
			setFirstname(firstname);
		}

		String lastname = (String)attributes.get("lastname");

		if (lastname != null) {
			setLastname(lastname);
		}

		String patronymic = (String)attributes.get("patronymic");

		if (patronymic != null) {
			setPatronymic(patronymic);
		}

		Date birthdate = (Date)attributes.get("birthdate");

		if (birthdate != null) {
			setBirthdate(birthdate);
		}

		String gender = (String)attributes.get("gender");

		if (gender != null) {
			setGender(gender);
		}

		Long positionTypesId = (Long)attributes.get("positionTypesId");

		if (positionTypesId != null) {
			setPositionTypesId(positionTypesId);
		}
	}

	/**
	 * Returns the birthdate of this employee.
	 *
	 * @return the birthdate of this employee
	 */
	@Override
	public Date getBirthdate() {
		return model.getBirthdate();
	}

	/**
	 * Returns the firstname of this employee.
	 *
	 * @return the firstname of this employee
	 */
	@Override
	public String getFirstname() {
		return model.getFirstname();
	}

	/**
	 * Returns the gender of this employee.
	 *
	 * @return the gender of this employee
	 */
	@Override
	public String getGender() {
		return model.getGender();
	}

	/**
	 * Returns the ID of this employee.
	 *
	 * @return the ID of this employee
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the lastname of this employee.
	 *
	 * @return the lastname of this employee
	 */
	@Override
	public String getLastname() {
		return model.getLastname();
	}

	/**
	 * Returns the patronymic of this employee.
	 *
	 * @return the patronymic of this employee
	 */
	@Override
	public String getPatronymic() {
		return model.getPatronymic();
	}

	/**
	 * Returns the position types ID of this employee.
	 *
	 * @return the position types ID of this employee
	 */
	@Override
	public long getPositionTypesId() {
		return model.getPositionTypesId();
	}

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
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
	 * Sets the birthdate of this employee.
	 *
	 * @param birthdate the birthdate of this employee
	 */
	@Override
	public void setBirthdate(Date birthdate) {
		model.setBirthdate(birthdate);
	}

	/**
	 * Sets the firstname of this employee.
	 *
	 * @param firstname the firstname of this employee
	 */
	@Override
	public void setFirstname(String firstname) {
		model.setFirstname(firstname);
	}

	/**
	 * Sets the gender of this employee.
	 *
	 * @param gender the gender of this employee
	 */
	@Override
	public void setGender(String gender) {
		model.setGender(gender);
	}

	/**
	 * Sets the ID of this employee.
	 *
	 * @param id the ID of this employee
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the lastname of this employee.
	 *
	 * @param lastname the lastname of this employee
	 */
	@Override
	public void setLastname(String lastname) {
		model.setLastname(lastname);
	}

	/**
	 * Sets the patronymic of this employee.
	 *
	 * @param patronymic the patronymic of this employee
	 */
	@Override
	public void setPatronymic(String patronymic) {
		model.setPatronymic(patronymic);
	}

	/**
	 * Sets the position types ID of this employee.
	 *
	 * @param positionTypesId the position types ID of this employee
	 */
	@Override
	public void setPositionTypesId(long positionTypesId) {
		model.setPositionTypesId(positionTypesId);
	}

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected EmployeeWrapper wrap(Employee employee) {
		return new EmployeeWrapper(employee);
	}

}