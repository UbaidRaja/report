/**
 * 
 */
package com.wcf.report.model;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Ubaid Raja
 * @since September 3, 2021
 *
 */
public class Employee {
	// Attributes
	@CsvBindByName(column = "employeeId")
	private Integer employeeId;

	@CsvBindByName(column = "employeeName")
	private String employeeName;

	@CsvBindByName(column = "purchaseDate")
	private Long purchaseDate;

	@CsvBindByName(column = "model")
	private String model;

	public Employee() {

	}


	/**
	 * @param employeeId
	 * @param employeeName
	 * @param purchaseDate
	 * @param model
	 */
	public Employee(Integer employeeId, String employeeName, Long purchaseDate, String model) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.purchaseDate = purchaseDate;
		this.model = model;
	}

	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", purchaseDate="
				+ purchaseDate + ", model=" + model + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}

}
