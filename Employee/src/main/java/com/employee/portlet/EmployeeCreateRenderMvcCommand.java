package com.employee.portlet;

import com.db.model.PositionType;
import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.EmployeeServiceUtil;
import com.db.service.PositionTypeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + EmployeeControllerPortletKeys.EMPLOYEECONTROLLER,
            "mvc.command.name=/employee/create"
        }
)
public class EmployeeCreateRenderMvcCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        /*long employeeId = ParamUtil.getLong(renderRequest, "employeeId", 0);

        if (employeeId > 0){
            try {
                renderRequest.setAttribute("employee", EmployeeLocalServiceUtil.getEmployee(employeeId));
                System.out.println("Date date: " + EmployeeLocalServiceUtil.getEmployee(employeeId).getBirthdate());
                System.out.println("String date: " + EmployeeLocalServiceUtil.getEmployee(employeeId).formatDate());
            } catch (PortalException e) {
                throw new RuntimeException(e);
            }
        }*/

        List<PositionType> positionTypeList = PositionTypeLocalServiceUtil.
                getPositionTypes(0,PositionTypeLocalServiceUtil.getPositionTypesCount());

        Map<Long, String> positionTypesMap = new HashMap<>();
        for (PositionType positionType:
                positionTypeList) {
            positionTypesMap.put(positionType.getId(), positionType.getName());
        }

        //Debug
		/*for (Map.Entry<Long,String> pair:
				positionTypesMap.entrySet()) {
			System.out.println(pair.getKey() + ":" + pair.getValue());
		}*/
        //END Debug

        renderRequest.setAttribute("positionTypes", positionTypesMap);

        return "/createEmployee.jsp";
    }
}
