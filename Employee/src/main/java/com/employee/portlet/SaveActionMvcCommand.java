package com.employee.portlet;

import com.db.model.Employee;
import com.db.service.EmployeeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + EmployeeControllerPortletKeys.EMPLOYEECONTROLLER,
                "mvc.command.name=saveEmployee"
        },
        service = MVCActionCommand.class
)
public class SaveActionMvcCommand extends BaseMVCActionCommand {
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        saveEmployee(actionRequest, actionResponse);
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
