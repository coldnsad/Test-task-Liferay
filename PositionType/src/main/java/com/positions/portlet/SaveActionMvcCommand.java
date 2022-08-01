package com.positions.portlet;

import com.db.model.PositionType;
import com.db.service.PositionTypeLocalServiceUtil;
import com.db.service.PositionTypeLocalServiceWrapper;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.positions.constants.PositionTypeControllerPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PositionTypeControllerPortletKeys.POSITIONTYPECONTROLLER,
                "mvc.command.name=savePosition" },
        service = MVCActionCommand.class
)

public class SaveActionMvcCommand extends BaseMVCActionCommand {

    private Long userId;

    /*private PositionTypeService _positionTypeService;
    @Reference(unbind = "-")
    protected void setGroupLocalService(PositionTypeService positionTypeService) {
        _positionTypeService = positionTypeService;
    }*/
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String name = ParamUtil.get(actionRequest,"name", "");
        System.out.println("SaveActionMvcCommand.doProcessAction()\n");
        System.out.println(name);

        PositionType positionType = PositionTypeLocalServiceUtil.
                createPositionType(CounterLocalServiceUtil.increment(PositionType.class.getName()));
        positionType.setName(name);

        PositionTypeLocalServiceUtil.addPositionType(positionType);
        System.out.println("Position type added");
    }
}
