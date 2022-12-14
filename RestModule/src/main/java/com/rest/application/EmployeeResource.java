package com.rest.application;

import com.db.model.Employee;
import com.db.service.EmployeeLocalServiceUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @GET
    @Produces("application/json")
    public String getAllEmployees() {
        List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(0, EmployeeLocalServiceUtil.getEmployeesCount());
        return JSONFactoryUtil.serialize(employees);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getEmployee(@PathParam("id") String id) {
        try {
            return JSONFactoryUtil.serialize(EmployeeLocalServiceUtil.getEmployee(Long.parseLong(id)));
        } catch (com.liferay.portal.kernel.exception.PortalException e) {
            throw new RuntimeException(e);
        }
    }

    /*@POST
    @Consumes("application/json")
    @Produces("application/json")
    public Employee addEmployee(Employee newEmployee) {
        // Implementation
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEmployee(Employee newEmployee, @PathParam("id") String id) {
        // Implementation
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") String id) {
        // Implementation
    }*/
}
