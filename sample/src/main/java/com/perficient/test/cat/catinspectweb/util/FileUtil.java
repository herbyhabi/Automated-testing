package com.perficient.test.cat.catinspectweb.util;

import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.DOWNLOADDIR;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.TESTDATADIR;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.findAKeywordInList;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.findAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.csvreader.CsvReader;

public class FileUtil extends TestCaseBase {
	

	static FileInputStream fs;
	static HSSFSheet hssfSheet;
	// Get Test data
	static String downloadDir = DOWNLOADDIR;
	static String testDataDir = TESTDATADIR;
	

	@Deprecated
	public com.perficient.test.cat.catinspectweb.util.FunctionUtil funtion = new FunctionUtil();

	
	
	/**
	 * Get the count number of column that having content.
	 * 
	 * @param xssfSheet
	 *            sheet object to count the column number.
	 * @return the count number of column that having content.
	 * @throws Exception
	 */
	public static int getColumnCount(Sheet xssfSheet) throws Exception {

		int count = 0;
		Iterator<Cell> iterator = xssfSheet.getRow(0).cellIterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		return count;
	}

	/**
	 * Get the count number of column that having content.
	 * 
	 * @param xssfSheet
	 *            sheet object to count the column number.
	 * @return the count number of column that having content.
	 * @throws Exception
	 */

	public static void iterateCells(Sheet xssfSheet) {
		for (Iterator<Cell> cit = (Iterator<Cell>) xssfSheet.getRow(0)
				.cellIterator(); cit.hasNext();) {
			Cell cell = cit.next();
			log.info("cell comment is:" + cell.getStringCellValue());
		}

	}

	/**
	 * Get the count number of column that having content.
	 * 
	 * @param xssfSheet
	 *            sheet object to count the column number.
	 * @param int rowNumber, the numerical order of the row
	 * @return the count number of column that having content.
	 * @throws Exception
	 */
	public int getColumnCount(Sheet xssfSheet, int rowNumber) throws Exception {

		int count = 0;
		Iterator<Cell> iterator = xssfSheet.getRow(rowNumber).cellIterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		return count;
	}

	/**
	 * Get the count number of row that having content.
	 * 
	 * @param xssfSheet
	 *            sheet object to count the row number.
	 * @return the count number of row that having content.
	 * @throws Exception
	 */
	public static int getRowCount(Sheet xssfSheet) throws Exception {

		int count = 0;
		Iterator<Row> iterator = xssfSheet.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		return count;
	}

	/**
	 * Wait for downloading of specific file finish, max wait time is 1 minute.
	 * 
	 * @param fileName
	 *            fileName
	 * @return boolean return True if find the download file, otherwise return
	 *         false.
	 * @throws Exception
	 */
	public static boolean waitForFileDownloadComplete(String fileName)
			throws Exception {
		int loopCount = 1;
		Thread.sleep(2000);
		while (loopCount <= 60) {

			File f = new File(downloadDir + File.separator + fileName);
			File fp = new File(downloadDir + File.separator + fileName
					+ ".part");
			if (f.isFile() && f.exists() && !fp.exists() && f.canRead()) {
				return true;
			} else {
				loopCount++;
				Thread.sleep(5000);
				log.info("Waiting for downloading " + fileName);
				continue;
			}
		}
		return false;
	}

	/**
	 * wait for file download, not check file name
	 * 
	 * @author mercy.liu
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForFileDownloadComplete() throws Exception {
		int loopCount = 1;
		Thread.sleep(2000);
		// FunctionUtil.waitForElementExist("//span[contains(text(),'Excel')]/parent::button[@aria-disabled='false']");
		while (loopCount <= 60) {
			if (getFilePathNameFrmAPath(downloadDir).length > 0) {
				return true;
			} else {
				loopCount++;
				Thread.sleep(5000);
				log.info("Waiting for downloading ");
				continue;
			}
		}
		return false;
	}

	/**
	 * wait for file download, not check file name. The downloaded is triggered
	 * by clicking on a link.
	 * 
	 * @author matthew.feng
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForFileDownloadCompleteByClickingALink()
			throws Exception {
		int loopCount = 1;
		Thread.sleep(2000);
		// FunctionUtil.waitForElementExist("//span[contains(text(),'Excel')]/parent::button[@aria-disabled='false']");
		while (loopCount <= 60) {
			if (getFilePathNameFrmAPath(downloadDir).length > 0) {
				return true;
			} else {
				loopCount++;
				Thread.sleep(5000);
				log.info("Waiting for downloading ");
				continue;
			}
		}
		return false;
	}

	/**
	 * Get the all the (path + file name) of all the files in a specified path
	 * 
	 * @author matthew.feng
	 * @return a list of file objects
	 * @throws Exception
	 */
	public static File[] getFilePathNameFrmAPath(String path) throws Exception {
		File f = new File(path);
		File[] paths = f.listFiles();
		return paths;
	}

