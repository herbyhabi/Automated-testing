package com.perficient.test.cat.catinspectweb.basepages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignFormPage extends BasicPage{
	
	//Selector: Manufacturer select
    @FindBy(xpath="//select[@id='manufacturer']")
    public WebElement selectorOfManufactuter;
    
    //Selector: Family Name select
    @FindBy(xpath="//select[@id='familyname']")
    public WebElement selectorOfFamilyName;

    //Input: Serial Number
    @FindBy(xpath="//input[@id='serialnumber']")
    public WebElement inputOfSerialNumber;
    
    //Text: Serial Number warning message
    @FindBy(xpath="//div[@class='alert alert-danger']")
    public WebElement textOfWarningMessage;
    
    //Input: Model Name select
    @FindBy(xpath="//input[@id='modelname']")
    public WebElement inputOfModelName;
    
    // Icon: Next icon
    @FindBy(xpath="//a[contains(@class,'right-arrow') and @href='#inspectionformtab']")
    public WebElement iconOfNexticon;
    
    //Selector: Filter by Type select
    @FindBy(xpath="//select[@name='sff_type']")
    public WebElement selectOfFilterByType;
    
    // Icon: Next icon
    @FindBy(xpath="//a[contains(@class,'right-arrow') and @href='#recipienttab']")
    public WebElement iconOfNexticon2;
    
    //List: Inspection Form list
    @FindBy(xpath="//input[@id='familyradio']")
    public List<WebElement> listOfInspectionForm;
    
    //Input:Assignee input
    @FindBy(xpath="//input[@name='usersearchtext']")
    public WebElement inputOfAssign;
    
    //Button: Assignee Name button
    @FindBy(xpath="//input[@id='assigneeOptRadio0']")
    public WebElement buttonOfAssigneeName;
    
    //Icon: Next icon
    @FindBy(xpath="//a[contains(@class,'right-arrow') and @href='#reviewtab']")
    public WebElement iconOfNexticon3;
    
    //Icon: Submit icon
    @FindBy(xpath="//span[@class='basic-icons upload pull-right']")
    public WebElement iconOfSubmit;
    
    //text:Assign Inspection confirm
    @FindBy(xpath="(//h3//span)[2]")
    public WebElement textOfAssignInspectionConfirm;
    
    //List: Confirm button on dialog box
    @FindBy(xpath="//button[@class ='btn btn-success']//span")
    public WebElement buttonOfconfirm;
    
    //text:Assign Inspection successfully
    @FindBy(xpath="//div[@id='toast-container']")
    public WebElement textOfAssignInspectionSuccessfully;
    
    //text:Assign Inspection successfully
    @FindBy(xpath="//div[@id='toast-container'/div]")
    public WebElement textOfAssignInspectionSuccessfully2;
    
    //text:Selected Inspection Form Name
    @FindBy(xpath="//b[@class='text-info']")
    public List<WebElement> listOfInspectionFormName;
}
