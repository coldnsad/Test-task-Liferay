package com.purchaseType.portlet;

import com.db.model.PurchaseType;
import com.db.service.PurchaseTypeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.purchaseType.constants.PurchaseTypeControllerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PurchaseTypeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PurchaseTypeControllerPortletKeys.PURCHASETYPECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PurchaseTypeControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	public void savePurchaseType(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {

		String name = ParamUtil.get(actionRequest,"name", "");

		PurchaseType purchaseType = PurchaseTypeLocalServiceUtil.
				createPurchaseType(CounterLocalServiceUtil.increment(PurchaseType.class.getName()));
		purchaseType.setName(name);

		PurchaseTypeLocalServiceUtil.addPurchaseType(purchaseType);
		System.out.println("purchaseType added");
	}
}