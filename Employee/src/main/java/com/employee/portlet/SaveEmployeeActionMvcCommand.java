package com.employee.portlet;

import com.db.model.Employee;
import com.db.service.EmployeeLocalService;
import com.db.service.EmployeeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
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

        PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

        SessionMessages.add(actionRequest, ((LiferayPortletConfig)portletConfig).getPortletId() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
        saveEmployee(actionRequest, actionResponse);
    }

    public void saveEmployee(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException, IOException {

        Employee employee = null;
        long employeeId = ParamUtil.getLong(actionRequest, "employeeId", 0);
        System.out.println("Employee id: " + employeeId);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        String lastName   = ParamUtil.getString(actionRequest, "lastname");
        String firstName  = ParamUtil.getString(actionRequest, "firstname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        String birthdayString = ParamUtil.getString(actionRequest, "birthdate");
        Date birthdayDate = ParamUtil.getDate(actionRequest, "birthdate", sdf);

        System.out.println("-----------------------------------");
        System.out.println("Date date: " + birthdayDate);
        System.out.println("String date: " + birthdayString);

        if (birthdayDate.compareTo(new Date()) > 0) {
            SessionErrors.add(actionRequest, "error-date-message");
            actionResponse.sendRedirect("http://localhost:8080/web/guest/%D0%A1%D0%BE%D1%82%D1%80%D1%83%D0%B4%D0%BD%D0%B8%D0%BA%D0%B8?p_p_id=com_employee_EmployeeControllerPortlet_INSTANCE_aeca&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_employee_EmployeeControllerPortlet_INSTANCE_aeca_mvcRenderCommandName=%2Femployee%2Fcreate&_com_employee_EmployeeControllerPortlet_INSTANCE_aeca_employeeId=" + employeeId);
        }else {
            SessionErrors.clear(actionRequest);
        }

        Long positionId   = ParamUtil.getLong(actionRequest,   "positionTypesId");
        String gender     = ParamUtil.getString(actionRequest, "gender");

        if (employeeId > 0) {
            employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);
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
