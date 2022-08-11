package com.electronics.portlet;

import com.db.model.Electronic;
import com.db.model.Employee;
import com.db.service.ElectronicLocalServiceUtil;
import com.db.service.EmployeeLocalServiceUtil;
import com.electronics.constants.ElectronicControllerPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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

	public void importFromCsv(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException, IOException {

		UploadPortletRequest upreq = PortalUtil.getUploadPortletRequest(actionRequest);
		File file = upreq.getFile("fileupload");
		if (!validateFile(file)) System.out.println("Wrong format");
	}


	public static boolean validateFile(File file) throws ParseException, IOException {

		String fileExtension = getFileExtension(file);
		if (fileExtension.equals("csv")) {
			System.out.println("Parse it!!");
			addElectronicFromCsv(file);
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

	private static List<Map<String, String>> getElectronicsFromCsv(File file) {

		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> electronoicData = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				if (values.length == 7) {
					/*System.out.println();
					for (int i = 0; i < values.length; i++) {
						System.out.print(values[i] + " ");
					}*/
					electronoicData.put("name", values[0]);
					electronoicData.put("etype", values[1]);
					electronoicData.put("price", values[2]);
					electronoicData.put("count", values[3]);
					electronoicData.put("instock", values[4]);
					electronoicData.put("archive", values[5]);
					electronoicData.put("description", values[6]);

					/*for (Map.Entry<String, String> map: employeeData.entrySet()) {
						System.out.print(map.getKey() + "-" + map.getValue() + " ");
					}*/
					System.out.println();
					result.add(new HashMap<>(electronoicData));
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
	private static boolean addElectronicFromCsv(File file) throws ParseException {

		Electronic electronic = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<Map<String, String>> electronics = getElectronicsFromCsv(file);

		for (Map<String, String> map : electronics) {
			System.out.println();
			for (Map.Entry<String, String> pair : map.entrySet()) {
				System.out.print(pair.getKey() + "-" + pair.getValue() + " ");
			}
		}
		for (Map<String, String> element : electronics) {
			electronic = ElectronicLocalServiceUtil.createElectronic(CounterLocalServiceUtil.
					increment(Electronic.class.getName()));

			electronic.setName(element.get("name"));
			electronic.setEtype(Long.parseLong(element.get("etype")));
			electronic.setPrice(Long.parseLong(element.get("price")));
			electronic.setCount(Integer.parseInt(element.get("count")));
			electronic.setInstock(Boolean.parseBoolean(element.get("instock")));
			electronic.setArchive(Boolean.parseBoolean(element.get("archive")));
			electronic.setDescription(element.get("description"));

			ElectronicLocalServiceUtil.addElectronic(electronic);
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
				if(!getFileRawName(entry.getName()).equals("electronic")){
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