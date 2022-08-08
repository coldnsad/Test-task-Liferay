package com.electronics.portlet;


import com.db.model.Electronic;
import com.db.model.Employee;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.electronics.constants.ElectronicControllerPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ElectronicControllerPortletKeys.ELECTRONICCONTROLLER,
                "mvc.command.name=saveElectronic"
        },
        service = MVCActionCommand.class
)

public class SaveElectronicActionMvcCommand extends BaseMVCActionCommand {
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        saveElectronic(actionRequest, actionResponse);
    }

    public void saveElectronic(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException, IOException {

        Electronic electronic = null;
        long electronicId = ParamUtil.getLong(actionRequest, "electronicId", 0);
        System.out.println("Electronic id: " + electronicId);

        String name   = ParamUtil.getString(actionRequest, "name");
        long etype  = ParamUtil.getLong(actionRequest, "etype");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean instock = ParamUtil.getBoolean(actionRequest, "instock");
        boolean archive = ParamUtil.getBoolean(actionRequest, "archive");
        String description = ParamUtil.getString(actionRequest, "description");

        System.out.println("-----------------------------------");
        System.out.println(name + " - " + etype + " - " + price + " - " + count + " - " + instock + "-" + archive + "-" + description);

        if (electronicId > 0) {
            electronic = ElectronicLocalServiceUtil.fetchElectronic(electronicId);
        }else {
            electronic = ElectronicLocalServiceUtil.createElectronic(CounterLocalServiceUtil.
                    increment(Electronic.class.getName()));
        }

        electronic.setName(name);
        electronic.setEtype(etype);
        electronic.setPrice(price);
        electronic.setCount(count);
        electronic.setInstock(instock);
        electronic.setArchive(archive);
        electronic.setDescription(description);

        ElectronicLocalServiceUtil.updateElectronic(electronic);

    }
}
