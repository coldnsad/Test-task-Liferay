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
 * This class is a wrapper for {@link Electronic}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Electronic
 * @generated
 */
public class ElectronicWrapper
	extends BaseModelWrapper<Electronic>
	implements Electronic, ModelWrapper<Electronic> {

	public ElectronicWrapper(Electronic electronic) {
		super(electronic);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("name", getName());
		attributes.put("etype", getEtype());
		attributes.put("price", getPrice());
		attributes.put("count", getCount());
		attributes.put("instock", isInstock());
		attributes.put("archive", isArchive());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long etype = (Long)attributes.get("etype");

		if (etype != null) {
			setEtype(etype);
		}

		Long price = (Long)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer count = (Integer)attributes.get("count");

		if (count != null) {
			setCount(count);
		}

		Boolean instock = (Boolean)attributes.get("instock");

		if (instock != null) {
			setInstock(instock);
		}

		Boolean archive = (Boolean)attributes.get("archive");

		if (archive != null) {
			setArchive(archive);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	 * Returns the archive of this electronic.
	 *
	 * @return the archive of this electronic
	 */
	@Override
	public boolean getArchive() {
		return model.getArchive();
	}

	/**
	 * Returns the count of this electronic.
	 *
	 * @return the count of this electronic
	 */
	@Override
	public int getCount() {
		return model.getCount();
	}

	/**
	 * Returns the description of this electronic.
	 *
	 * @return the description of this electronic
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the etype of this electronic.
	 *
	 * @return the etype of this electronic
	 */
	@Override
	public long getEtype() {
		return model.getEtype();
	}

	/**
	 * Returns the ID of this electronic.
	 *
	 * @return the ID of this electronic
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the instock of this electronic.
	 *
	 * @return the instock of this electronic
	 */
	@Override
	public boolean getInstock() {
		return model.getInstock();
	}

	/**
	 * Returns the name of this electronic.
	 *
	 * @return the name of this electronic
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the price of this electronic.
	 *
	 * @return the price of this electronic
	 */
	@Override
	public long getPrice() {
		return model.getPrice();
	}

	/**
	 * Returns the primary key of this electronic.
	 *
	 * @return the primary key of this electronic
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this electronic is archive.
	 *
	 * @return <code>true</code> if this electronic is archive; <code>false</code> otherwise
	 */
	@Override
	public boolean isArchive() {
		return model.isArchive();
	}

	/**
	 * Returns <code>true</code> if this electronic is instock.
	 *
	 * @return <code>true</code> if this electronic is instock; <code>false</code> otherwise
	 */
	@Override
	public boolean isInstock() {
		return model.isInstock();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this electronic is archive.
	 *
	 * @param archive the archive of this electronic
	 */
	@Override
	public void setArchive(boolean archive) {
		model.setArchive(archive);
	}

	/**
	 * Sets the count of this electronic.
	 *
	 * @param count the count of this electronic
	 */
	@Override
	public void setCount(int count) {
		model.setCount(count);
	}

	/**
	 * Sets the description of this electronic.
	 *
	 * @param description the description of this electronic
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the etype of this electronic.
	 *
	 * @param etype the etype of this electronic
	 */
	@Override
	public void setEtype(long etype) {
		model.setEtype(etype);
	}

	/**
	 * Sets the ID of this electronic.
	 *
	 * @param id the ID of this electronic
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets whether this electronic is instock.
	 *
	 * @param instock the instock of this electronic
	 */
	@Override
	public void setInstock(boolean instock) {
		model.setInstock(instock);
	}

	/**
	 * Sets the name of this electronic.
	 *
	 * @param name the name of this electronic
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the price of this electronic.
	 *
	 * @param price the price of this electronic
	 */
	@Override
	public void setPrice(long price) {
		model.setPrice(price);
	}

	/**
	 * Sets the primary key of this electronic.
	 *
	 * @param primaryKey the primary key of this electronic
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String showElectronicType() {
		return model.showElectronicType();
	}

	@Override
	protected ElectronicWrapper wrap(Electronic electronic) {
		return new ElectronicWrapper(electronic);
	}

}