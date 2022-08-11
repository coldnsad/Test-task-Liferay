package com.purchase.portlet;

import com.db.model.Electronic;
import com.db.model.Purchase;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.PurchaseLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.purchase.constants.PurchaseControllerPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

	public void importFromCsv(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException, IOException {

		UploadPortletRequest upreq = PortalUtil.getUploadPortletRequest(actionRequest);
		File file = upreq.getFile("fileupload");
		if (!validateFile(file)) System.out.println("Wrong format");
	}


	public static boolean validateFile(File file) throws ParseException, IOException {

		String fileExtension = getFileExtension(file);
		if (fileExtension.equals("csv")) {
			System.out.println("Parse it!!");
			addPurchasesFromCsv(file);
			return true;
		} else if (fileExtension.equals("zip")) {
			getFilesFromZip(file);
			return true;
		}
		return false;
	}

	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			// ХХХХХ.txt -> txt
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else return "";
	}

	public static String getFileRawName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf(".")).toLowerCase();
	}

	private static List<Map<String, String>> getPurchasesFromCsv(File file) {

		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> purchaseData = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				if (values.length == 4) {
					/*System.out.println();
					for (int i = 0; i < values.length; i++) {
						System.out.print(values[i] + " ");
					}*/
					purchaseData.put("electronicId", values[0]);
					purchaseData.put("employeeId", values[1]);
					purchaseData.put("purchaseDate", values[2]);
					purchaseData.put("purchaseTypeId", values[3]);

					System.out.println();
					result.add(new HashMap<>(purchaseData));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

		return result;
	}
	private static boolean addPurchasesFromCsv(File file) throws ParseException {

		Purchase purchase = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<Map<String, String>> purchases = getPurchasesFromCsv(file);

		for (Map<String, String> map : purchases) {
			System.out.println();
			for (Map.Entry<String, String> pair : map.entrySet()) {
				System.out.print(pair.getKey() + "-" + pair.getValue() + " ");
			}
		}
		for (Map<String, String> element : purchases) {
			purchase = PurchaseLocalServiceUtil.createPurchase(CounterLocalServiceUtil.
					increment(Purchase.class.getName()));

			purchase.setElectronicId(Long.parseLong(element.get("electronicId")));
			purchase.setEmployeeId(Long.parseLong(element.get("employeeId")));
			purchase.setPurchaseDate(sdf.parse(element.get("purchaseDate")));
			purchase.setPurchaseTypeId(Long.parseLong(element.get("purchaseTypeId")));

			PurchaseLocalServiceUtil.addPurchase(purchase);
		}
		return true;
	}



	private static void getFilesFromZip(File file) throws IOException, ParseException {
		byte[] buffer = new byte[2048];

		String finalPath = file.getParentFile().getPath() + "\\csv";
		File directoryForCsv = new File(finalPath);
		directoryForCsv.mkdir();

		Path outDir = Paths.get(finalPath);

		System.out.println(outDir);

		String zipFileName = file.getPath();

		try (FileInputStream fis = new FileInputStream(zipFileName);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 ZipInputStream stream = new ZipInputStream(bis)) {

			ZipEntry entry;
			while ((entry = stream.getNextEntry()) != null) {


				//Check on right name of file according to table in db
				if(!getFileRawName(entry.getName()).equals("purchases")){
					System.out.println(getFileRawName(entry.getName()));
					continue;
				}
				Path filePath = outDir.resolve(entry.getName());

				try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
					 BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {

					int len;
					while ((len = stream.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (final File fileEntry : directoryForCsv.listFiles()) {
			if (!fileEntry.isDirectory()) {
				validateFile(fileEntry);
			}
		}
	}
}