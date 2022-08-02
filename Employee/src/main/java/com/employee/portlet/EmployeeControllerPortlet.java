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

		List<PositionType> positionTypeList = PositionTypeLocalServiceUtil.
				getPositionTypes(0,PositionTypeLocalServiceUtil.getPositionTypesCount());

		Map<Long, String> positionTypesMap = new HashMap<>();
		for (PositionType positionType:
			 positionTypeList) {
			positionTypesMap.put(positionType.getId(), positionType.getName());
		}

		//Debug
		for (Map.Entry<Long,String> pair:
				positionTypesMap.entrySet()) {
			System.out.println(pair.getKey() + ":" + pair.getValue());
		}
		//END Debug

		renderRequest.setAttribute("positionTypes", positionTypesMap);

		super.doView(renderRequest, renderResponse);
	}

	public void saveEmployee(ActionRequest actionRequest, ActionResponse actionResponse){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String firstName  = ParamUtil.getString(actionRequest, "firstName");
		String lastName   = ParamUtil.getString(actionRequest, "lastName");
		String patronymic = ParamUtil.getString(actionRequest, "patronymic");
		String birthdayString = ParamUtil.getString(actionRequest,   "birthday");
		Date birthday = null;
		try {
			birthday = sdf.parse(birthdayString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Long positionId   = ParamUtil.getLong(actionRequest,   "position");
		String gender     = ParamUtil.getString(actionRequest, "gender");

		Employee employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.
				increment(Employee.class.getName()));

		employee.setLastname(lastName);
		employee.setFirstname(firstName);
		employee.setPatronymic(patronymic);
		employee.setBirthdate(birthday);
		employee.setPositionTypesId(positionId);
		employee.setGender(gender);

		EmployeeLocalServiceUtil.addEmployee(employee);

		System.out.println(firstName + " - " + lastName + " - " + patronymic + " - " + sdf.format(birthday) + " - " + positionId+ " - " + gender);
	}
}