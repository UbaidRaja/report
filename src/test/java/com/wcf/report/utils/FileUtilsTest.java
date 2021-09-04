package com.wcf.report.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

class FileUtilsTest {

	@Test
	public void testCellPhoneExistWithClassLoader() {
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource("csv/CellPhone.csv").getFile());
		assertTrue(file.exists());

		assertTrue(file.getAbsolutePath().endsWith("CellPhone.csv"));
	}

	@Test
	public void testCellPhoneUsageByMonthExistWithClassLoader() {
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource("csv/CellPhoneUsageByMonth.csv").getFile());
		assertTrue(file.exists());

		assertTrue(file.getAbsolutePath().endsWith("CellPhoneUsageByMonth.csv"));
	}

}
