package com.purchase.portlet;


import com.db.model.ElectroEmployee;
import com.db.model.Electronic;
import com.db.model.Employee;
import com.db.model.Purchase;
import com.db.service.*;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.purchase.constants.PurchaseControllerPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PurchaseControllerPortletKeys.PURCHASECONTROLLER,
                "mvc.command.name=savePurchase"
        },
        service = MVCActionCommand.class
)
public class savePurchaseMvcActionCommand extends BaseMVCActionCommand {

    private final String SAVE_PURCHASE_URL = "http://localhost:8080/web/guest/purchases?p_p_id=com_purchase_PurchaseControllerPortlet_INSTANCE_cnjk&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_purchase_PurchaseControllerPortlet_INSTANCE_cnjk_mvcRenderCommandName=%2Fpurchase%2Fcreate";
    private int errors;
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        SessionMessages.add(actionRequest, ((LiferayPortletConfig)portletConfig).getPortletId() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

        savePurchase(actionRequest, actionResponse);
    }

    private void savePurchase(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortalException {

        Purchase purchase = null;
        long purchaseId = ParamUtil.getLong(actionRequest, "purchaseId", 0);
        System.out.println("PurchaseId id: " + purchaseId);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        long electronicId = ParamUtil.getLong(actionRequest, "electronicId");
        long employeeId  = ParamUtil.getLong(actionRequest, "employeeId");
        Date purchaseDate = ParamUtil.getDate(actionRequest, "purchaseDate", sdf);
        long purchaseTypeId  = ParamUtil.getLong(actionRequest, "purchaseTypeId");

        Electronic chosenElectronic = ElectronicLocalServiceUtil.fetchElectronic(electronicId);
        long electronicTypeId = chosenElectronic.getEtype();
        int electronicCount = chosenElectronic.getCount();

        //SELECT * from ElectroEmployee WHERE (ElectroEmployee.employeeID = employeeId) AND (ElectroEmployee.etype = electronicTypeId)
        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(ElectroEmployee.class, "electroEmployee",
                PortalClassLoaderUtil.getClassLoader());
        userQuery.add(RestrictionsFactoryUtil.and
                (
                RestrictionsFactoryUtil.eq("electroEmployee.employeeId", employeeId),
                RestrictionsFactoryUtil.eq("electroEmployee.etypeId", electronicTypeId))
                );

        //Date check
        if (purchaseDate.compareTo(new Date()) > 0) {
            SessionErrors.add(actionRequest, "error-date-message");
            actionResponse.sendRedirect(SAVE_PURCHASE_URL);
            errors++;
            return;
        }

        //Employee Etype check
        try {
            List<ElectroEmployee> electroEmployee = ElectroEmployeeLocalServiceUtil.dynamicQuery(userQuery);
            for (ElectroEmployee element:
                 electroEmployee) {
                System.out.println(element.getEmployeeId() + " " + element.getEtypeId());
            }

            if (electroEmployee.size() < 1) {
                SessionErrors.add(actionRequest, "error-employee-message");
                actionResponse.sendRedirect(SAVE_PURCHASE_URL);
                errors++;
                return;
            }
        } catch (SystemException e) {
            System.out.println(e);
        }

        //Electronic count check
        if (electronicCount < 1) {
            SessionErrors.add(actionRequest, "error-electronic-count-message");
            actionResponse.sendRedirect(SAVE_PURCHASE_URL);
            errors++;
            return;
        }

        if (purchaseId > 0) {
            purchase = PurchaseLocalServiceUtil.fetchPurchase(purchaseId);
        }else {
            purchase = PurchaseLocalServiceUtil.createPurchase(CounterLocalServiceUtil.
                    increment(Purchase.class.getName()));
        }

        purchase.setElectronicId(electronicId);
        purchase.setEmployeeId(employeeId);
        purchase.setPurchaseDate(purchaseDate);
        purchase.setPurchaseTypeId(purchaseTypeId);

        PurchaseLocalServiceUtil.updatePurchase(purchase);

        chosenElectronic.setCount(electronicCount - 1);
        ElectronicLocalServiceUtil.updateElectronic(chosenElectronic);

        if (errors == 0) SessionErrors.clear(actionRequest);
    }
}
