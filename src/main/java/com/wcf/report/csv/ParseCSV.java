/**
 * 
 */
package com.wcf.report.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.wcf.report.model.Employee;
import com.wcf.report.model.MobileUsage;
import com.wcf.report.utils.FileUtils;

/**
 * @author Ubaid Raja
 * @since Sep 3, 2021
 */
public class ParseCSV {

	private static Logger LOGGER = LoggerFactory.getLogger(ParseCSV.class);
	/**
	 * To verify if year is available in the dataset and can be queried
	 * 
	 * @param yearInput
	 * @param allowedYears
	 * @return isYearAvailable
	 */
	private boolean isYearAvailable(Integer yearInput, Set<Integer> allowedYears) {
		boolean isYearAvailable = false;
		for (Integer s : allowedYears) {
//			System.out.println("Input year is : " + yearInput);
//			System.out.println("Year is : " + s);
			if (yearInput.equals(s)) {
				isYearAvailable = true;
				break;
			}
		}
		return isYearAvailable;
	}

	/**
	 * This methods takes year, usageFileName and list of employees as input
	 * 
	 * @param yearInput
	 * @param usageFileName
	 * @param employees
	 * @return Map of Employee with List of MobileUsage
	 * @throws IOException
	 */
	public Map<Employee, List<MobileUsage>> getEmployeeMobileUsageData(Integer yearInput, String usageFileName,
			List<Employee> employees) throws IOException {
		try {
			List<String[]> res = readCsvFileCustomSeparator(usageFileName, ',');
			Integer day = 0, month = 0, year = 0;
			Map<Employee, List<MobileUsage>> usage = new HashedMap();
			Set<Integer> allowedYears = new HashSet<Integer>();

			for (String[] obj : res) {
				MobileUsage value = new MobileUsage();
				value.setEmployeeId(Integer.valueOf(obj[0]));
				String origDate = obj[1];
				StringTokenizer st = new StringTokenizer(origDate, "/");
				while (st.hasMoreTokens()) {
					month = Integer.valueOf(st.nextToken());
					day = Integer.valueOf(st.nextToken());
					year = Integer.valueOf(st.nextToken());
				}
				value.setDay(day);
				value.setYear(year);
				value.setMonth(month);

				allowedYears.add(year);
				String totalMinutes = obj[2];
				value.setTotalMinutes(Integer.valueOf(totalMinutes));
				String totalData = obj[3];
				value.setTotalData(Double.valueOf(totalData));
				addToListInMap(usage, findEmployeeById(employees, value.getEmployeeId()), value);
			}

			if (!isYearAvailable(yearInput, allowedYears)) {
				throw new IllegalArgumentException("Unable to fetch records for the specific input year of " + yearInput);
			}
			return usage;
		} catch (IllegalStateException e) {
			LOGGER.error("IllegalStateException "+e.getMessage());
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException "+e.getMessage());
		} catch (CsvException e) {
			LOGGER.error("CsvException "+e.getMessage());
		}
		return null;
	}

	/**
	 * @param listEmployees
	 * @param employeeId
	 * @return
	 */
	public static Employee findEmployeeById(Collection<Employee> listEmployees, Integer employeeId) {
		return listEmployees.stream().filter(emp -> employeeId.equals(emp.getEmployeeId())).findFirst().orElse(null);
	}


	/**
	 * @param usages
	 * @param employee
	 * @param usage
	 */
	private static void addToListInMap(Map<Employee, List<MobileUsage>> usages, Employee employee, MobileUsage usage) {
		if (usages.containsKey(employee)) {
			List<MobileUsage> list = usages.get(employee);
			list.add(usage);
			usages.put(employee, list);
		} else {
			List<MobileUsage> list = new ArrayList<MobileUsage>();
			list.add(usage);
			usages.put(employee, list);
		}
	}

	/**
	 * Read CSV by using custom separator
	 * @param fileName
	 * @param separator
	 * @return
	 * @throws IOException
	 * @throws CsvException
	 */
	private List<String[]> readCsvFileCustomSeparator(String fileName, char separator)
			throws IOException, CsvException {

		try {
			List<String[]> r;
//			System.out.println("getResource : " + fileName);
			FileUtils app = new FileUtils();
			Reader fileReader = new InputStreamReader(app.getFileFromResourceAsStream(fileName));
			// custom separator
			CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
			try (CSVReader reader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser) // custom CSV parser
					.withSkipLines(1) // skip the first line, header info
					.build()) {
				r = reader.readAll();
			}
			return r;
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found exception : " + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IO exception : " + e.getMessage());
		} catch (CsvException e) {
			LOGGER.error("CSV exception :  " + e.getMessage());
		}
		return null;
	}

	/**
	 * Read CSV and add them to the Objects
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws CsvException
	 */
	public List<Employee> readCsvToObject(String fileName) throws IOException, CsvException {
		try {
			FileUtils app = new FileUtils();
			Reader reader = new InputStreamReader(app.getFileFromResourceAsStream(fileName));
			List<Employee> employees = new CsvToBeanBuilder<Employee>(reader).withType(Employee.class).build().parse();
			return employees;
		} catch (IllegalStateException e) {
			LOGGER.error("IllegalStateException exception :  " + e.getMessage());
		}
		return null;
	}
}
