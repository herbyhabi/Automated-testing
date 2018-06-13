package com.perficient.test.cat.catinspectweb.basepages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.test.cat.catinspectweb.util.FunctionUtil;


public class CreateFormPage extends BasicPage {
	
	/**
	*General icon
	*
	*/
	//List: Next icon 
    @FindBy(xpath="//a[@title='Next']")
    public List<WebElement> listOfNextIcon;
    
    //Icon: Reset icon
    @FindBy(xpath="//a[@title='Reset']")
    public List<WebElement> listOfReset;
  
    //Icon: Save as draft icon
    @FindBy(xpath="//a[@title='Save as Draft']")
    public List<WebElement> listOfSaveAsDraft;
    
    //Toast: Pops up a toast message when click the save button.
    @FindBy(xpath="//div[contains(@class , 'toast-success toast')]/div")
    public WebElement toastOfSaveForm;
    
    //Text: Pops up a toast message After clicking on Save as Draft icon
    @FindBy(xpath="//div[@class='toast-message']")
    public WebElement textOfSaveAsDratToastMessage;
    
    //Text: Pops up a Form Reset dialog when click the 'X' reset button.
    @FindBy(xpath="//div[@class='modal-body']//span")
    public WebElement textOfFormResetDialog;
  
    //Button: Pops up a Form Reset dialog when click the 'X' reset button.
    @FindBy(xpath="//span[@class='glyphicon glyphicon-remove']")
    public WebElement buttonOfCancelReset;
    
    //Button: OK button in dialog box
    @FindBy(xpath="//button[@class = 'btn btn-success']")
    public WebElement btnOfOK;
  
    //Button: Pops up a Form Reset dialog when click the 'X' reset button.
    @FindBy(xpath="//div[@class='modal-footer']//span[@class='glyphicon glyphicon-ok']")
    public WebElement buttonOfConfirmlReset;
    
    //text: The dialog box title.
    @FindBy(xpath="//h3")
    public WebElement textOfDialogBox;
    
    //Text: Delete confirmation message for deleting category, question, option set and sub question.
    @FindBy(xpath="//div[@class = 'modal-body']//span")
    public WebElement textOfDelete;
    
    
    /**
     * Form Type
     *
     *
     */
    //Input: Form Name
    @FindBy(xpath="//input[@id='formName']")
    public WebElement inputOfFormName;
    
    //Input: Form Name is not filled
    @FindBy(xpath="//input[@class='form-control ng-untouched ng-pristine ng-invalid']")
    public WebElement inputOfFormNameEmpty;

    //Selector: Form Type
    @FindBy(xpath="//Select[@id='formType']")
    public WebElement selectorOfFormType;
    
    //Selector: Form Type is in default status
    @FindBy(xpath="//select[@class='form-control ng-untouched ng-pristine ng-invalid']")
    public WebElement selectorOfFormTypeEmpty;

    //Selector: Form Family
    @FindBy(xpath="//select[@id='formFamily']")
    public WebElement selectorOfFormFamily;

    //Input: Serial number
    @FindBy(xpath="//input[@id='serialPrefix']")
    public WebElement inputOfSerialNumber;
    
    //Button: cancel button on the confirmation dialog
    @FindBy(xpath="//button[@class ='btn btn-danger' ]")
    public WebElement btnOfCancel;
    
    

    //Icon: Search icon for searching a form according to type and family.
    @FindBy(xpath="//button[@class = 'btn btn-sm btn-warning pull-right']")
    public WebElement iconOfSearch;

    //List: List Of Form title after search form.
    @FindBy(xpath="//b[@class = 'text-info']")
    public List<WebElement> listOfFormTitle;

    //List: List of category ID shown in Form Information dialog box.
    @FindBy(xpath="//label[@class = 'cat_cat_Id']")
    public List<WebElement> listOfCategoryId;

    //List: List of question shown in Form Information when click on category id.
    @FindBy(xpath="//div[@class = 'queBorder']")
    public List<WebElement> listOfQuestion;


    //List: List of Form from the search results
    @FindBy(xpath="//b[@class = 'text-info']")
    public List<WebElement> listOfForm;

    //List: List radio button of form
    @FindBy(xpath="//input[@id = 'cloneForm']")
    public List<WebElement> listOfRadioButton;

    //Button: Confirm button for Cloning a form
    @FindBy(xpath="//button[@class = 'btn btn-success']")
    public WebElement buttonOfConfirmCloningForm;


    //Icon: Next icon in Form Type step
    @FindBy(xpath="//span[contains(@class,'basic-icons right-arrow pull-right')]")
    public WebElement iconOfNextIcon;
    
    //Text: Form cloning confirmation content
    @FindBy(xpath="//div[@class='modal-body']//span")
    public WebElement textOfCloningConfirm;
    
