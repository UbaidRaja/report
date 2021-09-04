package com.wcf.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Unit test for running App.
 */
public class AppTest {
	@Test
	public void shouldCreateTheReport() {
		Integer year = 2018;
		ReportHandler handler = new ReportHandler();
		handler.handleReportRequest(year);
		String outputFileName = "Employee.pdf";
		File file = new File(outputFileName);
		assertTrue(file.exists());
		if (file.exists()) {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File(outputFileName);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}
		}
	}

	@Test
	public void invalidYear_shouldNotCreateReport() {
		Integer year = 2020;
		ReportHandler handler = new ReportHandler();
		assertThrows(IllegalArgumentException.class, () -> {
			handler.handleReportRequest(year);
		});

	}
}
