package com.wcf.report.pdf.writer;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wcf.report.model.Employee;
import com.wcf.report.model.MobileUsage;

/**
 * @author Ubaid Raja
 * @since Sep 3, 2021
 */
public class PDFWriter {

	/**
	 * To write data to the PDF file, following parameters are used
	 * 
	 * @param yearInput
	 * @param employees
	 * @param accumulatedTotalMinutes
	 * @param accumulatedTotalData
	 * @param data
	 * @param outputFileName
	 */
	public static void write(Integer yearInput, List<Employee> employees, Double accumulatedTotalMinutes,
			Double accumulatedTotalData, List<Map<Integer, List<MobileUsage>>> data, String outputFileName) {
		OutputStream file = null;
		try {
			file = new FileOutputStream(new File(outputFileName));
			// special font sizes
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 11);
			// Create a new Document object
			Document document = new Document();

			// You need PdfWriter to generate PDF document
			PdfWriter.getInstance(document, file);

			// Opening document for writing PDF
			document.open();

			// create a paragraph
			Paragraph paragraph = new Paragraph("Employee Information for Year " + yearInput);

			// Report run date, Number of phones, total minutes, total data, average
			// minutes, average data
			// specify column widths
			float[] columnWidths = { 1.7f, 2f, 1.3f, 1.3f, 1.7f, 1.7f }; // create PDF table with the given widths
			PdfPTable table = new PdfPTable(columnWidths);
			// set table width a percentage of the page width
			table.setWidthPercentage(100f);

			// insert column headings
			insertCell(table, "Report run date", Element.ALIGN_LEFT, 1, bfBold12);
			insertCell(table, "Number of phones", Element.ALIGN_LEFT, 1, bfBold12);
			insertCell(table, "Total Minutes", Element.ALIGN_LEFT, 1, bfBold12);
			insertCell(table, "Total Data", Element.ALIGN_LEFT, 1, bfBold12);
			insertCell(table, "Average Minutes", Element.ALIGN_LEFT, 1, bfBold12);
			insertCell(table, "Average Data", Element.ALIGN_LEFT, 1, bfBold12);
			table.setHeaderRows(1);

			DecimalFormat df = new DecimalFormat("0.00");

			LocalDate localDate = LocalDate.now();
			for (int x = 1; x < 2; x++) {

				insertCell(table, localDate.toString(), Element.ALIGN_RIGHT, 1, bf12);
				insertCell(table, String.valueOf(employees.size()), Element.ALIGN_RIGHT, 1, bf12);
				insertCell(table, String.valueOf(df.format(accumulatedTotalMinutes)), Element.ALIGN_RIGHT, 1, bf12);
				insertCell(table, String.valueOf(df.format(accumulatedTotalData)), Element.ALIGN_RIGHT, 1, bf12);
				insertCell(table, String.valueOf(df.format(accumulatedTotalMinutes / employees.size())),
						Element.ALIGN_RIGHT, 1, bf12);
				insertCell(table, String.valueOf(df.format(accumulatedTotalData / employees.size())),
						Element.ALIGN_RIGHT, 1, bf12);
			}

			// add the PDF table to the paragraph
			paragraph.add(table);
			document.add(paragraph);
			// create a paragraph
			paragraph = new Paragraph("Employee detailed Information.");
			boolean condition = true;
			PdfPTable table1 = new PdfPTable(29);
			table1.setWidthPercentage(100);
			table1.addCell(createCell("Employee Id", 1, 2, PdfPCell.BOX, condition));
			table1.addCell(createCell("Employee Name", 1, 2, PdfPCell.BOX, condition));
			table1.addCell(createCell("Model", 1, 2, PdfPCell.BOX, condition));
			table1.addCell(createCell("Purchase Date", 1, 2, PdfPCell.BOX, condition));
			table1.addCell(createCell("", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("Minutes Usage", 12, 1, PdfPCell.BOX, condition));

			table1.addCell(createCell("", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("Data Usage", 12, 1, PdfPCell.BOX, condition));

			// Below are for the Minutes usage
			table1.addCell(createCell("Jan", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("Feb", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("March", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("April", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("May", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("June", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("July", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("August", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("September", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("October", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("November", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("December", 1, 1, PdfPCell.BOX, condition));

			table1.addCell(createCell("", 1, 1, PdfPCell.BOX, condition));

			table1.addCell(createCell("Jan", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("Feb", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("March", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("April", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("May", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("June", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("July", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("August", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("September", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("October", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("November", 1, 1, PdfPCell.BOX, condition));
			table1.addCell(createCell("December", 1, 1, PdfPCell.BOX, condition));

			// add the PDF table to the paragraph
			paragraph.add(table1);

			List<Double> listOfMinutesUsageByMonth = null;
			List<Double> listOfDataUsageByMonth = null;

			for (int x = 0; x < employees.size(); x++) {
				condition = !condition;
				listOfMinutesUsageByMonth = new ArrayList<Double>(Collections.nCopies(13, 0.0));
				listOfDataUsageByMonth = new ArrayList<Double>(Collections.nCopies(13, 0.0));
				Employee e = employees.get(x);
				getMonthlyDataStats(data, listOfMinutesUsageByMonth, listOfDataUsageByMonth, e.getEmployeeId());

				table1.addCell(createCell(String.valueOf(e.getEmployeeId()), 1, 1, PdfPCell.BOX, condition));
				table1.addCell(createCell(e.getEmployeeName(), 1, 1, PdfPCell.BOX, condition));
				table1.addCell(createCell(e.getModel(), 1, 1, PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(e.getPurchaseDate()), 1, 1, PdfPCell.BOX, condition));
				// Below are the minutes usage

				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(1))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(2))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(3))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(4))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(5))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(6))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(7))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(8))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(9))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(10))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(11))), 1, 1,
						PdfPCell.BOX, condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfMinutesUsageByMonth.get(12))), 1, 1,
						PdfPCell.BOX, condition));

				table1.addCell(createCell("", 1, 1, PdfPCell.BOX, condition));

				// Below are the data usage
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(1))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(2))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(3))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(4))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(5))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(6))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(7))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(8))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(9))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(10))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(11))), 1, 1, PdfPCell.BOX,
						condition));
				table1.addCell(createCell(String.valueOf(df.format(listOfDataUsageByMonth.get(12))), 1, 1, PdfPCell.BOX,
						condition));
			}
			// add the paragraph to the document
			document.add(paragraph);

			// Add meta data information to PDF file
			document.addCreationDate();
			document.addAuthor("Ubaid Raja");
			document.addTitle("Employees");
			document.addCreator("Ubaid Raja");

			// close the document
			document.close();

			System.out.println("Your PDF File is succesfully created");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException io) {/* Failed to close */

			}
		}
	}

	/**
	 * This methods returns monthly stats of total used minutes and data
	 * 
	 * @param data
	 * @param listOfMinutesUsageByMonth
	 * @param listOfDataUsageByMonth
	 * @param employeeId
	 */
	private static void getMonthlyDataStats(List<Map<Integer, List<MobileUsage>>> data,
			List<Double> listOfMinutesUsageByMonth, List<Double> listOfDataUsageByMonth, Integer employeeId) {
		for (Map<Integer, List<MobileUsage>> ob : data) {
			for (Map.Entry<Integer, List<MobileUsage>> dataEntryset : ob.entrySet()) {
//				System.out.println(dataEntryset.getValue());
				List<MobileUsage> mobileUsageByEmployee = dataEntryset.getValue();
				if (mobileUsageByEmployee.get(0).getEmployeeId().equals(employeeId)) {
					final double totalMinutes = dataEntryset.getValue().stream()
							.mapToInt(row -> (int) row.getTotalMinutes()).sum();
					final double totalData = dataEntryset.getValue().stream()
							.mapToDouble(row -> (double) row.getTotalData()).sum();
//					System.out.println("sum of total minutes for month " + dataEntryset.getKey() + " is " + totalMinutes);
//					System.out.println("sum of total data for month " + dataEntryset.getKey() + " is " + totalData);
					listOfMinutesUsageByMonth.add(dataEntryset.getKey(), totalMinutes);
					listOfDataUsageByMonth.add(dataEntryset.getKey(), totalData);
				} else {
					continue;
				}

			}
		}
	}

	private static PdfPCell createCell(String content, int colspan, int rowspan, int border, boolean condition) {
		Font font = new Font(FontFamily.HELVETICA, 10);
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBackgroundColor(condition ? BaseColor.LIGHT_GRAY : BaseColor.WHITE);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setBorder(border);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return cell;
	}

	private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}
}