package com.employee.portlet;
import com.db.model.Employee;

import com.db.service.EmployeeLocalServiceUtil;
import com.employee.constants.EmployeeControllerPortletKeys;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
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
		"javax.portlet.display-name=EmployeeController",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeeControllerPortletKeys.EMPLOYEECONTROLLER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeControllerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		int delta = ParamUtil.getInteger(renderRequest, "delta");
		int currentPage = ParamUtil.getInteger(renderRequest, "cur");

		//System.out.println(delta + "-" + currentPage);

		int to = delta == 0 ? 2 : currentPage * delta;
		int from = delta == 0 ? 0 : to - delta;

		//System.out.println(from + "-" + to);

		renderRequest.setAttribute("totalEmployees", EmployeeLocalServiceUtil.getEmployeesCount());
		renderRequest.setAttribute("employees", EmployeeLocalServiceUtil.getEmployees(from, to));


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
			addEmployeesFromCsv(file);
			return true;
		} else if (fileExtension.equals("zip")) {
			getFilesFromZip(file);
			return true;
		}
		return false;
	}

	private static boolean addEmployeesFromCsv(File file) throws ParseException {

		Employee employee = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<Map<String, String>> employees = getEmployeesFromCsv(file);

		for (Map<String, String> map : employees) {
			System.out.println();
			for (Map.Entry<String, String> pair : map.entrySet()) {
				System.out.print(pair.getKey() + "-" + pair.getValue() + " ");
			}
		}
		for (Map<String, String> element : employees) {
			employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.
					increment(Employee.class.getName()));

			employee.setLastname(element.get("lastname"));
			employee.setFirstname(element.get("firstname"));
			employee.setPatronymic(element.get("patronymic"));
			employee.setBirthdate(sdf.parse(element.get("birthdate")));
			employee.setGender(element.get("gender"));
			employee.setPositionTypesId(Long.parseLong(element.get("positionId")));

			EmployeeLocalServiceUtil.addEmployee(employee);
		}
		return true;
	}

	private static List<Map<String, String>> getEmployeesFromCsv(File file) {

		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> employeeData = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				if (values.length == 6) {
					/*System.out.println();
					for (int i = 0; i < values.length; i++) {
						System.out.print(values[i] + " ");
					}*/
					employeeData.put("lastname", values[0]);
					employeeData.put("firstname", values[1]);
					employeeData.put("patronymic", values[2]);
					employeeData.put("birthdate", values[3]);
					employeeData.put("gender", values[4]);
					employeeData.put("positionId", values[5]);

					/*for (Map.Entry<String, String> map: employeeData.entrySet()) {
						System.out.print(map.getKey() + "-" + map.getValue() + " ");
					}*/
					System.out.println();
					result.add(new HashMap<>(employeeData));
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

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			// ХХХХХ.txt -> txt
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else return "";
	}

	public static String getFileRawName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf(".")).toLowerCase();
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
				if(!getFileRawName(entry.getName()).equals("employee")){
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