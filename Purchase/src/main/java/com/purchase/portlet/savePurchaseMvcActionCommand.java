package com.purchase.portlet;

import com.db.model.ElectroEmployee;
import com.db.model.Electronic;
import com.db.model.Purchase;
import com.db.service.*;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.purchase.constants.PurchaseControllerPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
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
    private int errors;
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        SessionMessages.add(actionRequest, ((LiferayPortletConfig)portletConfig).getPortletId() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

        savePurchase(actionRequest, actionResponse);
    }

    private PortletURL createRedirectURL(ActionRequest actionRequest, long purchaseId) {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);

        PortletURL redirectURL = PortletURLFactoryUtil.create(
                actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", "/purchase/create");
        redirectURL.setParameter("purchaseId", String.valueOf(purchaseId)); // note the changed parameter name

        return redirectURL;
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
        DynamicQuery userQuery = ElectroEmployeeLocalServiceUtil.dynamicQuery();
        userQuery.add(RestrictionsFactoryUtil.and
                (
                RestrictionsFactoryUtil.eq("employeeId", employeeId),
                RestrictionsFactoryUtil.eq("etypeId", electronicTypeId))
                );

        //Date check
        if (purchaseDate.compareTo(new Date()) > 0) {
            SessionErrors.add(actionRequest, "error-date-message");
            //actionResponse.sendRedirect(SAVE_PURCHASE_URL);
            System.out.println("Redirect to " + createRedirectURL(actionRequest, purchaseId));
            actionResponse.sendRedirect(createRedirectURL(actionRequest, purchaseId).toString());
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
                //actionResponse.sendRedirect(SAVE_PURCHASE_URL);
                System.out.println("Redirect to " + createRedirectURL(actionRequest, purchaseId));
                actionResponse.sendRedirect(createRedirectURL(actionRequest, purchaseId).toString());
                errors++;
                return;
            }
        } catch (SystemException e) {
            System.out.println(e);
        }

        //Electronic count check
        if (electronicCount < 1) {
            SessionErrors.add(actionRequest, "error-electronic-count-message");
            //actionResponse.sendRedirect(SAVE_PURCHASE_URL);
            System.out.println("Redirect to " + createRedirectURL(actionRequest, purchaseId));
            actionResponse.sendRedirect(createRedirectURL(actionRequest, purchaseId).toString());
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
