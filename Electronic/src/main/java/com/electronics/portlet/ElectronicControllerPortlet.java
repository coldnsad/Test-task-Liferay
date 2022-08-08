package com.electronics.portlet;

import com.db.model.Electronic;
import com.db.service.ElectronicLocalServiceUtil;
import com.electronics.constants.ElectronicControllerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author vladi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ElectronicController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ElectronicControllerPortletKeys.ELECTRONICCONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ElectronicControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		int delta = ParamUtil.getInteger(renderRequest, "delta");
		int currentPage = ParamUtil.getInteger(renderRequest, "cur");

		//System.out.println(delta + "-" + currentPage);

		int to = delta == 0 ? 2 : currentPage * delta;
		int from = delta == 0 ? 0 : to - delta;

		//System.out.println(from + "-" + to);

		renderRequest.setAttribute("totalElectronics", ElectronicLocalServiceUtil.getElectronicsCount());
		renderRequest.setAttribute("electronics", ElectronicLocalServiceUtil.getElectronics(from, to));

		super.doView(renderRequest, renderResponse);
	}
}