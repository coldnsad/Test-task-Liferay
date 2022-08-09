package com.purchase.portlet;

import com.db.model.Electronic;
import com.db.model.Employee;
import com.db.model.PurchaseType;
import com.db.service.ElectroEmployeeLocalServiceUtil;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.PurchaseTypeLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.purchase.constants.PurchaseControllerPortletKeys;
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
                "javax.portlet.name=" + PurchaseControllerPortletKeys.PURCHASECONTROLLER,
                "mvc.command.name=/purchase/create"
        }
)

public class PurchaseCreateRenderMvcCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        List<Electronic> electronicTypeList = ElectronicLocalServiceUtil.
                getElectronics(0,ElectronicLocalServiceUtil.getElectronicsCount());

        Map<Long, String> electronicsMap = new HashMap<>();
        for (Electronic electronic:
                electronicTypeList) {
            electronicsMap.put(electronic.getId(), electronic.getName());
        }

        List<Employee> employeesList = EmployeeLocalServiceUtil.
                getEmployees(0,EmployeeLocalServiceUtil.getEmployeesCount());

        Map<Long, String> employeesMap = new HashMap<>();
        for (Employee employee:
                employeesList) {
            employeesMap.put(employee.getId(), employee.getLastname() + " " + employee.getFirstname() + " "
                    + employee.getPatronymic());
        }

        List<PurchaseType> purchaseTypeList = PurchaseTypeLocalServiceUtil.
                getPurchaseTypes(0,PurchaseTypeLocalServiceUtil.getPurchaseTypesCount());

        Map<Long, String> purchaseTypeMap = new HashMap<>();
        for (PurchaseType purchaseType:
                purchaseTypeList) {
            purchaseTypeMap.put(purchaseType.getId(), purchaseType.getName());
        }

        renderRequest.setAttribute("electronics", electronicsMap);
        renderRequest.setAttribute("employees", employeesMap);
        renderRequest.setAttribute("purchaseTypes", purchaseTypeMap);

        return "/createPurchase.jsp";
    }
}
