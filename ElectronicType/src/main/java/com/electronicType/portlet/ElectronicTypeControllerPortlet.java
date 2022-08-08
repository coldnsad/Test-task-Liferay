package com.electronicType.portlet;

import com.db.model.ElectroType;
import com.db.service.ElectroTypeLocalServiceUtil;
import com.electronicType.constants.ElectronicTypeControllerPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

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
		"javax.portlet.display-name=ElectronicTypeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ElectronicTypeControllerPortletKeys.ELECTRONICTYPECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ElectronicTypeControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	public void saveElectroType(ActionRequest actionRequest, ActionResponse actionResponse){

		String name = ParamUtil.get(actionRequest,"name", "");
		System.out.println(name);

		ElectroType electroType = ElectroTypeLocalServiceUtil.
				createElectroType(CounterLocalServiceUtil.increment(ElectroType.class.getName()));
		electroType.setName(name);

		//Get all PositionTypes
		//PositionTypeLocalServiceUtil.getPositionTypes(0, PositionTypeLocalServiceUtil.getPositionTypesCount());

		ElectroTypeLocalServiceUtil.addElectroType(electroType);
		System.out.println("ElectroType added");
	}

}