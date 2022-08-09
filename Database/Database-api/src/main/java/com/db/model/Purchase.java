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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Purchase service. Represents a row in the &quot;db_Purchase&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PurchaseModel
 * @generated
 */
@ImplementationClassName("com.db.model.impl.PurchaseImpl")
@ProviderType
public interface Purchase extends PersistedModel, PurchaseModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.db.model.impl.PurchaseImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Purchase, Long> ID_ACCESSOR =
		new Accessor<Purchase, Long>() {

			@Override
			public Long get(Purchase purchase) {
				return purchase.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Purchase> getTypeClass() {
				return Purchase.class;
			}

		};

	public String showElectronic();

	public String showEmployee();

	public String showPurchaseType();

	public String formatDate();

}