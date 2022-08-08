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

package com.db.service.persistence;

import com.db.exception.NoSuchElectroTypeException;
import com.db.model.ElectroType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the electro type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ElectroTypeUtil
 * @generated
 */
@ProviderType
public interface ElectroTypePersistence extends BasePersistence<ElectroType> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ElectroTypeUtil} to access the electro type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the electro types where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching electro types
	 */
	public java.util.List<ElectroType> findByField1(String name);

	/**
	 * Returns a range of all the electro types where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @return the range of matching electro types
	 */
	public java.util.List<ElectroType> findByField1(
		String name, int start, int end);

	/**
	 * Returns an ordered range of all the electro types where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching electro types
	 */
	public java.util.List<ElectroType> findByField1(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the electro types where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching electro types
	 */
	public java.util.List<ElectroType> findByField1(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first electro type in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching electro type
	 * @throws NoSuchElectroTypeException if a matching electro type could not be found
	 */
	public ElectroType findByField1_First(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
				orderByComparator)
		throws NoSuchElectroTypeException;

	/**
	 * Returns the first electro type in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching electro type, or <code>null</code> if a matching electro type could not be found
	 */
	public ElectroType fetchByField1_First(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator);

	/**
	 * Returns the last electro type in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching electro type
	 * @throws NoSuchElectroTypeException if a matching electro type could not be found
	 */
	public ElectroType findByField1_Last(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
				orderByComparator)
		throws NoSuchElectroTypeException;

	/**
	 * Returns the last electro type in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching electro type, or <code>null</code> if a matching electro type could not be found
	 */
	public ElectroType fetchByField1_Last(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator);

	/**
	 * Returns the electro types before and after the current electro type in the ordered set where name = &#63;.
	 *
	 * @param id the primary key of the current electro type
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next electro type
	 * @throws NoSuchElectroTypeException if a electro type with the primary key could not be found
	 */
	public ElectroType[] findByField1_PrevAndNext(
			long id, String name,
			com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
				orderByComparator)
		throws NoSuchElectroTypeException;

	/**
	 * Removes all the electro types where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	public void removeByField1(String name);

	/**
	 * Returns the number of electro types where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching electro types
	 */
	public int countByField1(String name);

	/**
	 * Caches the electro type in the entity cache if it is enabled.
	 *
	 * @param electroType the electro type
	 */
	public void cacheResult(ElectroType electroType);

	/**
	 * Caches the electro types in the entity cache if it is enabled.
	 *
	 * @param electroTypes the electro types
	 */
	public void cacheResult(java.util.List<ElectroType> electroTypes);

	/**
	 * Creates a new electro type with the primary key. Does not add the electro type to the database.
	 *
	 * @param id the primary key for the new electro type
	 * @return the new electro type
	 */
	public ElectroType create(long id);

	/**
	 * Removes the electro type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the electro type
	 * @return the electro type that was removed
	 * @throws NoSuchElectroTypeException if a electro type with the primary key could not be found
	 */
	public ElectroType remove(long id) throws NoSuchElectroTypeException;

	public ElectroType updateImpl(ElectroType electroType);

	/**
	 * Returns the electro type with the primary key or throws a <code>NoSuchElectroTypeException</code> if it could not be found.
	 *
	 * @param id the primary key of the electro type
	 * @return the electro type
	 * @throws NoSuchElectroTypeException if a electro type with the primary key could not be found
	 */
	public ElectroType findByPrimaryKey(long id)
		throws NoSuchElectroTypeException;

	/**
	 * Returns the electro type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the electro type
	 * @return the electro type, or <code>null</code> if a electro type with the primary key could not be found
	 */
	public ElectroType fetchByPrimaryKey(long id);

	/**
	 * Returns all the electro types.
	 *
	 * @return the electro types
	 */
	public java.util.List<ElectroType> findAll();

	/**
	 * Returns a range of all the electro types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @return the range of electro types
	 */
	public java.util.List<ElectroType> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the electro types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of electro types
	 */
	public java.util.List<ElectroType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the electro types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectroTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electro types
	 * @param end the upper bound of the range of electro types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of electro types
	 */
	public java.util.List<ElectroType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectroType>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the electro types from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of electro types.
	 *
	 * @return the number of electro types
	 */
	public int countAll();

}