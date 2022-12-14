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
import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.PositionTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class EmployeeImpl extends EmployeeBaseImpl {

    public String showPosition(){
        try {
            return PositionTypeLocalServiceUtil.getPositionType(this.getPositionTypesId()).getName();
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(this.getBirthdate());
    }

    public String showEmployee(){
        return this.getLastname() + " " + this.getFirstname().charAt(0) + "." + " " + this.getPatronymic().charAt(0) + ".";
    }
}