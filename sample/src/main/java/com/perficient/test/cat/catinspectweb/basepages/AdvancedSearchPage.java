package com.perficient.test.cat.catinspectweb.basepages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchPage extends BasicPage {
	
	
	//Button: Search button.
	@FindBy(xpath="//button[contains(@class,'btn btn-default btn-success btn-click')]")
	public WebElement btnOfSearch;
	
	//Input: Text for search
	@FindBy(xpath="//div[@class = 'row' ]/input")
	public WebElement inputOfSearch;
	
	//Text: No Data
	@FindBy(xpath="//span[@class = 'text-center']")
	public WebElement textOfNoData;
	
	//List: Serial Number list
	@FindBy(xpath="//table[@id = 'table_width']//tr//td[1]//img")
	public List<WebElement> listOfStatus;
	
	//List: Serial Number list
	@FindBy(xpath="//table[@id = 'table_width']//tr//td[2]")
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
	
	//List: Header Column.
	@FindBy(xpath="//table[@id='insp-head-table']//th")
    public List<WebElement> listOfHeaderColumns;
}
