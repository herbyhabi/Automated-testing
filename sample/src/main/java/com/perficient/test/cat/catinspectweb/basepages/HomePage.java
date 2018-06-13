package com.perficient.test.cat.catinspectweb.basepages;


import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.clickByJavaScripts;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.emptyDownloadFolder;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.verifyDownloadFileSuccessfully;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage extends BasicPage {
    //tab: Create Form tab
    @FindBy(xpath="//span[@class='create']")
    public WebElement tabOfCreateForm;
    
    //tab: Advanced Search tab
    @FindBy(xpath="//span[@class='advSearch']")
    public WebElement tabOfAdvancedSearch;
    
    //tab: Manage Form tab
    @FindBy(xpath="//span[@class='manage_form']")
    public WebElement tabOfManageForm;
    
    //tab: User Admin tab
    @FindBy(xpath="//span[@class='admin']")
    public WebElement tabOfUserAdmin;
    
    //tab: Home Page tab
    @FindBy(xpath="//span[@class='home']")
    public WebElement tabOfHomePage;
    
    //tab: Assign Page tab
    @FindBy(xpath="//span[@class='assign']")
    public WebElement tabOfAssignPage;
    
    //List of the i icons of all the inspections
    @FindBy(xpath="//td[contains(@class, 'icons-col')]//a[@title='Details']")
    public List<WebElement> listOfDetailsIcon;

    //List: List of printing PDF file in home page
    //List of the i icons of all the inspections
    @FindBy(xpath="//a[@title='PDF']")
    public List<WebElement> listOfPrintDOF;

    //List: List Of PDF to download in home page.
    @FindBy(xpath="//span[contains(text(),\"Order PDF\")]")
    public List<WebElement> listOfPDFDownloadType;

    //List: List of Download PDF icon in PDF Download dialog box.
    @FindBy(xpath="//span[@class = 'glyphicon glyphicon-save']")
    public List<WebElement> listOfDownloadPDFIcon;
    
    //Select:select status selector
    @FindBy(xpath="//select[@name='statusselect']")
    public WebElement SelectorOfStatus;
    
    //List:list of S/N
    @FindBy(xpath="//tr/td[@class='inspections-row'][2]//a[contains(@href,'details') ]")
    public List<WebElement> listOfSerialNumber;
    
    //Button: default button
    @FindBy(xpath="//button[@class='btn btn-default']")
    public WebElement buttonOfDefault;
    
    //bar: No data bar
    @FindBy(xpath="//div[@class = 'text-center']")
    public WebElement barOfNoData;
    
    //Icon: Hamburger
    @FindBy(xpath="//span[@class = 'glyphicon glyphicon-menu-hamburger']")
    public WebElement iconOfHamburger;
    
    //List: Sub menu in the hamburger menu
    @FindBy(xpath="//a[@data-toggle= 'dropdown']")
    public List<WebElement> listOfSubMenu;
    
    //Input: Dealer Search 
    @FindBy(xpath="//input[@id =  'dealerSearch']")
    public WebElement inputOfDealerSearch;
    
    //List: Drop-Down list of dealer
    @FindBy(xpath="//li[@class = 'dropdown-submenu open']//div[@class='item']")
    public List<WebElement> dropDownListOfDealerOption;
    
    //Button: Confirm button for dealer code.
    @FindBy(xpath="//li[@class = 'dropdown-submenu open']//span[@class='glyphicon glyphicon-ok']")
    public WebElement btnOfConfirmForDealerCode;
    
    //Button: OK button for confirming to login as a dealer.
    @FindBy(xpath="//div[@class = 'modal-footer']//span[@class='glyphicon glyphicon-ok']")
    public WebElement btnOfOKForloginAsDealer;
    
    //Text: Confirmation for login as a dealer
    @FindBy(xpath="//h3//span[contains(text(),'Are you sure')]")
    public WebElement textOfConfirmationOfLoginAsDealer;
    
	//List: Header Column.
	@FindBy(xpath="//table[@id='insp-head-table']//th")
    public List<WebElement> listOfHeaderColumns;
	
	//icon: Export to CSV
	@FindBy(xpath="//span[@title='Export To CSV']")
    public WebElement iconOfExportToCSV;
	
	
		//List: Serial Number list
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[1]//img")
		public List<WebElement> listOfStatus;
		
		//List: Serial Number list
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[2]/a")
	    public List<WebElement> listOfSN;
		
		//List: Asset Id
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[3]")
	    public List<WebElement> listOfAssetId;
		
		//List: Type
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[4]")
	    public List<WebElement> listOfType;
		
		//List: Form name
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[5]/a")
	    public List<WebElement> listOfFormName;
		
		//List: Inspector name
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[6]")
	    public List<WebElement> listOfInspectorName;
		
		//List: Assign Date
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[7]")
	    public List<WebElement> listOfAssignDate;
		
		//List: Complete Date
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[8]")
	    public List<WebElement> listOfCompleteDate;
		
		//List: Sync Time
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[9]")
	    public List<WebElement> listOfSyncTime;
		
		//List: Customer
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[10]")
	    public List<WebElement> listOfCustomer;
		
		//List: Red box in Summary column
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[11]//span[contains(@class,'red')]")
		public List<WebElement> listOfSummaryRedBox;
		
		//List: Yellow box in Summary column
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[11]//span[contains(@class,'yellow')]")
		public List<WebElement> listOfSummaryYellowBox;
		
		//List: Green box in Summary column
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[11]//span[contains(@class,'green')]")
		public List<WebElement> listOfSummaryGreenBox;
				
		//List: Grey box in Summary column
		@FindBy(xpath="//table[@id = 'table_width']//tr//td[11]//span[contains(@class,'grey')]")
		public List<WebElement> listOfSummaryGreyBox;
				
		
		
    /**
     * Locate the index of serial number 
     * @author herby.he
     * @param serialNumber
     * @return
     * @throws Exception
     */
    public int locateSerialNumber(String serialNumber) throws Exception {
    	for(int i = 0; i<listOfSerialNumber.size();i++) {
    		if(listOfSerialNumber.get(i).getText().trim().contains(serialNumber)) {
    			return i;
    		}
    	}
    	return -1;
		
	}
    
	public int gettheDealerFromTheList(String dealerCode) throws Exception {
		for(int i =0;i<dropDownListOfDealerOption.size();i++) {
			if(dropDownListOfDealerOption.get(i).getText().trim().contains(dealerCode)) {
				return i;
			}
		}
			return -1;	
	}
	
	/**
	 * To verify the downloaded csv file name is correct.
	 * @author matthew.feng
	 * @param listInTable
	 * @throws Exception
	 */
	public void verifyExportedCSVFile() throws Exception{
		emptyDownloadFolder();
		clickByJavaScripts(iconOfExportToCSV, "Click on the icon of Export to CVS");
		String fileName = verifyDownloadFileSuccessfully();	
		customAssertion.assertTrue(fileName.split("\\.")[1].contains("csv"),"The downloaded file name is NOT correct.");
	}
	
	
	
    

    
    
    
    
}