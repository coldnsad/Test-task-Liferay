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
public class ElectroTypesPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED, "com.db.service"));

	@Before
	public void setUp() {
		_persistence = ElectroTypesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ElectroTypes> iterator = _electroTypeses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroTypes electroTypes = _persistence.create(pk);

		Assert.assertNotNull(electroTypes);

		Assert.assertEquals(electroTypes.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		_persistence.remove(newElectroTypes);

		ElectroTypes existingElectroTypes = _persistence.fetchByPrimaryKey(
			newElectroTypes.getPrimaryKey());

		Assert.assertNull(existingElectroTypes);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addElectroTypes();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroTypes newElectroTypes = _persistence.create(pk);

		newElectroTypes.setName(RandomTestUtil.randomString());

		_electroTypeses.add(_persistence.update(newElectroTypes));

		ElectroTypes existingElectroTypes = _persistence.findByPrimaryKey(
			newElectroTypes.getPrimaryKey());

		Assert.assertEquals(
			existingElectroTypes.getId(), newElectroTypes.getId());
		Assert.assertEquals(
			existingElectroTypes.getName(), newElectroTypes.getName());
	}

	@Test
	public void testCountByField1() throws Exception {
		_persistence.countByField1("");

		_persistence.countByField1("null");

		_persistence.countByField1((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		ElectroTypes existingElectroTypes = _persistence.findByPrimaryKey(
			newElectroTypes.getPrimaryKey());

		Assert.assertEquals(existingElectroTypes, newElectroTypes);
	}

	@Test(expected = NoSuchElectroTypesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<ElectroTypes> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"db_ElectroTypes", "id", true, "name", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		ElectroTypes existingElectroTypes = _persistence.fetchByPrimaryKey(
			newElectroTypes.getPrimaryKey());

		Assert.assertEquals(existingElectroTypes, newElectroTypes);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroTypes missingElectroTypes = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingElectroTypes);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		ElectroTypes newElectroTypes1 = addElectroTypes();
		ElectroTypes newElectroTypes2 = addElectroTypes();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroTypes1.getPrimaryKey());
		primaryKeys.add(newElectroTypes2.getPrimaryKey());

		Map<Serializable, ElectroTypes> electroTypeses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, electroTypeses.size());
		Assert.assertEquals(
			newElectroTypes1,
			electroTypeses.get(newElectroTypes1.getPrimaryKey()));
		Assert.assertEquals(
			newElectroTypes2,
			electroTypeses.get(newElectroTypes2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ElectroTypes> electroTypeses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electroTypeses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		ElectroTypes newElectroTypes = addElectroTypes();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroTypes.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ElectroTypes> electroTypeses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electroTypeses.size());
		Assert.assertEquals(
			newElectroTypes,
			electroTypeses.get(newElectroTypes.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ElectroTypes> electroTypeses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electroTypeses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectroTypes.getPrimaryKey());

		Map<Serializable, ElectroTypes> electroTypeses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electroTypeses.size());
		Assert.assertEquals(
			newElectroTypes,
			electroTypeses.get(newElectroTypes.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			ElectroTypesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<ElectroTypes>() {

				@Override
				public void performAction(ElectroTypes electroTypes) {
					Assert.assertNotNull(electroTypes);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroTypes.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("id", newElectroTypes.getId()));

		List<ElectroTypes> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		ElectroTypes existingElectroTypes = result.get(0);

		Assert.assertEquals(existingElectroTypes, newElectroTypes);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroTypes.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("id", RandomTestUtil.nextLong()));

		List<ElectroTypes> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		ElectroTypes newElectroTypes = addElectroTypes();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectroTypes.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		Object newId = newElectroTypes.getId();

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
			ElectroTypes.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"id", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ElectroTypes addElectroTypes() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ElectroTypes electroTypes = _persistence.create(pk);

		electroTypes.setName(RandomTestUtil.randomString());

		_electroTypeses.add(_persistence.update(electroTypes));

		return electroTypes;
	}

	private List<ElectroTypes> _electroTypeses = new ArrayList<ElectroTypes>();
	private ElectroTypesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}