    //Option: Form Type Options
    @FindBy(xpath="//select[@id = 'formType']/option")
    public List<WebElement> listOfFormTypeOption;
    
    //Option: Form Family Option
    @FindBy(xpath="//select[@id = 'formFamily']/option")
    public List<WebElement> listOfFamilyOption;
  
    
    /**
     * Categories
     *
     */
    //Input: Input Category name
    @FindBy(xpath="//input[@name='addCat']")
    public WebElement inputOfCategoryName;

    //Button: Plus button in categories step
    @FindBy(xpath="//span[@class = 'input-group-btn']")
    public WebElement buttonOfPlusButton;

    //Icon: Next icon in Categories step
    @FindBy(xpath="//span[contains(@class,'basic-icons right-arrow pull-right')]")
    public WebElement iconOfNextIcon2;
    
    @FindBy(xpath="(//div[@class='panel-heading']//a[@class='pull-left'])[1]")
    public WebElement iconOfBack;
    
    //Icon: Delete icon.
    @FindBy(xpath="//table//span[contains(@class, 'remove')]")
    public WebElement iconOfDeleteCategory;
    
    
    /**
     * Questions
     *
     */

    //Item: Item of question
    @FindBy(xpath="//div[@class = 'cat_que_para_block']")
    public WebElement itemOfQuestion;

    //Icon: edit icon to update a question.
    @FindBy(xpath="//span[@class = 'glyphicon glyphicon-edit glyphicon-yellow gly-right']")
    public WebElement iconOfEditForUpdateQuestion;

    //Button: Plus button to add a question in Questions step
    @FindBy(xpath="//div[@class = 'cat_add_block']")
    public WebElement buttonOfPlusButton2;

    //Text area: Test area for enter a question name
    @FindBy(xpath="//textarea[@name= 'ques']")
    public WebElement textAreaOfQuestionName;
    
    //Text area: Test area for enter a sub question name
    @FindBy(xpath="//input[@name='subQues']")
    public WebElement textAreaOfAddSubQues;
    
    //Button: Add sub question button
    @FindBy(xpath="//div[@class='input-group']//button[@class='btn btn-default']/span[@class='glyphicon glyphicon-plus']")
    public WebElement buttonOfAddSubQues;
    
    //Button: Edit sub question button
    @FindBy(xpath="//tbody[@dragula='sub-questions']//span[@class='glyphicon glyphicon-edit glyphicon-yellow']")
    public WebElement buttonOfEditSubQues;
    
    //Text area: text area for Edit sub question
    @FindBy(xpath="//input[@class='edit_input ng-untouched ng-pristine ng-valid']")
    public WebElement textAreaOfEditSubQues;
    
    //Button: Check edit sub question button
    @FindBy(xpath="//tbody[@dragula='sub-questions']//span[@class='glyphicon glyphicon-check glyphicon-green']")
    public WebElement buttonOfCheckSubQues;
    
    //Text area: text area for existing sub question title
    @FindBy(xpath="//span[@class='cat_bold']")
    public  WebElement textAreaOfSubQues;
    
    //Dialog header info: Missing Information header
    @FindBy(xpath="//div[@class='modal-header']//h3/span")
    public List<WebElement> headerOfMissingInfoDialog;
    
    //Dialog content info: Missing Information content
    @FindBy(xpath="//div[@class='modal-body']//span")
    public WebElement contentOfMissingInfoDialog;
    
    //Button: Missing Information confirm button
    @FindBy(xpath="//div[@class='modal-footer']//span[@class='glyphicon glyphicon-ok']")
    public WebElement buttonOfConfirmMissingInfo;
    
    //Input: Input Unit of Measurement for sub question
    @FindBy(xpath="//input[@name='units']")
    public WebElement inputOfMeasurement;
    
    //Button: button of add measurement
    @FindBy(xpath="//button[@id='unitsButton']")
    public WebElement buttonOfAddMeasurement;
    
    //Text: Sub question title under Categories
    @FindBy(xpath="//div[@class='cat_sques_para']")
    public WebElement textOfSubQuesTitle;

    //Title: Question title in Question step
    @FindBy(xpath="//h4")
    public WebElement titleOfQuestion;

    //Text area: Test area for enter a question name
    @FindBy(xpath="//input[@id = 'oSearch']")
    public WebElement inputOfOptionSet;

    //List: List of option for options Set
    @FindBy(xpath="//div[@class = 'item' and contains(text(),'1-5 Satisfaction')]")
    public WebElement listOfOptions;

    //Icon: Save icon for save question
    @FindBy(xpath="//i[@class  = 'fa fa-floppy-o fa-2x']")
    public WebElement iconOfSaveQuestion;

    //Text: Text is Option that user selected.
    @FindBy(xpath="//div[@class = 'sForm']//span[1]")
    public WebElement textOfSelectedOption;
    
