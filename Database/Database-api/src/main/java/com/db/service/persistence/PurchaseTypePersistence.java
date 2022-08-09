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

import com.db.exception.NoSuchPurchaseTypeException;
import com.db.model.PurchaseType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the purchase type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PurchaseTypeUtil
 * @generated
 */
@ProviderType
public interface PurchaseTypePersistence extends BasePersistence<PurchaseType> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PurchaseTypeUtil} to access the purchase type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the purchase types where id = &#63;.
	 *
	 * @param id the ID
	 * @return the matching purchase types
	 */
	public java.util.List<PurchaseType> findByField1(long id);

	/**
	 * Returns a range of all the purchase types where id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param id the ID
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @return the range of matching purchase types
	 */
	public java.util.List<PurchaseType> findByField1(
		long id, int start, int end);

	/**
	 * Returns an ordered range of all the purchase types where id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param id the ID
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching purchase types
	 */
	public java.util.List<PurchaseType> findByField1(
		long id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the purchase types where id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param id the ID
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching purchase types
	 */
	public java.util.List<PurchaseType> findByField1(
		long id, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first purchase type in the ordered set where id = &#63;.
	 *
	 * @param id the ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase type
	 * @throws NoSuchPurchaseTypeException if a matching purchase type could not be found
	 */
	public PurchaseType findByField1_First(
			long id,
			com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
				orderByComparator)
		throws NoSuchPurchaseTypeException;

	/**
	 * Returns the first purchase type in the ordered set where id = &#63;.
	 *
	 * @param id the ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase type, or <code>null</code> if a matching purchase type could not be found
	 */
	public PurchaseType fetchByField1_First(
		long id,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator);

	/**
	 * Returns the last purchase type in the ordered set where id = &#63;.
	 *
	 * @param id the ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase type
	 * @throws NoSuchPurchaseTypeException if a matching purchase type could not be found
	 */
	public PurchaseType findByField1_Last(
			long id,
			com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
				orderByComparator)
		throws NoSuchPurchaseTypeException;

	/**
	 * Returns the last purchase type in the ordered set where id = &#63;.
	 *
	 * @param id the ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase type, or <code>null</code> if a matching purchase type could not be found
	 */
	public PurchaseType fetchByField1_Last(
		long id,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator);

	/**
	 * Removes all the purchase types where id = &#63; from the database.
	 *
	 * @param id the ID
	 */
	public void removeByField1(long id);

	/**
	 * Returns the number of purchase types where id = &#63;.
	 *
	 * @param id the ID
	 * @return the number of matching purchase types
	 */
	public int countByField1(long id);

	/**
	 * Caches the purchase type in the entity cache if it is enabled.
	 *
	 * @param purchaseType the purchase type
	 */
	public void cacheResult(PurchaseType purchaseType);

	/**
	 * Caches the purchase types in the entity cache if it is enabled.
	 *
	 * @param purchaseTypes the purchase types
	 */
	public void cacheResult(java.util.List<PurchaseType> purchaseTypes);

	/**
	 * Creates a new purchase type with the primary key. Does not add the purchase type to the database.
	 *
	 * @param id the primary key for the new purchase type
	 * @return the new purchase type
	 */
	public PurchaseType create(long id);

	/**
	 * Removes the purchase type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the purchase type
	 * @return the purchase type that was removed
	 * @throws NoSuchPurchaseTypeException if a purchase type with the primary key could not be found
	 */
	public PurchaseType remove(long id) throws NoSuchPurchaseTypeException;

	public PurchaseType updateImpl(PurchaseType purchaseType);

	/**
	 * Returns the purchase type with the primary key or throws a <code>NoSuchPurchaseTypeException</code> if it could not be found.
	 *
	 * @param id the primary key of the purchase type
	 * @return the purchase type
	 * @throws NoSuchPurchaseTypeException if a purchase type with the primary key could not be found
	 */
	public PurchaseType findByPrimaryKey(long id)
		throws NoSuchPurchaseTypeException;

	/**
	 * Returns the purchase type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the purchase type
	 * @return the purchase type, or <code>null</code> if a purchase type with the primary key could not be found
	 */
	public PurchaseType fetchByPrimaryKey(long id);

	/**
	 * Returns all the purchase types.
	 *
	 * @return the purchase types
	 */
	public java.util.List<PurchaseType> findAll();

	/**
	 * Returns a range of all the purchase types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @return the range of purchase types
	 */
	public java.util.List<PurchaseType> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the purchase types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of purchase types
	 */
	public java.util.List<PurchaseType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the purchase types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of purchase types
	 */
	public java.util.List<PurchaseType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PurchaseType>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the purchase types from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of purchase types.
	 *
	 * @return the number of purchase types
	 */
	public int countAll();

}