package com.perficient.test.cat.catinspectweb.basepages.unit;

import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TablePortlet extends Portlet {
	
	private String formateRow;
	private String formatCell;
	private String formatCol;
	private String formatCol01;
	private String formatHeaderCol;
	private String formatProductColumnLink;
	private String formatProductLinkInOrderConfirmation;
	private String formatProductNumber;
	private String formatUnitWeight;
	private String formatAvailability;
	private String formatQTY;
	private String formatEach;
	private String formatTotal;
	private String formatTotal2;
	private String formatEach2;
	private String formatEach3;
	private String formatEach4;
	private String formatEach5;
	private String formatEachCoreDeposit;
	private String formatEachCoreDepositprice;
	private String formatAvailability1;
	private String formatEachUnit;
	private String formatTOTALUnit;
	private String formatEachReplace;
	private String formatRequestedDeliveryDate;
	private String formatEachPrice;

	public TablePortlet(String portletName, String locatorPrefix) {
		super(portletName,locatorPrefix);
		init();
	}
	
	/**
	 * @author icey.qi Added by matthew.feng
	 * init row and col in the table
	 */
	private void init() {
		this.formateRow = "((" + locatorPrefix + "//tbody)[1]/tr)[%s]";
		this.formatCol = "(" + locatorPrefix + "//tbody)[1]/tr/td[%s]";
		this.formatCell = ("(" + formateRow + "/td)[%s]");
		this.formatCol01="(" + locatorPrefix + "//tbody)[1]/tr/td[%s][@id]";
		this.formatProductColumnLink = formatCol01+"//a[@class='hover_underline']";
		this.formatProductLinkInOrderConfirmation = formatCol+"//p[@class='strong_content']";
		this.formatProductNumber = formatCol+"//span[contains(text(),'Part Number')]";
		this.formatUnitWeight = formatCol+"//span";
		this.formatAvailability = formatCol+"//td[contains(@id,'td_6_1')]";
		this.formatQTY = formatCol+"//input[contains(@id,'qty')]";
		this.formatEach = formatCol+"//span";
		this.formatEach5=formatCol+"//span";//This is for "Unable to Retrieve Price" message displayed in Each column.
		
		this.formatTotal = formatCol +"//span[@class='space_class']";
		this.formatTotal2 = formatCol +"//span[@class='price']";
		this.formatEach2 = formatCol +"//span[@class='sisMatchPLPInfoLink']";
		this.formatEachCoreDepositprice=formatCol+"/div/div[@style]/div[2]";
		this.formatEachCoreDeposit=formatCol+"/div/div[@style]/div[1]";
		this.formatEach3 = formatCol;
		this.formatEach4 = formatCol +"//span[@class='space_class'][1]";
		this.formatAvailability1=formatCol+"//a[contains(text(),'Contact Dealer')]";
        this.formatEachUnit=formatCol+"//span[2]";
		this.formatEachReplace=formatCol+"//span[3]";
		this.formatTOTALUnit=formatCol+"//div";
		this.formatRequestedDeliveryDate = locatorPrefix + "/..//input[contains(@id,'requestedShippingDate')]";
		this.formatEachPrice = formatCol +"/span[@class='space_class']";
	}
	
	/**
	 * Acquire the WebElemnt of which row according to the index(row number)
	 */
	public WebElement getRow(int index) {
		return find(formateRow, index+1);
	}
	
	/**
	 * @author matthew.feng Acquire a list of WebElemnt of which column
	 *         according to the index(column number)
	 */
	public List<WebElement> getCol(int index) {
		return findAll(formatCol, index);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of product link elements from the 1st column (Product Column) of Shopping Cart Page
	 */
	public List<WebElement> getProductLinks() {
		log.info("formatProductColumnLink "+formatProductColumnLink);
		return findAll(formatProductColumnLink, 3);
	}
	
	public List<WebElement> getProductLinks1() {
		log.info("formatProductColumnLink "+formatProductColumnLink);
		return findAll(formatProductColumnLink, 2);
	}
	
	/**
	 * @author abby.zhang
	 * Acquire a list of product Number in shopping cart page**/
	public List<WebElement> getProductNumbers() {
		return findAll(formatProductNumber,2);
	}
	
	public List<WebElement> getProductLinksINOrderConfirmation() {
		return findAll(formatProductLinkInOrderConfirmation,1);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of Unit Weight elements from the 2nd column (Unit Weight Column) of Shopping Cart Page
	 */
	public List<WebElement> getUnitWeight() {
		return findAll(formatUnitWeight, 2);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of Availability elements from the 3rd column (Availability Column) of Shopping Cart Page
	 */
	public List<WebElement> getAvailability() {
		return findAll(formatAvailability, 3);
	}
	
	/**
	 * @author jenny.sun 
	 * Acquire a list of Availability elements from the 5rd column (Availability Column) of Shopping Cart Page
	 */
	
	public List<WebElement> getAvailability1(){
		log.info("Availability is:"+formatAvailability1);
		return findAll(formatAvailability1,4);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of QTY elements from the 4th column (QTY Column) of Shopping Cart Page
	 */
	public List<WebElement> getQTY() {
		return findAll(formatQTY, 1);
	}
	
	public List<WebElement> getQTYForRegisterAccount() {
		return findAll(formatQTY, 2);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of the first EACH element from the 5th column (EACH Column) of Shopping Cart Page
	 */
	public List<WebElement> getEach() {
		log.info("formatEach =   "+ formatEach);
		return findAll(formatEach, 5);
	}
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of the second EACH Elements elements from the 5th column (EACH Column) of Shopping Cart Page
	 */
	public List<WebElement> getEach2() {
		log.info("format Each2 =   "+ formatEach2);
		return findAll(formatEach2, 7);
	}
	
	
	public List<WebElement> getEach3(){
		log.info("format Each3 =   "+ formatEach3);
		return findAll(formatEach3, 5);
	}
	
	public List<WebElement> getEach4ForRegisterAccount(){
		log.info("format Each4 =   "+ formatEach4);
		return findAll(formatEach4, 7);
	}
	
	public List<WebElement> getEach4() {
		log.info("formate Each4 = "+formatEach4);
		return findAll(formatEach4,5);
		
	}
	
	public List<WebElement> getEach5() {
		log.info("formate Each5 = "+formatEach5);
		return findAll(formatEach5,7);
		
	}
	
	
	public List<WebElement> getFormatEachCoreDeposit() {
		log.info("formatEachCoreDeposit =   "+ formatEachCoreDeposit);
		return findAll(formatEachCoreDeposit, 7);
	} 
	
	
	
	public List<WebElement> getEachPrice(){
		log.info("format Each3 =   "+ formatEach3);
		return findAll(formatEachPrice, 7);
	}
	
	public List<WebElement> getFormatEachCoreDepositprice() {
		log.info("formatEachCoreDepositprice =   "+ formatEachCoreDeposit);
		return findAll(formatEachCoreDepositprice, 5);
	} 
	
	public List<WebElement> getFormatEachCoreDepositpriceForRegisterAccount() {
		log.info("formatEachCoreDepositprice =   "+ formatEachCoreDeposit);
		return findAll(formatEachCoreDepositprice, 7);
	} 
	
	/**
	 * @author matthew.feng 
	 * Acquire a list of TOTAL elements from the 6th column (TOTAL Column) of Shopping Cart Page
	 */
	public List<WebElement> getTotal() {
		log.info("format Total =   "+ formatTotal);
		return findAll(formatTotal, 7);
	}
	
	
	public List<WebElement> getTotalForRegisterAccount() {
		log.info("format Total =   "+ formatTotal2);
		return findAll(formatTotal2, 8);
	}

	/**
	 * acquire the specific WebElement of table cell according to the row and
	 * column
	 */
	public WebElement getCell(int row, int col) {
		log.info("formatCell:::::"+formatCell);
		return find(formatCell, row+1, col);
	}
	
	/**
	 * acquire the specific WebElement inside of table cell according to the row
	 * ,column and the given element tagname
	 */
	public WebElement getCellAs(int row, int col, String element) {
		return getCell(row, col).findElement(By.tagName(element));
	}

	/**
	 * acquire the specific WebElement inside of table cell according to the row
	 * ,column and the given element tagname
	 */
	public List<WebElement> getCellAsAll(int row, int col, String element) {
		return getCell(row, col).findElements(By.tagName(element));
	}

	/**
	 * get the row count except header row
	 */
	public int getRowCount() {
		return findAll(formateRow, "*").size()-1;
	}

	public int getColumnCount() {
		return getHeader().size();
	}
	
	public List<WebElement> getHeader() {
		return getRow(0).findElements(By.tagName("th"));
	}
	
	public List<WebElement> getFormatEachUnit() {
		return findAll(formatEachUnit, 6);
	}
	
	public List<WebElement> getFormatTotalUnit() {
		return findAll(formatTOTALUnit, 7);
	}
	
	public List<WebElement> getformatEachReplace() {
		return findAll(formatEachReplace, 5);
	}
	
	public WebElement getformatRequestedDeliveryDate() {
		return find(formatRequestedDeliveryDate, 0);
	}
}
