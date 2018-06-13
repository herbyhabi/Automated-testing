package com.perficient.test.cat.catinspectweb.basepages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAdministrationPage extends BasicPage {
	
	//List: User name
	@FindBy(xpath="//table[@id= 'table_width']//tr//td[2]")
    public List<WebElement> listOfUserName;
	
	//List: Account Type
	@FindBy(xpath="//table[@id= 'table_width']//tr//td[3]")
	public List<WebElement> listOfAccountType;
	
	//List: CatRecId
	@FindBy(xpath="//table[@id= 'table_width']//tr//td[4]")
    public List<WebElement> listOfCatRecId;
	
	//List: Organization id
	@FindBy(xpath="//table[@id= 'table_width']//tr//td[5]")
    public List<WebElement> listOfOrganizationId;
	
	//List: Edit icon
	@FindBy(xpath="//table[@id= 'table_width']//tr//td[6]/span")
	public List<WebElement> listOfEditIcon;
	
	//Icon: User icon
	@FindBy(xpath="//span[@title = 'User By CatRecId']")
    public WebElement iconOfUserByCatRecId;
	
	//Icon: Refresh icon
	@FindBy(xpath="//span[@title = 'Refresh']")
	public WebElement iconOfRefresh;
	
/**
 * The following three elements(Name, CatRecId, Organization) are belong to User Admin Page.
 * 
 */
	//Input: Search By Name in user admin page
	@FindBy(xpath="//input[@placeholder= 'Search by Name' ]")
	public WebElement inputOfSearchByName;
	
	//Input: Search by CatRecId in User Admin page
	@FindBy(xpath="//input[@placeholder= 'CatRecID']")
	public WebElement inputOfSearchByCatRecId;
	
	//Input: Search By Organization id in User Admin page
	@FindBy(xpath="//input[@placeholder= 'Organization']")
	public WebElement inputOfSearchByOrganizationId;
	
/**
 * The following three elements(Name, Organization, CatrecId) are belong to User Profile dialog box.
 * 
 */
	//Input: Name in User Profile dialog box
	@FindBy(xpath="//input[@name= 'name']")
	public WebElement inputOfUserName;
	
	//Input: Organization id in User Profile dialog box.
	@FindBy(xpath="//input[@name= 'organization']")
	public WebElement inputOfOrganizationId;
	
	//Input: CatRecId field in User Profile dialog box
	@FindBy(xpath="//input[@name= 'search']")
	public WebElement inputOfCatRecId;
	
	
	//Button: Search button
	@FindBy(xpath="//div[@class = 'form-group']//button[@type = 'button']")
	public WebElement btnOfSearch;
	
	//Text: text when the catRecId is invalid.
	@FindBy(xpath="(//div[@class = 'form-group'])[2]")
	public WebElement textOfMessageForValidCatRecId;
	
	
	
	
	
	
	
	
	
	//Button: OK button on User profile dialog box.
	@FindBy(xpath="//div[@class='modal-footer']//button[contains(@class,'btn btn-success')]")
	public WebElement btnOfOK;
	
	//Button: Cancel button on user profile dialog box.
	@FindBy(xpath="//div[@class='modal-footer']//button[@class ='btn btn-danger']")
	public WebElement btbtnOfCancel;
	
	//Text: Error message
	@FindBy(xpath="//div[@class = 'alert alert-danger']")
    public List<WebElement> listOfErrorMessage;
	


	//sort arrow: Name
	@FindBy(xpath="//span[@sort-by='Name']/span")
	public WebElement sortArrowOfName;
	
	//sort arrow: Account Types
	@FindBy(xpath="//span[@sort-by='Account_Type']/span[@class='cat_sort_arrows']")
	public WebElement sortArrowOfAccountType;
	
	//sort arrow: CatRecID
	@FindBy(xpath="//span[@sort-by='CatRecID']/span[@class='cat_sort_arrows']")
	public WebElement sortArrowOfCatRecID;
	
	//sort arrow: Organization
	@FindBy(xpath="//span[@sort-by='Organization']/span[@class='cat_sort_arrows']")
	public WebElement sortArrowOfOrganization;
	
	
	//Selector: Selector of Account Types
	@FindBy(xpath="//select")
	public WebElement selectorOfAccountTypes;
	


}
