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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Electronic service. Represents a row in the &quot;db_Electronic&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.db.model.impl.ElectronicModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.db.model.impl.ElectronicImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Electronic
 * @generated
 */
@ProviderType
public interface ElectronicModel extends BaseModel<Electronic> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a electronic model instance should use the {@link Electronic} interface instead.
	 */

	/**
	 * Returns the primary key of this electronic.
	 *
	 * @return the primary key of this electronic
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this electronic.
	 *
	 * @param primaryKey the primary key of this electronic
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this electronic.
	 *
	 * @return the ID of this electronic
	 */
	public long getId();

	/**
	 * Sets the ID of this electronic.
	 *
	 * @param id the ID of this electronic
	 */
	public void setId(long id);

	/**
	 * Returns the name of this electronic.
	 *
	 * @return the name of this electronic
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this electronic.
	 *
	 * @param name the name of this electronic
	 */
	public void setName(String name);

	/**
	 * Returns the etype of this electronic.
	 *
	 * @return the etype of this electronic
	 */
	public long getEtype();

	/**
	 * Sets the etype of this electronic.
	 *
	 * @param etype the etype of this electronic
	 */
	public void setEtype(long etype);

	/**
	 * Returns the price of this electronic.
	 *
	 * @return the price of this electronic
	 */
	public long getPrice();

	/**
	 * Sets the price of this electronic.
	 *
	 * @param price the price of this electronic
	 */
	public void setPrice(long price);

	/**
	 * Returns the count of this electronic.
	 *
	 * @return the count of this electronic
	 */
	public int getCount();

	/**
	 * Sets the count of this electronic.
	 *
	 * @param count the count of this electronic
	 */
	public void setCount(int count);

	/**
	 * Returns the instock of this electronic.
	 *
	 * @return the instock of this electronic
	 */
	public boolean getInstock();

	/**
	 * Returns <code>true</code> if this electronic is instock.
	 *
	 * @return <code>true</code> if this electronic is instock; <code>false</code> otherwise
	 */
	public boolean isInstock();

	/**
	 * Sets whether this electronic is instock.
	 *
	 * @param instock the instock of this electronic
	 */
	public void setInstock(boolean instock);

	/**
	 * Returns the archive of this electronic.
	 *
	 * @return the archive of this electronic
	 */
	public boolean getArchive();

	/**
	 * Returns <code>true</code> if this electronic is archive.
	 *
	 * @return <code>true</code> if this electronic is archive; <code>false</code> otherwise
	 */
	public boolean isArchive();

	/**
	 * Sets whether this electronic is archive.
	 *
	 * @param archive the archive of this electronic
	 */
	public void setArchive(boolean archive);

	/**
	 * Returns the description of this electronic.
	 *
	 * @return the description of this electronic
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this electronic.
	 *
	 * @param description the description of this electronic
	 */
	public void setDescription(String description);

}