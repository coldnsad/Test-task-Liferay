package com.electroEmployee.portlet;

import com.db.model.ElectroEmployee;
import com.db.model.ElectroType;
import com.db.model.Employee;
import com.db.service.ElectroEmployeeLocalServiceUtil;
import com.db.service.ElectroTypeLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.electroEmployee.constants.ElectronicEmployeeControllerPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ElectronicEmployeeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ElectronicEmployeeControllerPortletKeys.ELECTRONICEMPLOYEECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ElectronicEmployeeControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		List<Employee> employeesList = EmployeeLocalServiceUtil.
				getEmployees(0,EmployeeLocalServiceUtil.getEmployeesCount());

		Map<Long, String> employeesMap = new HashMap<>();
		for (Employee employee:
				employeesList) {
			employeesMap.put(employee.getId(), employee.getLastname() + " " + employee.getFirstname() + " "
					+ employee.getPatronymic());
		}

		List<ElectroType> electroTypeList = ElectroTypeLocalServiceUtil.
				getElectroTypes(0,ElectroTypeLocalServiceUtil.getElectroTypesCount());

		Map<Long, String> electroTypesMap = new HashMap<>();
		for (ElectroType electroType:
				electroTypeList) {
			electroTypesMap.put(electroType.getId(), electroType.getName());
		}

		renderRequest.setAttribute("employees", employeesMap);
		renderRequest.setAttribute("electroTypes", electroTypesMap);

		super.doView(renderRequest, renderResponse);
	}

	public void saveElectroEmployee(ActionRequest actionRequest, ActionResponse actionResponse){

		long employeeId = ParamUtil.getLong(actionRequest,"employeeId");
		long etypeId = ParamUtil.getLong(actionRequest,"etypeId");

		ElectroEmployee electroEmployee = ElectroEmployeeLocalServiceUtil.
				createElectroEmployee(CounterLocalServiceUtil.increment(ElectroEmployee.class.getName()));
		electroEmployee.setEmployeeId(employeeId);
		electroEmployee.setEtypeId(etypeId);

		ElectroEmployeeLocalServiceUtil.addElectroEmployee(electroEmployee);
		System.out.println("electroEmployee added");
	}
}