package com.electronics.portlet;


import com.db.model.ElectroType;
import com.db.model.Electronic;
import com.db.model.PositionType;
import com.db.service.ElectroTypeLocalServiceUtil;
import com.db.service.PositionTypeLocalServiceUtil;
import com.electronics.constants.ElectronicControllerPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
                "javax.portlet.name=" + ElectronicControllerPortletKeys.ELECTRONICCONTROLLER,
                "mvc.command.name=/electronic/create"
        }
)
public class ElectronicCreateRenderMvcCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        List<ElectroType> electroTypeList = ElectroTypeLocalServiceUtil.
                getElectroTypes(0,ElectroTypeLocalServiceUtil.getElectroTypesCount());

        Map<Long, String> electroTypesMap = new HashMap<>();
        for (ElectroType electroType:
                electroTypeList) {
            electroTypesMap.put(electroType.getId(), electroType.getName());
        }

        //Debug
		/*for (Map.Entry<Long,String> pair:
				positionTypesMap.entrySet()) {
			System.out.println(pair.getKey() + ":" + pair.getValue());
		}*/
        //END Debug

        renderRequest.setAttribute("electronicTypes", electroTypesMap);

        return "/createElectronic.jsp";
    }
}
