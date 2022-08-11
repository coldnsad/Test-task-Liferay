package com.statistics.portlet;

import com.db.model.*;
import com.db.service.*;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.statistics.constants.StatisticControllerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

/**
 * @author user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=StatisticController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StatisticControllerPortletKeys.STATISTICCONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class StatisticControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {


		renderRequest.setAttribute("tv-seller", getBestTvSellerDataInAMonth());
		try {
			renderRequest.setAttribute("amount-purchases-paid-by-a-card", getAmountOfPurchasesPaidByCard());
		} catch (PortalException e) {
			throw new RuntimeException(e);
		}
		renderRequest.setAttribute("amount-archive-tv-smartphones", getAmountOfPurchasesOfAchiveElectronic());

		super.doView(renderRequest, renderResponse);
	}


	private String[] getAmountOfPurchasesOfAchiveElectronic(){

		String[] outputArray = new String[1];
		//ID category TV And Smartphones
		DynamicQuery queryTVId = ElectroTypeLocalServiceUtil.dynamicQuery();
		queryTVId.add(RestrictionsFactoryUtil.like("name", "TV"));
		List<ElectroType> electroTypeList = ElectroEmployeeLocalServiceUtil.dynamicQuery(queryTVId);


		long tvId = 0;
		if(electroTypeList.size() != 0) {
			tvId = electroTypeList.get(0).getId();
		}

		DynamicQuery querySmartphoneId = ElectroTypeLocalServiceUtil.dynamicQuery();
		querySmartphoneId.add(RestrictionsFactoryUtil.like("name", "Smartphones"));
		electroTypeList = ElectroEmployeeLocalServiceUtil.dynamicQuery(querySmartphoneId);

		long smartphonesId = 0;
		if(electroTypeList.size() != 0) {
			smartphonesId = electroTypeList.get(0).getId();
		}
		//Archive TV and Smartphones electronic
		long electronicTypeId = 0;
		List<Electronic> archiveTvSmartphones = new ArrayList<>();
		List<Electronic> electronics = ElectronicLocalServiceUtil.getElectronics(0, ElectronicLocalServiceUtil.getElectronicsCount());
		for (Electronic electronic:electronics) {
			electronicTypeId = electronic.getEtype();
			if ( ((electronicTypeId == tvId) || (electronicTypeId == smartphonesId)) && (electronic.getArchive() == true) ) {
				archiveTvSmartphones.add(electronic);
			}
		}

		//Amount of archive TV/Smartphones electronic
		long result = 0;
		for (Electronic electronic:
				archiveTvSmartphones) {
			result+=electronic.getPrice();
		}


		outputArray[0] = result / 100 + "rub";

		return outputArray;
	}
	private String[] getAmountOfPurchasesPaidByCard() throws PortalException {

		String[] outputArray = new String[1];

		//ID category Card
		DynamicQuery queryPurchaseTypeId = PurchaseTypeLocalServiceUtil.dynamicQuery();
		queryPurchaseTypeId.add(RestrictionsFactoryUtil.like("name", "Card"));
		List<PurchaseType> purchaseTypeList = PurchaseTypeLocalServiceUtil.dynamicQuery(queryPurchaseTypeId);

		long cardPurchaseId = 0;
		if(purchaseTypeList.size() != 0) {
			cardPurchaseId = purchaseTypeList.get(0).getId();
		}else{
			outputArray[0] = "0rub";
		}

		//Purchases paid by a card
		long purchaseTypeId = 0;
		List<Purchase> cardPurchases = new ArrayList<>();
		List<Purchase> purchases = PurchaseLocalServiceUtil.getPurchases(0, PurchaseLocalServiceUtil.getPurchasesCount());
		for (Purchase purchase:
				purchases) {
			purchaseTypeId = purchase.getPurchaseTypeId();

			if (purchaseTypeId == cardPurchaseId) {
				cardPurchases.add(purchase);
			}
		}

		//Amount of card purchases
		long result = 0;
		for (Purchase purchase:
				cardPurchases) {
			result+=ElectronicLocalServiceUtil.getElectronic(purchase.getElectronicId()).getPrice();
		}


		outputArray[0] = result / 100 + "rub";

		return outputArray;
	}

	private String[] getBestTvSellerDataInAMonth(){

		String[] outputArray = new String[2];
		//First Day of current month
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDayOfMonth = null;
		try {
			firstDayOfMonth = sdf.parse(
					YearMonth.now().
							atDay(1).
							toString()
			);
			System.out.println(firstDayOfMonth);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}


		//ID category TV
		DynamicQuery queryElectroId = ElectroTypeLocalServiceUtil.dynamicQuery();
		queryElectroId.add(RestrictionsFactoryUtil.like("name", "TV"));
		List<ElectroType> electroTypeList = ElectroEmployeeLocalServiceUtil.dynamicQuery(queryElectroId);

		long tvId = 0;
		if(electroTypeList.size() != 0) {
			tvId = electroTypeList.get(0).getId();
			System.out.println("-------------------------------");
			System.out.println("TvID : " + tvId);
			System.out.println("-------------------------------");
		}else{
			outputArray[0] = "There is no the best TV seller this month";
			outputArray[1] = "0rub";
			return outputArray;
		}

		//Tv Purchases in current month
		long electronicTypeId = 0;
		List<Purchase> tvPurchases = new ArrayList<>();
		List<Purchase> purchases = PurchaseLocalServiceUtil.getPurchases(0, PurchaseLocalServiceUtil.getPurchasesCount());
		for (Purchase purchase:
				purchases) {
			try {
				electronicTypeId = ElectronicLocalServiceUtil.getElectronic(purchase.getElectronicId()).getEtype();
			} catch (PortalException e) {
				throw new RuntimeException(e);
			}

			if ( (electronicTypeId == tvId) && (purchase.getPurchaseDate().compareTo(firstDayOfMonth) > 0) ) {
				tvPurchases.add(purchase);
			}
		}

		for (Purchase tvPurchase:
				tvPurchases) {
			try {
				System.out.println("Name: " + EmployeeLocalServiceUtil.getEmployee(tvPurchase.getEmployeeId()).getLastname() + ";" + "Date: " + tvPurchase.getPurchaseDate());
			} catch (PortalException e) {
				throw new RuntimeException(e);
			}
		}


		//Maximum sales amount per each Tv Employee in a month
		Map<Employee, Long> employeeAmount = new HashMap<>();
		Employee currentEmployee = null;
		for (Purchase tvPurchase:
				tvPurchases) {
			try {
				currentEmployee = EmployeeLocalServiceUtil.getEmployee(tvPurchase.getEmployeeId());

				if (employeeAmount.containsKey(currentEmployee)) {
					employeeAmount.put(currentEmployee, employeeAmount.get(currentEmployee) + ElectronicLocalServiceUtil.getElectronic(tvPurchase.getElectronicId()).getPrice());
				}else{
					employeeAmount.put(currentEmployee, ElectronicLocalServiceUtil.getElectronic(tvPurchase.getElectronicId()).getPrice());
				}
			} catch (PortalException e) {
				throw new RuntimeException(e);
			}
		}


		//The Best TV seller this month
		Employee bestSeller = null;
		long max = 0;
		for (Map.Entry<Employee, Long> pair:
				employeeAmount.entrySet()) {
			if (pair.getValue() > max) {
				max = pair.getValue();
				bestSeller = pair.getKey();
			}
			System.out.println("Name: " + pair.getKey().getLastname() + ";" + "Amount(kopecs): " + pair.getValue());
		}

		if (bestSeller != null) {
			outputArray[0] = bestSeller.showEmployee();
			outputArray[1] = max / 100 + "rub";
		}
		else{
			outputArray[0] = "There is no the best TV seller this month";
			outputArray[1] = "rub";
		}
		return outputArray;
	}
}