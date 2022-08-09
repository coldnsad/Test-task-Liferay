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

package com.db.model.impl;

import com.db.model.Employee;
import com.db.model.PurchaseType;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.PositionTypeLocalServiceUtil;
import com.db.service.PurchaseTypeLocalServiceUtil;
import com.db.service.persistence.PurchaseTypeUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.text.SimpleDateFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class PurchaseImpl extends PurchaseBaseImpl {

    public String showElectronic(){
        try {
            return ElectronicLocalServiceUtil.getElectronic(this.getElectronicId()).getName();
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }

    public String showEmployee(){
        try {
            Employee empl = EmployeeLocalServiceUtil.getEmployee(this.getEmployeeId());
            return empl.getLastname() + " " + empl.getFirstname().charAt(0) + "." + " " + empl.getPatronymic().charAt(0) + ".";
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }

    public String showPurchaseType(){
        try {
            return PurchaseTypeLocalServiceUtil.getPurchaseType(this.getPurchaseTypeId()).getName();
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(this.getPurchaseDate());
    }
}