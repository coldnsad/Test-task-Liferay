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

package com.db.service.impl;

import com.db.model.Purchase;
import com.db.service.PurchaseLocalServiceUtil;
import com.db.service.base.PurchaseLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Registration;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.db.model.Purchase",
	service = AopService.class
)
public class PurchaseLocalServiceImpl extends PurchaseLocalServiceBaseImpl {

	public List<Purchase> getPurchases() throws PortalException {
		DynamicQuery query = PurchaseLocalServiceUtil.dynamicQuery();
		query.addOrder(OrderFactoryUtil.desc("purchaseDate"));
		return PurchaseLocalServiceUtil.dynamicQuery(query);
	}
}