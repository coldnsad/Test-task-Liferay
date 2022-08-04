package com.employee.portlet;

import com.db.model.Employee;
import com.db.service.EmployeeLocalService;
import com.db.service.EmployeeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
public class SaveEmployeeActionMvcCommand extends BaseMVCActionCommand {
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        saveEmployee(actionRequest, actionResponse);
    }

    public void saveEmployee(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException {

        Employee employee = null;
        long employeeId = ParamUtil.getLong(actionRequest, "employeeId", 0);
        System.out.println("Employee id: " + employeeId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        String lastName   = ParamUtil.getString(actionRequest, "lastname");
        String firstName  = ParamUtil.getString(actionRequest, "firstname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        String birthdayString = ParamUtil.getString(actionRequest, "birthdate");
        Date birthdayDate = ParamUtil.getDate(actionRequest, "birthdate", sdf);

        System.out.println("Date date: " + birthdayDate);
        System.out.println("String date: " + birthdayString);

        Long positionId   = ParamUtil.getLong(actionRequest,   "positionTypesId");
        String gender     = ParamUtil.getString(actionRequest, "gender");

        if (employeeId > 0) {
            employee = EmployeeLocalServiceUtil.createEmployee(employeeId);
        }else {
            employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.
                    increment(Employee.class.getName()));
        }

        employee.setLastname(lastName);
        employee.setFirstname(firstName);
        employee.setPatronymic(patronymic);
        employee.setBirthdate(birthdayDate);
        employee.setPositionTypesId(positionId);
        employee.setGender(gender);

        EmployeeLocalServiceUtil.updateEmployee(employee);

        System.out.println(firstName + " - " + lastName + " - " + patronymic + " - " + sdf.format(birthdayDate) + " - " + positionId+ " - " + gender);
    }
}
