/**
 * 
 */
package com.wcf.report.model;

/**
 * @author Ubaid Raja
 * @since September 3, 2021
 *
 */
public class MobileUsage {

	// Attributes
	private Integer employeeId;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer totalMinutes;
	private Double totalData;

	@Override
	public String toString() {
		return "MobileUsage [employeeId=" + employeeId + ", year=" + year + ", month=" + month + ", day=" + day
				+ ", totalMinutes=" + totalMinutes + ", totalData=" + totalData + "]";
	}

	
	/**
	 * @param employeeId
	 * @param year
	 * @param month
	 * @param day
	 * @param totalMinutes
	 * @param totalData
	 */
	public MobileUsage(Integer employeeId, Integer year, Integer month, Integer day, Integer totalMinutes,
			Double totalData) {
		super();
		this.employeeId = employeeId;
		this.year = year;
		this.month = month;
		this.day = day;
		this.totalMinutes = totalMinutes;
		this.totalData = totalData;
	}

	/**
	 * 
	 */
	public MobileUsage() {
		super();
	}

	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	
	/**
	 * @param employeeId
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the totalMinutes
	 */
	public Integer getTotalMinutes() {
		return totalMinutes;
	}

	/**
	 * @param totalMinutes the totalMinutes to set
	 */
	public void setTotalMinutes(Integer totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	/**
	 * @return the totalData
	 */
	public Double getTotalData() {
		return totalData;
	}

	/**
	 * @param totalData the totalData to set
	 */
	public void setTotalData(Double totalData) {
		this.totalData = totalData;
	}

}
