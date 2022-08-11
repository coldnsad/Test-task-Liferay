package com.rest.application;

import com.db.model.Electronic;
import com.db.model.Employee;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/electronic")
public class ElectronicResource {

    @GET
    @Produces("application/json")
    public String getElectronicInStock() {
        DynamicQuery queryElectronicInStock = ElectronicLocalServiceUtil.dynamicQuery();
        queryElectronicInStock.add(RestrictionsFactoryUtil.eq("instock", true));

        List<Electronic> electronics = ElectronicLocalServiceUtil.dynamicQuery(queryElectronicInStock);

        return JSONFactoryUtil.serialize(electronics);
    }
}
