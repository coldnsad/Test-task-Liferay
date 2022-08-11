package com.employee.portlet;

import com.db.model.Employee;
import com.db.service.EmployeeLocalService;
import com.db.service.EmployeeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.liferay.portal.kernel.servlet.taglib.TagDynamicIncludeUtil.include;

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

    private PortletURL createRedirectURL(ActionRequest actionRequest, long epmployeeId) {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);

        PortletURL redirectURL = PortletURLFactoryUtil.create(
                actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", "/employee/create");
        redirectURL.setParameter("employeeId", String.valueOf(epmployeeId)); // note the changed parameter name

        return redirectURL;
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
        /*System.out.println("Date date: " + birthdayDate);
        System.out.println("String date: " + birthdayString);*/
        //HttpServletRequest r = PortalUtil.getHttpServletRequest(actionRequest);

        if (birthdayDate.compareTo(new Date()) > 0) {
            SessionErrors.add(actionRequest, "error-date-message");
            //actionResponse.sendRedirect(SAVE_EMPLOYEE_URL + employeeId);
            System.out.println("Redirect to " + createRedirectURL(actionRequest, employeeId));
            actionResponse.sendRedirect(createRedirectURL(actionRequest, employeeId).toString());
            return;
        }else {
            SessionErrors.clear(actionRequest);
        }

        long positionId   = ParamUtil.getLong(actionRequest,   "positionTypesId");
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
