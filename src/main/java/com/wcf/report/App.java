package com.wcf.report;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ubaid Raja
 * @since Sep 4, 2021
 */

public class App {
	private static Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			int yearInput = readInput(sc);

			ReportHandler handler = new ReportHandler();
			handler.handleReportRequest(yearInput);

		} catch (Exception e) {
			LOGGER.error("Exception " + e.getMessage());
		}
	}

	public static int readInput(Scanner in) {
		int year = 0;
		try {
			System.out.println("Enter year - ");
			year = in.nextInt();
		} catch (Exception e) {
			LOGGER.info("only numbers are allowed");
			in.nextLine();
			year = readInput(in);
		}
		return year;
	}
}
