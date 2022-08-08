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

package com.db.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ElectronicLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicLocalService
 * @generated
 */
public class ElectronicLocalServiceWrapper
	implements ElectronicLocalService, ServiceWrapper<ElectronicLocalService> {

	public ElectronicLocalServiceWrapper(
		ElectronicLocalService electronicLocalService) {

		_electronicLocalService = electronicLocalService;
	}

	/**
	 * Adds the electronic to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronic the electronic
	 * @return the electronic that was added
	 */
	@Override
	public com.db.model.Electronic addElectronic(
		com.db.model.Electronic electronic) {

		return _electronicLocalService.addElectronic(electronic);
	}

	/**
	 * Creates a new electronic with the primary key. Does not add the electronic to the database.
	 *
	 * @param id the primary key for the new electronic
	 * @return the new electronic
	 */
	@Override
	public com.db.model.Electronic createElectronic(long id) {
		return _electronicLocalService.createElectronic(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the electronic from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronic the electronic
	 * @return the electronic that was removed
	 */
	@Override
	public com.db.model.Electronic deleteElectronic(
		com.db.model.Electronic electronic) {

		return _electronicLocalService.deleteElectronic(electronic);
	}

	/**
	 * Deletes the electronic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the electronic
	 * @return the electronic that was removed
	 * @throws PortalException if a electronic with the primary key could not be found
	 */
	@Override
	public com.db.model.Electronic deleteElectronic(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicLocalService.deleteElectronic(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _electronicLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _electronicLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.db.model.impl.ElectronicModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _electronicLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.db.model.impl.ElectronicModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _electronicLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _electronicLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _electronicLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.db.model.Electronic fetchElectronic(long id) {
		return _electronicLocalService.fetchElectronic(id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _electronicLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the electronic with the primary key.
	 *
	 * @param id the primary key of the electronic
	 * @return the electronic
	 * @throws PortalException if a electronic with the primary key could not be found
	 */
	@Override
	public com.db.model.Electronic getElectronic(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicLocalService.getElectronic(id);
	}

	/**
	 * Returns a range of all the electronics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.db.model.impl.ElectronicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics
	 * @param end the upper bound of the range of electronics (not inclusive)
	 * @return the range of electronics
	 */
	@Override
	public java.util.List<com.db.model.Electronic> getElectronics(
		int start, int end) {

		return _electronicLocalService.getElectronics(start, end);
	}

	/**
	 * Returns the number of electronics.
	 *
	 * @return the number of electronics
	 */
	@Override
	public int getElectronicsCount() {
		return _electronicLocalService.getElectronicsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _electronicLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _electronicLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the electronic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronic the electronic
	 * @return the electronic that was updated
	 */
	@Override
	public com.db.model.Electronic updateElectronic(
		com.db.model.Electronic electronic) {

		return _electronicLocalService.updateElectronic(electronic);
	}

	@Override
	public ElectronicLocalService getWrappedService() {
		return _electronicLocalService;
	}

	@Override
	public void setWrappedService(
		ElectronicLocalService electronicLocalService) {

		_electronicLocalService = electronicLocalService;
	}

	private ElectronicLocalService _electronicLocalService;

}