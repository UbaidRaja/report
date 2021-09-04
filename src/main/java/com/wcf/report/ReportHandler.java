/**
 * 
 */
package com.wcf.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.print.PrintException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.exceptions.CsvException;
import com.wcf.report.csv.ParseCSV;
import com.wcf.report.formatter.DataFormatter;
import com.wcf.report.model.Employee;
import com.wcf.report.model.MobileUsage;
import com.wcf.report.pdf.writer.PDFWriter;
import com.wcf.report.printer.FilePrinter;

/**
 * @author Ubaid Raja
 * @since Sep 4, 2021
 */
public class ReportHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(ReportHandler.class);

	protected void handleReportRequest(Integer yearInput) {
		try {

			LOGGER.info("Handling request with year " + yearInput);

			ParseCSV csvParser = new ParseCSV();

			String fileName = "csv/CellPhone.csv";

			List<Employee> employees = csvParser.readCsvToObject(fileName);

			String usageFileName = "csv/CellPhoneUsageByMonth.csv";

			String outputFileName = "Employee.pdf";

			Map<Employee, List<MobileUsage>> employeeMobileUsageData = csvParser.getEmployeeMobileUsageData(yearInput,
					usageFileName, employees);

			DataFormatter formatter = new DataFormatter();

			List<Map<Integer, List<MobileUsage>>> data = formatter.partitionData(employeeMobileUsageData, yearInput);

			if (employeeMobileUsageData != null) {
				PDFWriter.write(yearInput, employees, formatter.getAccumulatedTotalMinutes(),
						formatter.getAccumulatedTotalData(), data, outputFileName);
				FilePrinter.print(outputFileName);
			}
		} catch (IOException e) {
			LOGGER.error("IOException found " + e.getMessage());
		} catch (CsvException e) {
			LOGGER.error("CsvException found " + e.getMessage());
		} catch (IllegalStateException e) {
			LOGGER.error("IllegalStateException found " + e.getMessage());
		} catch (PrintException e) {
			LOGGER.error("PrintException found " + e.getMessage());
		}
	}
}
