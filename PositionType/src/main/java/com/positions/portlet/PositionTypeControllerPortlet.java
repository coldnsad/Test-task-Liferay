package com.positions.portlet;

import com.db.model.PositionType;
import com.db.service.PositionTypeLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.positions.constants.PositionTypeControllerPortletKeys;

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
		"javax.portlet.display-name=PositionTypeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PositionTypeControllerPortletKeys.POSITIONTYPECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PositionTypeControllerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);


	}

	public void savePosition(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		System.out.println("Received in the controller(savePosition)");
	}
}