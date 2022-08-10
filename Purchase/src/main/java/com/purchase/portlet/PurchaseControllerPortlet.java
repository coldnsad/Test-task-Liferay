package com.purchase.portlet;

import com.db.service.EmployeeLocalServiceUtil;
import com.db.service.PurchaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.purchase.constants.PurchaseControllerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.display-name=PurchaseController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PurchaseControllerPortletKeys.PURCHASECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PurchaseControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		/*int delta = ParamUtil.getInteger(renderRequest, "delta");
		int currentPage = ParamUtil.getInteger(renderRequest, "cur");*/

		//System.out.println(delta + "-" + currentPage);

		/*int to = delta == 0 ? 2 : currentPage * delta;
		int from = delta == 0 ? 0 : to - delta;*/

		//System.out.println(from + "-" + to);

		renderRequest.setAttribute("totalPurchases", PurchaseLocalServiceUtil.getPurchasesCount());
		try {
			renderRequest.setAttribute("purchases", PurchaseLocalServiceUtil.getPurchases());
		} catch (PortalException e) {
			throw new RuntimeException(e);
		}

		super.doView(renderRequest, renderResponse);
	}
}