	/**
	 * Get the (path+name) of the first file of all the files in a specified
	 * path
	 * 
	 * @author matthew.feng
	 * @return the path+name of the first file
	 * @throws Exception
	 */
	public static String getFirstFileInAPath(File[] paths) throws Exception {
		int i = 0;
		for (; i < paths.length; i++) {
			if (paths[i].getName().contains(".xlsx")) {
				break;
			}
		}
		return paths[i] + "";
	}

	/**
	 * Verify cell value in desired value list
	 * 
	 * @param cellVal
	 * @param desiredValues
	 * @return true if value in desired value list, else return false
	 * @throws Exception
	 */
	public static boolean verifyCellValueInDesiredValueList(String cellVal,
			String desiredValues) throws Exception {

		boolean result = false;
		if (desiredValues.contains(cellVal)) {
			result = true;
		}
		return result;
	}

	/**
	 * Verify cell value in desired value
	 * 
	 * @param cellVal
	 * @param desiredValues
	 * @return true if value in desired value list, else return false
	 * @throws Exception
	 */
	public boolean verifyCellValueInDesiredValue(String cellVal,
			String desiredValue) throws Exception {
		boolean result = false;
		if (cellVal.equals(desiredValue)) {
			result = true;
		}
		return result;
	}

	/**
	 * Rename the file Name
	 * 
	 * @param sourceFileName
	 *            source file name need to change
	 * @param destFileName
	 *            destination file name
	 * @return boolean - True if rename success, False if failed.
	 * @throws Exception
	 */
	public boolean renameFile(String sourceFileName, String destFileName)
			throws Exception {

		File src = new File(downloadDir + File.separator + sourceFileName);
		File dest = new File(downloadDir + File.separator + destFileName);
		log.info(src.getAbsolutePath());
		log.info(dest.getAbsolutePath());
		// InputStream in = new FileInputStream(dest);
		boolean con = src.renameTo(dest);
		return con;
	}

	/**
	 * Get the total record number from table info. Example: get number "2111"
	 * from "Showing 1 to 10 of 2,111 entries"
	 * 
	 * @param totalEntriesStr
	 * @return
	 */
	public int getTotalRecordNumberFromTotalEntriesString(String totalEntriesStr) {

		return Integer.parseInt(totalEntriesStr
				.substring(totalEntriesStr.lastIndexOf("of") + 3,
						totalEntriesStr.lastIndexOf("entries") - 1).trim()
				.replace(",", ""));
	}

	/**
	 * Get text value list from a web element list
	 * 
	 * @param entries
	 *            Web Element entries.
	 * @param number
	 *            Total number of the records you want to get.
	 * @return a list of text value
	 */
	public List<String> getTextListFromWebElementList(List<WebElement> entries,
			int number) {

		List<String> entriesTextList = new ArrayList<String>();

		Iterator<WebElement> iterator = entries.iterator();

		for (int i = 1; i <= number && iterator.hasNext(); i++) {
			entriesTextList.add(iterator.next().getText().trim());
		}
		return entriesTextList;
	}

	/**
	 * Get text value list from specific excel column.
	 * 
	 * @param xssfSheet
	 * @param number
	 *            Total number of the records you want to get.
	 * @param columnIndex
	 *            Column index
	 * @return
	 */
	public List<String> getTextListFromExcelColumn(XSSFSheet xssfSheet,
			int columnIndex, int number) {

		List<String> recordTextList = new ArrayList<String>();

		for (int i = 1; i <= 10; i++) {
			recordTextList.add(xssfSheet.getRow(i).getCell(columnIndex)
					.getStringCellValue().trim());
		}
		return recordTextList;
	}

	/**
	 * Verify excel record same to table view.
	 * 
	 * @param xssfSheet
	 * @param firstColumnWebElement
	 */
	public void verifyExcelRecordOrderSameToTable(XSSFSheet xssfSheet,
			List<WebElement> firstColumnWebElement) {

		List<String> tableValueList = getTextListFromWebElementList(
				firstColumnWebElement, 10);
		List<String> excelValueList = getTextListFromExcelColumn(xssfSheet, 0,
				10);

		Assert.assertEquals(tableValueList, excelValueList,
				"The records in excel should be in the same order as they are in the table. ");

	}

