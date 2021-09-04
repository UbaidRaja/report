# Program to print mobile usage report
This program takes in a Year parameter as input from the user, creates a PDF document and sends it as a print job on the local computer printer.

### Data Model
Two CSV files are provided in the csv folder inside resources folder. 

**CellPhone.csv**
*	employeeId
*	employeeName
*	purchaseDate
*	model

**CellPhoneUsageByMonth.csv** 
*	employeeId
*	date
*	totalMinutes
*	totalData

### Report  

Report contain the following information

**Header Section**

*	Report Run Date
*	Number of Phones
*	Total Minutes
*	Total Data
*	Average Minutes
*	Average Data

**Details Section**

For each company cell phone provide the following information
*	Employee Id
*	Employee Name
*	Model
*	Purchase Date
*	Minutes Usage
    *	one column for each month
*	Data Usage
    *	one column for each month

As the program finishes execution a generated PDF file is opened in the browser and a print command is sent to the local printer in the computer. 

# Tech Stack
Java is used as the programming language and following libraries are used,
*	Open CSV
*	junit jupiter
*	itextpdf
*	log4j-slf4j

# How to run project
This project available on Git repo and can be cloned using following command
*	git clone git@github.com:UbaidRaja/report.git
Once the code is available this could be run using the following maven command
* mvn clean install

There are a couple of Test cases that will run with this command.

* If you want to run this project as a Java application, goto App.java inside com.wcf.report package and run it, it will ask for Year as an input
 
# Clarifications
The original data present in the CellPhoneUsageByMonth.csv had date column, so I have partitioned data on these following columns
* year
* month

For the reporting section, I have used itextpdf to generate reports.
