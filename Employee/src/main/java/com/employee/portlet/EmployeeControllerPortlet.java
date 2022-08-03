package com.employee.portlet;
import com.db.model.Employee;
import com.db.model.PositionType;

import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.PositionTypeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeeControllerPortletKeys.EMPLOYEECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeControllerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		int delta = ParamUtil.getInteger(renderRequest, "delta");
		int currentPage = ParamUtil.getInteger(renderRequest, "cur");

		//System.out.println(delta + "-" + currentPage);

		int to = delta == 0 ? 2 : currentPage * delta;
		int from = delta == 0 ? 0 : to - delta;

		//System.out.println(from + "-" + to);

		renderRequest.setAttribute("totalEmployees", EmployeeLocalServiceUtil.getEmployeesCount());
		renderRequest.setAttribute("employees", EmployeeLocalServiceUtil.getEmployees(from,to));

		super.doView(renderRequest, renderResponse);
	}
}