	/**
	 * verify Total Number Of Record Equal To entries number in Table Record
	 * Info
	 * 
	 * @param xssfSheet
	 * @param text_tableRecordInfo
	 * @param numToDeduct
	 *            The number of rows to be deducted from the total records
	 *            number, such as column name.....
	 */
	public boolean verifyTotalNumberOfRecordEqualToTableRecordInfo(
			XSSFSheet xssfSheet, WebElement text_tableRecordInfo,
			int numToDeduct) {

		// Total number displayed on table
		int totalNumberReocrdOnTable = getTotalRecordNumberFromTotalEntriesString(text_tableRecordInfo
				.getText());

		// Total number in excel
		int totalNumberOfrecordsInExcel = xssfSheet.getLastRowNum()
				- numToDeduct;
		return totalNumberReocrdOnTable == totalNumberOfrecordsInExcel;
	}

	/**
	 * Get a dateRange based on the current date and the months need verify
	 * 
	 * @param months
	 * @return DateRange
	 */
	public Date[] getDateRange(int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, -12);
		calendar.set(Calendar.SECOND, 0);
		Date dateRangeMax = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, -12);
		calendar.add(Calendar.MONTH, -months + 1);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		Date dateRangeMin = calendar.getTime();
		Date[] dates = { dateRangeMax, dateRangeMin };
		return dates;
	}

	/**
	 * Verify sort button exist on excel column headings
	 * 
	 * @param xssfSheet
	 * @return true if it have sort button, otherwise return false
	 */
	public boolean verifySortButtonExistOnColumnHeadings(XSSFSheet xssfSheet) {
		return xssfSheet.getCTWorksheet().isSetAutoFilter();
	}

	/**
	 * Verify sort button exist on excel column headings for xls file
	 * 
	 * @param hssfSheet
	 * @return true if it have sort button, otherwise return false
	 */
	public boolean verifySortButtonExistOnColumnHeadingsXls(HSSFSheet hssfSheet) {
		// return hssfSheet.getCTWorksheet().isSetAutoFilter();
		return true;
	}

	/**
	 * @author icey.qi
	 * @param ExpectedData
	 *            : expect data you want to match in excel
	 * @param colum
	 *            : which column you want to match expect data
	 * @throws FileNotFoundException
	 */
	public static void verifyDownLoadDataMatchs(String excelName,
			String expectedData, int column) throws Exception {
		String filePath = downloadDir + File.separator + excelName;
		File excelfileDefault;
		FileInputStream fs;
		XSSFSheet xssfSheet;
		excelfileDefault = new File(filePath);
		fs = new FileInputStream(excelfileDefault);
		xssfSheet = new XSSFWorkbook(fs).getSheetAt(0);

		int rowEnd = xssfSheet.getLastRowNum();

		// Loop each row of record in excel
		for (int i = 1; i <= rowEnd - 3; i++) {

			Assert.assertEquals(xssfSheet.getRow(i).getCell(column)
					.getStringCellValue().trim(), expectedData,
					"data doesn't match desired data in column " + column);

		}

		fs.close();
	}

	public static void verifyDownloadHeader(String excelName, String[] header)
			throws Exception {
		String filePath = downloadDir + File.separator + excelName;
		File excelfileDefault;
		FileInputStream fs;
		XSSFSheet xssfSheet;
		excelfileDefault = new File(filePath);
		fs = new FileInputStream(excelfileDefault);
		xssfSheet = new XSSFWorkbook(fs).getSheetAt(0);

		int column = header.length;

		// Loop each row of record in excel
		for (int i = 0; i < column; i++) {
			Assert.assertEquals(xssfSheet.getRow(0).getCell(i)
					.getStringCellValue().trim().replaceAll("[\\t\\n\\r]", ""),
					header[i],
					"header data doesn't match desired data in column "
							+ (i + 1));
		}

		fs.close();

	}

	/**
	 * * Delete all files from download folder
	 * 
	 * @return
	 * @throws Exception
	 */
	static public void emptyDownloadFolder() throws Exception {
		File file = new File(downloadDir);
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			temp = new File(downloadDir + File.separator + element);
			if (temp.isFile()) {
				temp.delete();
			}
		}
		emptyFolder(downloadDir);
	}

	/**
	 * 
	 * @param folderName
	 * @throws Exception
	 * @author tripp.hu
	 */
	static public void emptyFolder(String folderName) throws Exception {
		File file = new File(folderName);
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			temp = new File(folderName + File.separator + element);
			if (temp.isFile()) {
				temp.delete();
			}
		}
	}

	/**
	 * Download excel from webpage and compare the values on the excel with the
	 * values on the webpage
	 * 
	 * @param filename
	 *            , desiredTotalNumberOfColumn, xpath
	 * @throws Exception
	 * @author Amy.hu
	 */

	@SuppressWarnings("resource")
	static public void verifyDownloadExcel(String filename,
			int columnNumberOnFile, int columnNumberOnPage,
			String rowValuesOnPage) throws Exception {
		// Click on Excel button and verify excel file is downloaded
		// successfully.
		Assert.assertTrue(FileUtil.waitForFileDownloadComplete(filename),
				"Unable to download file");

		// Define fs, hssfsheet
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				downloadDir + File.separator + filename));
		hssfSheet = new HSSFWorkbook(fs).getSheetAt(0);

		// Verify Excel Export have desired column count
		Assert.assertEquals(getColumnCount(hssfSheet), columnNumberOnFile,
				"Downloaded file doesn't match expected column count.");
		// iterateCells(hssfSheet);

		// Get last row number
		int rowEnd = hssfSheet.getLastRowNum();
		if (rowEnd > 13) {
			rowEnd = 13;
		}
		// Loop each row of record in excel
		for (int i = 1; i <= rowEnd - 1; i++) {
			int count;
			if (filename.contains("OrderHistory")) {
				count = columnNumberOnPage - 1;
			}// there is a Reorder button on the page, but no value on the excel
				// file
			else {
				count = columnNumberOnPage;
			}

			for (int j = 0; j < count; j++) {
				String coreDepositsValue = hssfSheet.getRow(i).getCell(j)
						.getStringCellValue().trim();
				String desiredCoreDepositsValues = findAll(rowValuesOnPage)
						.get((i - 1) * columnNumberOnPage + j).getText().trim();

				log.info("desiredCoreDepositsValues >>>> "
						+ desiredCoreDepositsValues);
				List<WebElement> headers = findAll("//table[contains(@class, 'GridRowTable')]//th//div[@class='dojoxGridSortNode']");
				if (findAKeywordInList("Order status", headers) != -1 & j == 2
						& !desiredCoreDepositsValues.isEmpty()) {

					String[] dp = desiredCoreDepositsValues.split(" ");
					if (dp.length != 1) {
						desiredCoreDepositsValues = dp[0].substring(0, 1)
								+ dp[dp.length - 1].substring(0, 1);
					} else {
						desiredCoreDepositsValues = dp[0].substring(0, 2)
								.toUpperCase().trim();
					}
				}

				log.info("coreDepositsValue:" + i + "," + j + "cell is:"
						+ coreDepositsValue);
				log.info("desiredCoreDepositsValues:" + i + "," + j
						+ "cell is:" + desiredCoreDepositsValues);

				// verify supplier in desired supplier value list
				Assert.assertTrue(
						verifyCellValueInDesiredValueList(coreDepositsValue,
								desiredCoreDepositsValues),
						"Supplier Code column value[" + coreDepositsValue
								+ "] doesn't match desired Core Deposits"
								+ desiredCoreDepositsValues);
			}
		}
		fs.close();
		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * Download CSV from webpage and compare the values on the CSV with the
	 * values on the webpage
	 * 
	 * @param filename
	 *            , desiredTotalNumberOfColumn, xpath
	 * @throws Exception
	 * @author Amy.hu
	 */
	static public void verifyDownloadCSV(String filename,
			int columnNumberOnFile, int columnNumberOnPage,
			String rowValuesOnPage) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>();

		// Click on CSV button and verify excel file is downloaded
		// successfully.
		Assert.assertTrue(waitForFileDownloadComplete(filename),
				"Unable to download file");
		Thread.sleep(20000);
		CsvReader reader = new CsvReader(downloadDir + File.separator
				+ filename);
		reader.readHeaders();
		while (reader.readRecord()) {
			csvList.add(reader.getValues());
		}
		reader.close();

		// Verify CSV Export have desired column count
		Assert.assertEquals(reader.getHeaderCount(), columnNumberOnFile,
				"Downloaded file doesn't match expected column count.");

		// Get last row number
		int rowEnd = csvList.size();
		if (rowEnd > 13) {
			rowEnd = 13;
		}
		// Loop each row of record in excel
		for (int i = 0; i < rowEnd - 1; i++) {
			int count;
			if (filename.contains("OrderHistory")) {
				count = columnNumberOnPage - 1;
			}// there is a Reorder button on the page, but no value on the CSV
				// file
			else {
				count = columnNumberOnPage;
			}

			for (int j = 0; j < count; j++) {
				String coreDepositsValue = csvList.get(i)[j].trim();
				String desiredCoreDepositsValues = findAll(rowValuesOnPage)
						.get((i) * columnNumberOnPage + j).getText();

				List<WebElement> headers = findAll("//table[contains(@class, 'GridRowTable')]//th//div[@class='dojoxGridSortNode']");
				if (findAKeywordInList("Order status", headers) != -1 & j == 2
						& !desiredCoreDepositsValues.isEmpty()) {
					String[] dp = desiredCoreDepositsValues.split(" ");
					if (dp.length != 1) {
						desiredCoreDepositsValues = dp[0].substring(0, 1)
								+ dp[dp.length - 1].substring(0, 1);
					} else {
						desiredCoreDepositsValues = dp[0].substring(0, 2)
								.toUpperCase().trim();
					}
				}

				log.info("CSV Number" + i + "," + j + "cell is:"
						+ coreDepositsValue);
				log.info("UI Number" + i + "," + j + "is:"
						+ desiredCoreDepositsValues);
				// verify supplier in desired supplier value list

				Assert.assertTrue(
						verifyCellValueInDesiredValueList(coreDepositsValue,
								desiredCoreDepositsValues),
						"Supplier Code column value[" + coreDepositsValue
								+ "] doesn't match desired Core Deposits"
								+ desiredCoreDepositsValues);
			}
		}

		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * Download PDF from webpage and compare the values on the PDF with the
	 * values on the webpage
	 * 
	 * @param filename
	 *            , desiredTotalNumberOfColumn, xpath
	 * @throws Exception
	 * @author Amy.hu
	 */

	static public void verifyDownloadPDF(String filename,
			String desiredAllColumnNames, int columnNumberOnPage,
			String rowValuesOnPage) throws Exception {

		PDFParser parser;
		PDFTextStripper stripper;
		COSDocument cosDoc;
		Thread.sleep(20000);
		FileInputStream fis = new FileInputStream(downloadDir + File.separator
				+ filename);
		// FileInputStream fis = new FileInputStream("./Download/coreList.pdf");
		parser = new PDFParser(fis);
		parser.parse();
		cosDoc = parser.getDocument();
		stripper = new PDFTextStripper();
		String docText = stripper.getText(new PDDocument(cosDoc));
		// System.out.println(docText);
		String[] rowValue = docText.split(System.getProperty("line.separator"));
		cosDoc.close();

		// Verify PDF Export have desired column name
		Assert.assertTrue(rowValue[0].contains(desiredAllColumnNames.trim()),
				"Downloaded file doesn't match expected column name.");

		// Get last row number on the PDF file
		int rowEnd = rowValue.length;
		// just compare the data on the first page
		if (rowEnd > 13) {
			rowEnd = 12;
		}
		for (int i = 0; i < rowEnd - 1; i++) {
			String desiredCoreDepositsValues = "";
			String coreDepositsValue = rowValue[i + 1];
			for (int j = 0; j < columnNumberOnPage; j++) {
				String v = findAll(rowValuesOnPage).get(
						(i) * columnNumberOnPage + j).getText();
				List<WebElement> headers = findAll("//table[contains(@class, 'GridRowTable')]//th//div[@class='dojoxGridSortNode']");
				if (findAKeywordInList("Order status", headers) != -1 & j == 2
						& !v.isEmpty()) { // Abby:if annotate this part TC1962
											// will pass, I think maybe the data
											// style is changed. So I annotate
											// it.
					String[] dp = v.split(" ");
					if (dp.length != 1) {
						desiredCoreDepositsValues = dp[0].substring(0, 1)
								+ dp[dp.length - 1].substring(0, 1);
					} else {
						desiredCoreDepositsValues = dp[0].substring(0, 2)
								.toUpperCase().trim();
					}
				} else {
					desiredCoreDepositsValues = desiredCoreDepositsValues
							+ findAll(rowValuesOnPage).get(
									(i) * columnNumberOnPage + j).getText();
					// verify supplier in desired supplier value list
				}
			}

			if (filename.contains("OrderHistory")) {
				desiredCoreDepositsValues = desiredCoreDepositsValues.replace(
						"RE-ORDER", "");
			}// there is a Reorder button on the page, but no value on the pdf
				// file

			log.info("UI" + i + desiredCoreDepositsValues.trim());
			log.info("PDF" + i + coreDepositsValue.trim());
			Assert.assertTrue(
					coreDepositsValue
							.replace(" ", "")
							.trim()
							.contains(
									desiredCoreDepositsValues.replace(" ", "")
											.trim()),
					"Core Deposits column value[" + coreDepositsValue
							+ "] doesn't match desired Core Deposits"
							+ desiredCoreDepositsValues);
		}

		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * Get the count number of column that having content for xsl file.
	 * 
	 * @param hssfSheet
	 *            sheet object to count the column number. @ amy.hu
	 * @return the count number of column that having content.
	 * @throws Exception
	 */
	public static int getColumnCountXls(Sheet hssfSheet) throws Exception {
		int count = 0;
		Iterator<Cell> iterator = hssfSheet.getRow(0).cellIterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		return count;
	}

	/**
	 * Validate that CSV document opens up with part numbers *quantity showing
	 * with quantity
	 * 
	 * @author shaine.gu
	 * @throws Exception
	 */
	static public void verifyDownloadCSVWithInfo(String filename, int headers,
			List<String> qtyList, List<String> partNoList) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>();

		// Click on CSV button and verify excel file is downloaded
		// successfully.
		Assert.assertTrue(waitForFileDownloadComplete(filename),
				"Unable to download file");
		Thread.sleep(20000);
		CsvReader reader = new CsvReader(downloadDir + File.separator
				+ filename);
		reader.readHeaders();
		while (reader.readRecord()) {
			csvList.add(reader.getValues());
		}
		reader.close();
		// Verify CSV Export have desired column count
		Assert.assertEquals(reader.getHeaderCount(), headers,
				"Downloaded file doesn't match expected column count.");
		int rowEnd = csvList.size();
		for (int i = 0; i <= rowEnd - 1; i++) {
			int count = reader.getHeaderCount();
			for (int j = 0; j <= count - 1; j++) {
				if (j == 0) {
					Assert.assertEquals(csvList.get(i)[j], qtyList.get(i));
				} else if (j == 1) {
					Assert.assertEquals(csvList.get(i)[j], partNoList.get(i));
				}

			}

		}

	}

	/**
	 * Validate that excel document opens up with part numbers *quantity showing
	 * with quantity
	 * 
	 * @author shaine.gu
	 * @param filename
	 * @param headers
	 * @param qtyList
	 * @param partNoList
	 * @throws Exception
	 */
	static public void verifyDownloadExcelWithInfo(String filename,
			int headers, List<String> qtyList, List<String> partNoList)
			throws Exception {
		// Click on Excel button and verify excel file is downloaded
		// successfully.
		Assert.assertTrue(FileUtil.waitForFileDownloadComplete(filename),
				"Unable to download file");

		// Define fs, hssfsheet
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				downloadDir + File.separator + filename));
		hssfSheet = new HSSFWorkbook(fs).getSheetAt(0);

		// Verify Excel Export have desired column count
		Assert.assertEquals(getColumnCount(hssfSheet), headers,
				"Downloaded file doesn't match expected column count.");
		// iterateCells(hssfSheet);

		// Get last row number
		int rowEnd = hssfSheet.getLastRowNum();

		// Loop each row of record in excel
		for (int i = 0; i <= rowEnd - 1; i++) {
			// int count=getColumnCount(hssfSheet);

			for (int j = 0; j <= headers - 1; j++) {
               log.info("i is::::"+i);
				if (j == 0) {
					Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
							.getStringCellValue().trim(), qtyList.get(i));
				} else if (j == 1) {
					Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
							.getStringCellValue().trim(), partNoList.get(i));
				}

			}

		}
		fs.close();
		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * Validate that pdf document opens up with part numbers *quantity showing
	 * with quantity
	 * 
	 * @author shaine.gu
	 * @param filename
	 * @param headers
	 * @param qtyList
	 * @param partNoList
	 * @throws Exception
	 */
	static public void verifyDownloadPdfWithInfo(String filename, int headers,
			List<String> qtyList, List<String> partNoList) throws Exception {
		PDFParser parser;
		PDFTextStripper stripper;
		COSDocument cosDoc;
		Thread.sleep(20000);
		FileInputStream fis = new FileInputStream(downloadDir + File.separator
				+ filename);
		// FileInputStream fis = new FileInputStream("./Download/coreList.pdf");
		parser = new PDFParser(fis);
		parser.parse();
		cosDoc = parser.getDocument();
		stripper = new PDFTextStripper();
		String docText = stripper.getText(new PDDocument(cosDoc));

		// System.out.println(docText);
		String[] rowValue = docText.split(System.getProperty("line.separator"));
		cosDoc.close();

		// Get last row number on the PDF file
		int rowEnd = rowValue.length;
		// just compare the data on the first page
		for (int i = 1; i <= rowEnd - 1; i++) {
			String[] coreDepositsValue = rowValue[i].split(" ");
			for (int j = 0; j < headers; j++) {
				if (j == 0) {
					Assert.assertEquals(coreDepositsValue[j],
							qtyList.get(i - 1));
				} else if (j == 1) {
					Assert.assertEquals(coreDepositsValue[j],
							partNoList.get(i - 1));
				}
			}
		}
		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * @author shaine.gu
	 * @param filename
	 * @param headers
	 * @param orderNoList
	 * @param orderDateList
	 * @param purchaseOrderNumList
	 * @param serialNoList
	 * @param modelList
	 * @param orderAmountList
	 * @throws Exception
	 */
	static public void verifyDownloadExcelWithInfo1(String filename,
			int headers, List<String> orderNoList, List<String> orderDateList)
			throws Exception {
		String elementInExcel;
		Assert.assertTrue(FileUtil.waitForFileDownloadComplete(filename),
				"Unable to download file");
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				downloadDir + File.separator + filename));
		hssfSheet = new HSSFWorkbook(fs).getSheetAt(0);
		Assert.assertEquals(getColumnCount(hssfSheet), headers,
				"Downloaded file doesn't match expected column count.");
		int rowEnd = hssfSheet.getLastRowNum();

		// Loop each row of record in excel
		for (int i = 0; i <= rowEnd - 1; i++) {
			// int count=getColumnCount(hssfSheet);

			for (int j = 0; j <= headers - 1; j++) {
				// elementInExcel = hssfSheet.getRow(i + 1).getCell(j)
				// .getStringCellValue().trim();
				switch (j) {
				case 0:
					Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
							.getStringCellValue().trim(), orderNoList.get(i));
					break;
				case 1:
					Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
							.getStringCellValue().trim(), orderDateList.get(i));
					break;
				/*
				 * case 2: Assert.assertEquals(hssfSheet.getRow(i +
				 * 1).getCell(j) .getStringCellValue().trim(),
				 * purchaseOrderNumList.get(i)); break; case 3:
				 * Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
				 * .getStringCellValue().trim(), serialNoList.get(i)); break;
				 * case 4: Assert.assertEquals(hssfSheet.getRow(i +
				 * 1).getCell(j) .getStringCellValue().trim(),
				 * modelList.get(i)); break; case 5:
				 * Assert.assertEquals(hssfSheet.getRow(i + 1).getCell(j)
				 * .getStringCellValue().trim(), orderAmountList.get(i)); break;
				 */

				}
			}

		}
		fs.close();
		// clean download folder
		emptyDownloadFolder();
	}

	/**
	 * @author shaine.gu
	 * @param filename
	 * @param headers
	 * @param orderNoList
	 * @param orderDateList
	 * @param purchaseOrderNumList
	 * @param serialNoList
	 * @param modelList
	 * @param orderAmountList
	 * @throws Exception
	 */
	static public void verifyDownloadCSVWithInfo1(String filename, int headers,
			List<String> orderNoList, List<String> orderDateList)
			throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		String elementInCsv;
		// Click on CSV button and verify excel file is downloaded
		// successfully.
		Assert.assertTrue(waitForFileDownloadComplete(filename),
				"Unable to download file");
		Thread.sleep(20000);
		CsvReader reader = new CsvReader(downloadDir + File.separator
				+ filename);
		reader.readHeaders();
		while (reader.readRecord()) {
			csvList.add(reader.getValues());
		}
		reader.close();
		// Verify CSV Export have desired column count
		Assert.assertEquals(reader.getHeaderCount(), headers,
				"Downloaded file doesn't match expected column count.");
		int rowEnd = csvList.size();
		for (int i = 0; i <= rowEnd - 1; i++) {
			int count = reader.getHeaderCount();
			for (int j = 0; j <= count - 1; j++) {
				elementInCsv = csvList.get(i)[j];
				switch (j) {
				case 0:
					Assert.assertEquals(elementInCsv, orderNoList.get(i));
					break;
				case 1:
					Assert.assertEquals(elementInCsv, orderDateList.get(i));
					break;
				/*
				 * case 2: Assert.assertEquals(elementInCsv,
				 * purchaseOrderNumList.get(i)); break;
				 * 
				 * case 3: Assert.assertEquals(elementInCsv,
				 * serialNoList.get(i)); break; case 4:
				 * Assert.assertEquals(elementInCsv, modelList.get(i)); break;
				 * case 5: Assert.assertEquals(elementInCsv,
				 * orderAmountList.get(i)); break;
				 */
				}

			}

		}
	}

	static public void verifyDownloadPdfWithInfo1(String filename, int headers,
			List<String> orderNoList, List<String> orderDateList)
			throws Exception {
		PDFParser parser;
		PDFTextStripper stripper;
		COSDocument cosDoc;
		Thread.sleep(20000);
		FileInputStream fis = new FileInputStream(downloadDir + File.separator
				+ filename);
		// FileInputStream fis = new FileInputStream("./Download/coreList.pdf");
		parser = new PDFParser(fis);
		parser.parse();
		cosDoc = parser.getDocument();
		stripper = new PDFTextStripper();
		String docText = stripper.getText(new PDDocument(cosDoc));

		// System.out.println(docText);
		String[] rowValue = docText.split(System.getProperty("line.separator"));
		cosDoc.close();

		// Get last row number on the PDF file
		int rowEnd = rowValue.length;
		// just compare the data on the first page
		for (int i = 1; i <= rowEnd - 1; i++) {
			String[] coreDepositsValue = rowValue[i].split(" ");
			for (int j = 0; j < headers; j++) {

				switch (j) {
				case 0:
					Assert.assertEquals(coreDepositsValue[j],
							orderNoList.get(i - 1));
					break;
				case 1:
					Assert.assertEquals(coreDepositsValue[j],
							orderDateList.get(i - 1));

					break;
				/**
				 * case 2: Assert.assertEquals(coreDepositsValue[j],
				 * purchaseOrderNumList.get(i-1)); break; case 3:
				 * if((!"".equals(
				 * serialNoList.get(i-1)))&&coreDepositsValue.length<=4){
				 * Assert.assertEquals(coreDepositsValue[j],
				 * serialNoList.get(i-1)); } break;
				 * 
				 * case 4:
				 * if((!"".equals(modelList.get(i-1)))&&coreDepositsValue
				 * .length>=5){ Assert.assertEquals(coreDepositsValue[j],
				 * modelList.get(i-1)); } break; case 5:
				 * if((!"".equals(orderAmountList
				 * .get(i-1)))&&coreDepositsValue.length>=6){
				 * Assert.assertEquals(coreDepositsValue[j],
				 * orderAmountList.get(i-1)); } break;
				 * 
				 */
				}

			}

		}

		// clean download folder
		emptyDownloadFolder();
	}
	
	
	  /**
     * Read values from a specific row in a specific excel sheet
     * @author matthew.feng
     * @param xssfSheet
     * @param rowNum
     * @return
     * @throws Exception
     */
    public static List<String> getValuesOfSpecificRow(XSSFSheet xssfSheet, int rowNum) throws Exception{
        List<String> values = new ArrayList<String>();
        int numOfValues = xssfSheet.getRow(rowNum).getLastCellNum();
        for (int i=0; i<numOfValues; i++) { //read values starting from the 2nd column because the first column is section ID
            values.add(xssfSheet.getRow(rowNum).getCell(i).toString());
        }
        return values;
    }
    
    /**
     * To find an excel sheet by searching the sheet name.
     * @author matthew.feng
     * @param wb
     * @param sheetName
     * @return
     * @throws Exception
     */
    public static XSSFSheet findExcelSheetByName(XSSFWorkbook wb, String sheetName) throws Exception{
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            String actualSheetName = wb.getSheetAt(i).getSheetName();
            System.out.println("Name = "+actualSheetName);
            if(sheetName.equalsIgnoreCase(actualSheetName)){
                return wb.getSheetAt(i);
            }
        }
        return null;
    }
    
    /** **********************Excel Functions **************************************/
    /**
     * To read a list of input values from an excel sheet in an excel workbook.
     * @param fileName   the workbook name
     * @param sheetName  the excel sheet name, i.e. the test case ID in the excel tab
     * @param rowNum     the row number where the list of input values are
     * @return            the input values read from the specific row
     * @throws Exception
     */
    public static List<String> readDataFromSpecificRowAndSheet(String fileName, String sheetName, int rowNum) throws Exception{
        // Define Input Stream for desired file
        InputStream fs = new FileInputStream(testDataDir +File.separator +fileName);
        // Define Workbook and Sheet
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        //find the excel sheet whose name is same to sheet name
        XSSFSheet xssfSheet = findExcelSheetByName(wb,sheetName);
        List<String> returnList = getValuesOfSpecificRow(xssfSheet,rowNum);
        fs.close();
        return returnList;
    }

}
