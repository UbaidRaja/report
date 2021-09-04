/**
 * 
 */
package com.wcf.report.printer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcf.report.App;

/**
 * @author Ubaid Raja
 * @since Sep 4, 2021
 */
public class FilePrinter {

	private static Logger LOGGER = LoggerFactory.getLogger(FilePrinter.class);
	/**
	 * This method send the file to the connected printers to print it
	 * @param fileName
	 * @throws PrintException
	 * @throws IOException
	 */
	public static void print(String fileName) throws PrintException, IOException {
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.DUPLEX);
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		if (ps.length == 0) {
			throw new IllegalStateException("No Printer found");
		}
		
		LOGGER.info("Available printers: " + Arrays.asList(ps));

		PrintService myService = null;
		for (PrintService printService : ps) {
			if (printService.getName().equals("Your printer name")) {
				myService = printService;
				break;
			}
		}

		if (myService == null) {
			throw new IllegalStateException("Printer not found");
		}

		FileInputStream fis = new FileInputStream(fileName);
		Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		DocPrintJob printJob = myService.createPrintJob();
		printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
		fis.close();
	}
}