    //list: the containers of filtered options set
    @FindBy(xpath="//div[@_ngcontent-c13 and @class='item']")
    public List<WebElement> listOfOptionSet;
    
    //list: the containers of filtered options set
    @FindBy(xpath="(//label[@_ngcontent-c13 and @class='cat-ck']/following-sibling::div[1])[1]/div/div[@class='item']")
    public List<WebElement> listOfFilteredOptionSet;


    //Button: strengthHide button for help(Optional)
    @FindBy(xpath="//span[@class = 'glyphicon glyphicon-chevron-right']")
    public WebElement buttonOfHelpOptional;

    //text: Select one of the following to add to the help icon
    @FindBy(xpath="//div[@class = 'lt-color']")
    public WebElement textOfSelectOptionToAddHelpFile;

    //Radio button: Text and/or image button
    @FindBy(xpath="//input[@value = 'text_and_image']")
    public WebElement buttonOfTextAndOrImage;

    //radio button: PDF document button
    @FindBy(xpath="//input[@value = 'doc']")
    public WebElement buttonOfPDFDocument;

    //Button: Upload button for upload image file or PDF file.
    @FindBy(xpath="//a[@class ='cat_button']")
    public WebElement btnOfUpload;
    
  //Button: Choose File for uploading a PDF
    @FindBy(xpath="//input[@name = 'helpText']")
    public WebElement buttonOfChoosePDFFile;
    
    //Button: OK button on dialog box.
    @FindBy(xpath="//button[@class = 'btn btn-success']")
    public WebElement buttonOfConfirm;
    
    //Button: Download button for help file.
    @FindBy(xpath="//span[contains(@class, 'glyphicon-download-alt')]")
    public WebElement buttonOfDownLoadHelpFile;
    
    //image: A picture for preview.
    @FindBy(xpath="//img[@alt = 'Image Loading...']")
    public WebElement imgOfPreviewPicture;
    
    //Icon: Media changed icon , e.g. Media uploaded or Media removed.
    @FindBy(xpath="//div[@class = 'text-center']")
    public WebElement iconOfMediaDialog;

    //Image: PDF icon
    @FindBy(xpath="//img[@alt = 'Image Loading...']")
    public WebElement imgOfPDF;
    
    //Icon: delete icon for option set
    @FindBy(xpath="//p//span[contains(@class, 'remove')]")
    public WebElement iconOfDeleteOptionSet;
    
    //Icon: delete icon for sub question
    @FindBy(xpath="//tbody[@dragula = 'sub-questions']//span[contains(@class, 'remove')]")
    public WebElement iconOfDeleteSubQues;
    
    //Icon: delete icon for question.
    @FindBy(xpath="//div[@class= 'cat_ques_para']//span[contains(@class, 'remove')]")
    public List<WebElement> ListOfDeleteQuestionIcon;

    /**
     * Preview and Submit
     */
    
    //Icon: Submit icon in Preview and Submit
    @FindBy(xpath="//div[@class='pull-right cat_process form-creation-heading']//a[@title='Publish']")
    public WebElement iconOfSubmit;
    
    //Text: The confirmation of publishing a form.
    @FindBy(xpath="//div[@class='modal-body']//span")
    public WebElement textOfConfirmationOfPublishForm;
    
    

    /**
     * Find a form after searching by family and type in the second step of create form.
     * @author matthew.feng
     * @param formName
     * @return
     * @throws Exception
     */
    public int selectFormByText(String formName) throws Exception {
        for(int i = 0; i < listOfForm.size(); i++) {
            if(listOfForm.get(i).getText().trim().contains(formName)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Verify all categories are shown in Form Information
     * @author Herby He
     * @throws Exception
     */

    public void verifyCategoryShown() throws Exception{

        for( int i =0; i<listOfCategoryId.size();i ++){
        FunctionUtil.isElementExist(listOfCategoryId.get(i));
        }
    }
    
    /**
     * To get the number of option sets that contains the keyword
     * @author matthew.feng
     * @param listOfOptionSets
     * @param keyword
     * @return
     * @throws Exception
     */
    public int getNumOfOptionSetThatContainsKeyword(List<WebElement> listOfOptionSets, String keyword) throws Exception{
    	int numOfFitOptionSets = 0;
    	List<WebElement> subOptionSets;
    	for (int i = 0; i < listOfOptionSets.size(); i++) {
			if (listOfOptionSets.get(i).getText().contains(keyword)){
				numOfFitOptionSets++;
			}
			else {
				subOptionSets = listOfOptionSets.get(i).findElements(By.tagName("span"));
				for (int j = 0; j < subOptionSets.size(); j++) {
					if (subOptionSets.get(j).getText().contains(keyword)){
						numOfFitOptionSets++;
						break;
					}
				}
			}
		}
    	return numOfFitOptionSets; 
    }

}
