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

package com.db.service.persistence.test;

import com.db.exception.NoSuchElectroEmployeeException;
import com.db.model.ElectroEmployee;
import com.db.service.ElectroEmployeeLocalServiceUtil;
import com.db.service.persistence.ElectroEmployeePersistence;
import com.db.service.persistence.ElectroEmployeeUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ElectroEmployeePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED, "com.db.service"));

	@Before
	public void setUp() {
		_persistence = ElectroEmployeeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ElectroEmployee> iterator = _electroEmployees.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroEmployee electroEmployee = _persistence.create(pk);

		Assert.assertNotNull(electroEmployee);

		Assert.assertEquals(electroEmployee.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		_persistence.remove(newElectroEmployee);

		ElectroEmployee existingElectroEmployee =
			_persistence.fetchByPrimaryKey(newElectroEmployee.getPrimaryKey());

		Assert.assertNull(existingElectroEmployee);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addElectroEmployee();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroEmployee newElectroEmployee = _persistence.create(pk);

		newElectroEmployee.setEmployeeId(RandomTestUtil.nextLong());

		newElectroEmployee.setEtypeId(RandomTestUtil.nextLong());

		_electroEmployees.add(_persistence.update(newElectroEmployee));

		ElectroEmployee existingElectroEmployee = _persistence.findByPrimaryKey(
			newElectroEmployee.getPrimaryKey());

		Assert.assertEquals(
			existingElectroEmployee.getId(), newElectroEmployee.getId());
		Assert.assertEquals(
			existingElectroEmployee.getEmployeeId(),
			newElectroEmployee.getEmployeeId());
		Assert.assertEquals(
			existingElectroEmployee.getEtypeId(),
			newElectroEmployee.getEtypeId());
	}

	@Test
	public void testCountByField1() throws Exception {
		_persistence.countByField1(RandomTestUtil.nextLong());

		_persistence.countByField1(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		ElectroEmployee existingElectroEmployee = _persistence.findByPrimaryKey(
			newElectroEmployee.getPrimaryKey());

		Assert.assertEquals(existingElectroEmployee, newElectroEmployee);
	}

	@Test(expected = NoSuchElectroEmployeeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<ElectroEmployee> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"db_ElectroEmployee", "id", true, "employeeId", true, "etypeId",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		ElectroEmployee existingElectroEmployee =
			_persistence.fetchByPrimaryKey(newElectroEmployee.getPrimaryKey());

		Assert.assertEquals(existingElectroEmployee, newElectroEmployee);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroEmployee missingElectroEmployee = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingElectroEmployee);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		ElectroEmployee newElectroEmployee1 = addElectroEmployee();
		ElectroEmployee newElectroEmployee2 = addElectroEmployee();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroEmployee1.getPrimaryKey());
		primaryKeys.add(newElectroEmployee2.getPrimaryKey());

		Map<Serializable, ElectroEmployee> electroEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, electroEmployees.size());
		Assert.assertEquals(
			newElectroEmployee1,
			electroEmployees.get(newElectroEmployee1.getPrimaryKey()));
		Assert.assertEquals(
			newElectroEmployee2,
			electroEmployees.get(newElectroEmployee2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ElectroEmployee> electroEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electroEmployees.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		ElectroEmployee newElectroEmployee = addElectroEmployee();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroEmployee.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ElectroEmployee> electroEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electroEmployees.size());
		Assert.assertEquals(
			newElectroEmployee,
			electroEmployees.get(newElectroEmployee.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ElectroEmployee> electroEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electroEmployees.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroEmployee.getPrimaryKey());

		Map<Serializable, ElectroEmployee> electroEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electroEmployees.size());
		Assert.assertEquals(
			newElectroEmployee,
			electroEmployees.get(newElectroEmployee.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			ElectroEmployeeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<ElectroEmployee>() {

				@Override
				public void performAction(ElectroEmployee electroEmployee) {
					Assert.assertNotNull(electroEmployee);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("id", newElectroEmployee.getId()));

		List<ElectroEmployee> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		ElectroEmployee existingElectroEmployee = result.get(0);

		Assert.assertEquals(existingElectroEmployee, newElectroEmployee);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("id", RandomTestUtil.nextLong()));

		List<ElectroEmployee> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		ElectroEmployee newElectroEmployee = addElectroEmployee();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		Object newId = newElectroEmployee.getId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("id", new Object[] {newId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingId = result.get(0);

		Assert.assertEquals(existingId, newId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"id", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ElectroEmployee addElectroEmployee() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroEmployee electroEmployee = _persistence.create(pk);

		electroEmployee.setEmployeeId(RandomTestUtil.nextLong());

		electroEmployee.setEtypeId(RandomTestUtil.nextLong());

		_electroEmployees.add(_persistence.update(electroEmployee));

		return electroEmployee;
	}

	private List<ElectroEmployee> _electroEmployees =
		new ArrayList<ElectroEmployee>();
	private ElectroEmployeePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}