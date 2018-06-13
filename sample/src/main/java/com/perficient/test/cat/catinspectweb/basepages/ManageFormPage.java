package com.perficient.test.cat.catinspectweb.basepages;

import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.tryAllConvertStringToDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageFormPage extends BasicPage{
	//Select Filter type
	@FindBy(xpath="//div[@class='col-xs-6 cat_right_border']//select[contains(@class,'cat_status_select')]")
    public WebElement selectorOfFilter;
	
	//Select Family selector
    @FindBy(xpath="//div[@class='col-xs-6']//select[contains(@class,'cat_status_select')]")
    public WebElement selectorOfselectfamily;

    //Option: Filter Type
    @FindBy(xpath="//div[@class='col-xs-6 cat_right_border']/select/option")
    public List<WebElement> listOfFilterType;

    //search icon
    @FindBy(xpath="//span[@class='glyphicon glyphicon-search']")
    public WebElement iconOfSearch;
    
    //Button: upload button
    @FindBy(xpath="//i[contains(@class, 'upload')]")
    public WebElement btnOfUpload;


    //Input : Serial Number Prefix
    @FindBy(xpath="//input[@placeholder = 'Serial Number Prefix']")
    public WebElement inputOfSerialNumberPrefix;

    //Input: search field
    @FindBy(xpath="//div[@class = 'cat_search_filter col-xs-3']/input")
    public WebElement inputOfSearchValue;
    


    //List: List of question shown in Form Information when click on category id.
    @FindBy(xpath="//div[@class = 'queBorder']")
    public List<WebElement> listOfQuestion;

    //List: slider color
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[1]/span")
    public List<WebElement> listOfSliderColor;

    //List: Serial number
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[2]/span")
    public List<WebElement> listOfSerialNumberPrefix;
    
    //List: Type
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[3]")
    public List<WebElement> listOfType;

    //List:Form Name
    @FindBy(xpath="//td/a[@href='javascript:void(0)']")
    public List<WebElement> listOfFormName;
    
    //List: Created By
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[5]")
    public List<WebElement> listOfCreator;
    
    //List: Last Modified Date
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[6]")
    public List<WebElement> listOfModifiedDate;

    //List: Form number
    @FindBy(xpath="(//table[@id= 'table_width']//tr[@class = 'formActionHover'])//td[7]")
    public List<WebElement> listOfFormNumber;
    
    //List: form Actions in the last column, i.e. the column containing open, download and delete icons.
    @FindBy(xpath="//table[@id= 'table_width']//td[@class='actions']")
    public List<WebElement> listOfFormActions;

    //List: Archive/Active
    @FindBy(xpath="//td/i[1][contains(@class, 'material-icons')]")
    public List<WebElement> listOfCheckBox;

    //Selector: All Forms
    @FindBy(xpath="//div[@class= 'cat_date_filter cat_right_border col-xs-4']/select")
    public WebElement selectorOfFormStatus;
    
    //Selector: All Types
    @FindBy(xpath="//div[@class = 'col-xs-4 cat_right_border']/select")
    public WebElement selectorOfFormType;
    
    //Option list: FormStatus
    @FindBy(xpath="//div[@class ='cat_date_filter cat_right_border col-xs-4']/select/option")
    public List<WebElement> listOfFormStatusOption;

    //Option List; Form type
    @FindBy(xpath="//div[@class = 'col-xs-4 cat_right_border']/select/option")
    public List<WebElement> listOfFormTypeOption;
    
    //Button: OK button in dialog box
    @FindBy(xpath="//button[@class = 'btn btn-success']")
    public WebElement btnOfOK;
    
    //BUtton: Cancel
    @FindBy(xpath="//button[@class = 'btn btn-danger']")
    public WebElement btnOfCancel;
    
    //Text: Confirmation of archiving a form
    @FindBy(xpath="//span[contains(text(),'Are you sure')]")
    public WebElement textOfconfimationofArchiveOrActivateForm;
    
    //Text: warning text: NO Data
    @FindBy(xpath="//span[@class = 'text-center']")
    public WebElement textOfNoData;
    
    //Text: Message when uploading an incorrect file.
    @FindBy(xpath="(//h3//span)[2]")
    public WebElement textOfMessageForuploadOrDeletedraftForm;
    
    //List: sorted column
    @FindBy(xpath="//th[@sort-by]")
    public List<WebElement> listOfSortedColumn;
    
    //List: List Of Draft form Name.
    @FindBy(xpath="(//table[@id= 'table_width']//tr[contains(@class , 'formActionHover')])//td[1]")
    public List<WebElement> listOfDraftFormName;
    
    //List: List Of Draft form Type.
    @FindBy(xpath="(//table[@id= 'table_width']//tr[contains(@class , 'formActionHover')])//td[2]")
    public List<WebElement> listOfDraftFormType;
    
    //List: List Of Draft form Family.
    @FindBy(xpath="(//table[@id= 'table_width']//tr[contains(@class , 'formActionHover')])//td[3]")
    public List<WebElement> listOfDraftFormFamily;
    
    //List: List Of Draft Serial number prefix.
    @FindBy(xpath="(//table[@id= 'table_width']//tr[contains(@class , 'formActionHover')])//td[4]")
    public List<WebElement> listOfDraftFormSerialNumberPre;
    
    //List: List Of Draft form Type.
    @FindBy(xpath="(//table[@id= 'table_width']//tr[contains(@class , 'formActionHover')])//td[5]")
    public List<WebElement> listOfDraftFormCreatedTime;
    
    //List: Download icon
    @FindBy(xpath="//i[@title = 'Download']")
    public List<WebElement> listOfDownloadIcon;
    
    //List: Delete icon
    @FindBy(xpath="//i[@title = 'Delete']")
    public List<WebElement> listOfDeleteIcon;

    /**
     * Get the index of the first form by status 
     * @author herby.he
     * @param status(you can enter archived or active value)
     * @return
     * @throws Exception
     */
    public int getIndexOfTheFirstFormByStatus(String status) throws Exception{
        for(int i =0;i <listOfSliderColor.size();i++){
            if(listOfSliderColor.get(i).getAttribute("class").contains(status)){
                return i;
            }
        }    
        return -1;
    }
    
    /**
     * Only for Manager Form page, select a option from the drop-down list.
     * @author herby.he
     * @param selector
     * @param optionList
     * @param selectedIndex
     * @throws Exception
     */
    public void selectAOptionFromdropdownlist(WebElement selector, List<WebElement> optionList, int selectedIndex) throws Exception {
    	click(selector,"click on selector");
    	click(optionList.get(selectedIndex),"Select the expected option from the drop-down list");
	
	}
    
    
    /**
     * verify the number list is ordered correctly.
     * @author herby.he
     * @param webElement
     * @param index (starting from this value)
     * @param listName
     * @param xec (true means ascending order, false means descending order)
     * @throws Exception
     */
    
    public void  verifyNumberOrderCorrectly(List<WebElement> webElement, int index, String listName, final Boolean xec) throws Exception {
    	List<Integer> origalList= new ArrayList<Integer>();
		 
		for(int i = index; i<webElement.size();i++) {
			int value = Integer.parseInt(webElement.get(i).getText().trim()); //Transfer string to integer type.
			origalList.add(value);	
		}
		
		for(int j= 0;j<(origalList.size()-1);j++) {
			
			if(xec) {
				if(origalList.get(j)>origalList.get(j+1)) {
					customAssertion.assertTrue(false,"The ascending  order for "+listName+" is incorrect");
					break;
				}
			}else {
				if(origalList.get(j)<origalList.get(j+1)) {
					customAssertion.assertTrue(false,"The descending order for "+listName+" is incorrect");
					break;
				}
			}
			
		}

	}
    
    
    /**
     * Verify the order of date is correct.
     * @author herby.he
     * @param webElement
     * @param index (Starting from this value)
     * @param listName
     * @param xec (true means ascending order, false means descending order.)
     */
    
    public void verifyDateOrdercorrectly(List<WebElement> webElement, int index, String listName, final Boolean xec ) {
    	List<Date> origalList= new ArrayList<Date>();
    	
    	for(int i = 0; i<webElement.size();i++) {
    		Date date = new Date();
    		
    		//Filter out empty component.
    		if(webElement.get(i).getText().trim().length()>4) {
    			date = tryAllConvertStringToDate(webElement.get(i).getText().trim()); 
    			origalList.add(date);
    			
    		}
    	  
    	}
    	
    	for(int j =0;j<origalList.size()-1;j++) {
    		int result = origalList.get(j).compareTo(origalList.get(j+1));
    		if(xec) {
    			if(result==1) {
    				customAssertion.assertTrue(false,"The order for "+listName+" is incorrect." + j);
    				break;
    			}

    		}else {
    			if(result==-1) {
    				customAssertion.assertTrue(false,"The order for "+listName+" is incorrect." + j);
    				break;
    			}
				
			}
    		
    		
    	}
    	
	}
    
    
    /**
     * Hover over the cursor on the last column at the specific row, 
     * then click on an action icon as specified, i.e. open, download or delete.
     * @author matthew.feng
     * @param rowNum   the row number in the last column, starting from 1.
     * @param iconNum   the icon number in the selected row, starting from 1.
     * @throws Exception
     */
    public void clickOnFormActionIconFromTheLastColumn(int rowNum, int iconNum) throws Exception{
    	click(listOfFormActions.get(rowNum-1));
    	WebElement rowItem = listOfFormActions.get(rowNum-1);
    	List<WebElement> iconList = rowItem.findElements(By.tagName("i"));
    	click(iconList.get(iconNum-1), "Click on the "+iconList.get(iconNum-1).getAttribute("title")+" icon.");	
    }


}
