package com.wcf.report.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.wcf.report.model.Employee;
import com.wcf.report.model.MobileUsage;

/**
 * @author Ubaid Raja
 * @since Sep 4, 2021
 */
public class DataFormatter {

	private Double accumulatedTotalMinutes;
	private Double accumulatedTotalData;

	public DataFormatter() {
		accumulatedTotalMinutes = 0.0;
		accumulatedTotalData = 0.0;
	}

	/**
	 * @return the accumulatedTotalMinutes
	 */
	public Double getAccumulatedTotalMinutes() {
		return accumulatedTotalMinutes;
	}

	/**
	 * @param accumulatedTotalMinutes the accumulatedTotalMinutes to set
	 */
	public void setAccumulatedTotalMinutes(Double accumulatedTotalMinutes) {
		this.accumulatedTotalMinutes = accumulatedTotalMinutes;
	}

	/**
	 * @return the accumulatedTotalData
	 */
	public Double getAccumulatedTotalData() {
		return accumulatedTotalData;
	}

	/**
	 * @param accumulatedTotalData the accumulatedTotalData to set
	 */
	public void setAccumulatedTotalData(Double accumulatedTotalData) {
		this.accumulatedTotalData = accumulatedTotalData;
	}

	
	/**
	 * @param usage
	 * @param year
	 * @return
	 */
	public List<List<MobileUsage>> partitionDataYearly(Map<Employee, List<MobileUsage>> usage, Integer year) {
		List<List<MobileUsage>> myObjectsPerYear = new ArrayList<List<MobileUsage>>();
		final int val = year;
		for (Map.Entry<Employee, List<MobileUsage>> entry : usage.entrySet()) {
			List<MobileUsage> yearlyList = entry.getValue();
			myObjectsPerYear.add(yearlyList.stream().filter(p -> p.getYear().equals(val)).collect(Collectors.toList()));
		}
		return myObjectsPerYear;
	}

	/** This methods partitions data based on monthly usage from a year
	 * @param yearlyData
	 * @return
	 */
	public List<Map<Integer, List<MobileUsage>>> partitionDataMonthly(List<List<MobileUsage>> yearlyData) {
		List<Map<Integer, List<MobileUsage>>> myObjectsPerMonth = new ArrayList<Map<Integer, List<MobileUsage>>>();
		for (List<MobileUsage> data : yearlyData) {
			myObjectsPerMonth.add(data.stream().collect(Collectors.groupingBy(MobileUsage::getMonth)));
		}
		return myObjectsPerMonth;
	}

	private void calculateAccumulatedData(List<List<MobileUsage>> yearlyData) {
		for (List<MobileUsage> data : yearlyData) {
			accumulatedTotalMinutes = accumulatedTotalMinutes
					+ data.stream().mapToInt(row -> (int) row.getTotalMinutes()).sum();
			accumulatedTotalData = accumulatedTotalData
					+ data.stream().mapToDouble(row -> (double) row.getTotalData()).sum();
		}
	}

	
	/**
	 * @param usage
	 * @param year
	 * @return
	 */
	public List<Map<Integer, List<MobileUsage>>> partitionData(Map<Employee, List<MobileUsage>> usage, Integer year) {
		// get distinct year from the list and partition based on it
		List<List<MobileUsage>> yearlyData = partitionDataYearly(usage, year);
		calculateAccumulatedData(yearlyData);
		// get distinct months from that list and partition based on it.
		List<Map<Integer, List<MobileUsage>>> monthlyData = partitionDataMonthly(yearlyData);
		return monthlyData;
	}

	/**
	 * @param data
	 */
	public void printMonthlyData(List<Map<Integer, List<MobileUsage>>> data) {
		for (Map<Integer, List<MobileUsage>> ob : data) {
			for (Map.Entry<Integer, List<MobileUsage>> dataEntryset : ob.entrySet()) {
				final double totalMinutes = dataEntryset.getValue().stream()
						.mapToInt(row -> (int) row.getTotalMinutes()).sum();
				final double totalData = dataEntryset.getValue().stream()
						.mapToDouble(row -> (double) row.getTotalData()).sum();
//				System.out.println("sum of total minutes for month " + dataEntryset.getKey() + " is " + totalMinutes);
//				System.out.println("sum of total data for month " + dataEntryset.getKey() + " is " + totalData);
			}
		}
	}

	/**
	 * @param data
	 */
	public void printData(List<List<MobileUsage>> data) {
//		System.out.println("Data is : " + data.size());
		for (List<MobileUsage> usage : data) {
			for (MobileUsage us : usage) {
//				System.out.println(us);
			}
		}
	}
}
