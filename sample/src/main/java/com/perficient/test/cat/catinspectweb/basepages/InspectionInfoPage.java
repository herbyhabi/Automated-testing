package com.perficient.test.cat.catinspectweb.basepages;

import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.DOWNLOADDIR;
import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.FileUtil.emptyDownloadFolder;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.waitForElementDisappear;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class InspectionInfoPage extends BasicPage{
	/**
	 * 
	 * Inspection Details part
	 * 
	 * 
	 */
    	//Link: Inspection Details link
    	@FindBy(xpath="//li[@class= 'toolbar-item']/a[@class ='btn-border' ]")
    	public WebElement linkOfInspectionDetails;
    	
		//List: the list of Inspection Information
	    @FindBy(xpath="//div[@id='print_data']//div[contains(@class,'md-6') and @style]")
	    public List<WebElement> listOfInspectionInfo;
	    
	    //Button: Download PDF button
	    @FindBy(xpath="//a[@title='Download PDF']")
	    public WebElement buttonOfDownloadPDF;
	    
	    // Button: Remove Download PDF screen button
	    @FindBy(xpath="//span[@class='glyphicon glyphicon-remove']")
	    public WebElement buttonOfRemove;

	    
	    
	/**
	 *
	 * Edit Inspection part
	 *
	 *
	 */
	    //List: List of section.
	    @FindBy(xpath="//div[contains(@class, 'cat_div_box')]")
	    public List<WebElement> listOfSection;
	    
	    //Link: Edit Inspection link
	    @FindBy(xpath="//li[@class='toolbar-item']//a[contains(@href,'edit')]")
	    public WebElement linkOfEditInspection;
	        
	    //Text: Form name in the top of header information
	    @FindBy(xpath="//div[@class = 'col-xs-4'][1]")
	    public WebElement textOfFormName;
	    
	    //text:customer name in the top of header information.
	    @FindBy(xpath="//div[@class = 'col-xs-4'][2]")
	    public WebElement textOfcustomerName;
	    
	    //Text: text of date in the top of header information
	    @FindBy(xpath="//div[@class = 'col-xs-4'][3]")
	    public WebElement textOfDate;
	    
	    //Check box: Show Images check box.
	    @FindBy(xpath="//label[@class ='img_label']/input[@type= 'checkbox']")
	    public WebElement checkBoxOfShowImages;
	    
	    //Text:The text of header information
	    @FindBy(xpath="//label[@for='header_info']//b")
	    public WebElement textOfHeaderInformation;
	    
	    
	    //Input: Serial Number
	    @FindBy(xpath="//input[@name='SerialNumber']")
	    public WebElement inputOfSerialNumber;
	    
	    //Input: Equipment No
	    @FindBy(xpath="//input[@name='EquipmentNumber']")
	    public WebElement inputOfEquipmentNo;
	    
	    //Input: SMU
	    @FindBy(xpath="//input[@name='SMU']")
	    public WebElement inputOfSMU;
	    
	    //Select: SMR
	    @FindBy(xpath="//select[@name='SMUType']")
	    public WebElement selectorOfSMR;
	    
	    //Input: Location Description
	    @FindBy(xpath="//input[@name='LocationDescription']")
	    public WebElement inputOfLocationDescription;
	    
	    //Input: Customer Name
	    @FindBy(xpath="//input[@name='CustomerName']")
	    public WebElement inputOfCustomerName;
	    
	    //Input: Customer Phone
	    @FindBy(xpath="//input[@name='CustomerPhone']")
	    public WebElement inputOfCustomerPhone;
	    
	    //Input: Customer Email
	    @FindBy(xpath="//input[@name='CustomerEmail']")
	    public WebElement inputOfCustomerEmail;
	    
	    //button: update button in Edit Inspection
	    @FindBy(xpath="//button[@type='submit']")
	    public WebElement buttonOfUpdateButton;
	    
	    //Title: update confirmation dialog title
	    @FindBy(xpath="//div[@class='modal-header']//h3")
	    public WebElement titleOfUpdateConfirmation;
	    
	    //Text: update confirmation dialog content
	    @FindBy(xpath="//div[@class='modal-body']//span")
	    public WebElement contentOfUpdateConfirmation;
	    
	    //text: Inspection updated Successfully toast
	    @FindBy(xpath="//div[@class ='toast-message']")
	    public WebElement textOfUpdatedSuccessful;
	    
	    //Button: OK icon in pop up box
	    @FindBy(xpath="//div[@class='modal-content']//span[@class='glyphicon glyphicon-ok']")
	    public WebElement buttonOfOK;
	    
	    //icon: page loading icon
	    @FindBy(xpath="//div[@class='loader_outer_div']")
	    public WebElement iconOfPageLoading;
	    
	    //List: The all buttons in the first question。(Add Comment/Add Picture/View Picture)
	    @FindBy(xpath="(//div[@class='ques_response'])[1]/following-sibling::div//a[@class='cat_button']")
	    public List<WebElement> listOfAddPicture;
	    
	    //List: The all buttons in the General Comments section。(Add Comment/Add Picture/View Picture)
	    @FindBy(xpath="(//div[@class='box-data'])[2]//a")
	    public List<WebElement> listOfAddPictureInGeneralComments;
	      
	    //Title: Delete Image dialog title
	    @FindBy(xpath="//div[@class='modal-header']//h3")
	    public WebElement titleOfDeleteImage;
	    
	    //Message: Delete Image message
	    @FindBy(xpath="//span[contains(text(),'delete this image')]")
	    public WebElement messageOfDeleteImage;

	    //Text: Delete Image check box(do not ask again)
	    @FindBy(xpath="//div[@class='checkbox lft-align']/label")
	    public WebElement checkboxOfDeleteImage;
	    
	    //Button: cross mark button
	    @FindBy(xpath="//button[@class='btn btn-danger']")
	    public WebElement buttonOfCrossMark;	    
	    
	    //list: Right arrow list. It exists after clicking on Edit Inspection button.
	  	@FindBy(xpath="//span[@class='glyphicon glyphicon-chevron-right']")
	  	public List<WebElement> listOfRightArrow;
	    
	    //list: Form Category Name list. It exists after clicking on Edit Inspection button.
		@FindBy(xpath="//label[contains(@for,'id')]//b")
		public List<WebElement> listOfFormCategoryNames;
		
		//Button: confirm button (delete and upload)
	    @FindBy(xpath="//div[@class='modal-footer']//span[@class='glyphicon glyphicon-ok']")
	    public WebElement buttonOfConfirm;
	    
	    //Icon: Media uploaded icon 
	    @FindBy(xpath="//div[@class='popup-dialog']")
	    public WebElement iconOfMediadialog;
	    
	    //Thumb nail: uploaded picture thumb nail
	    @FindBy(xpath="//a[@class='thumbnail thumb-img inline-img dynSt']")
	    public WebElement thumbnailOfPreviewPicture;
	    
	    //Button: Check mark button for delete image  
	    @FindBy(xpath="//div[@class='modal-footer']//span[@class='glyphicon glyphicon-ok']")
	    public WebElement buttonOfCheckMark;
	    
	    //Text: Delete Image message
	    @FindBy(xpath="//div[@class = 'modal-body']//span")
	    public WebElement textOfConfirmationUpdateInspection;

	    //Text: Delete Image check box
	    @FindBy(xpath="//div[@class='checkbox lft-align']")
	    public WebElement checkboxOfUpdateConfirmation;
	    
	    //List: The list of pictures in the question
	    @FindBy(xpath="//img[@src and not(@class)]")
	    public List<WebElement> listOfQuestionPics;
	  
	    //List: X icon list for pictures
	    @FindBy(xpath="//img[@src and not(@class)]/../following-sibling::span")
	    public List<WebElement> listOfXIconForPictures;

	    //Link: Link of View all Inspections
	    @FindBy(xpath="//a[contains(text(),'View all Inspections')]")
	    public WebElement linkOfViewAllInspections;
	    
	    //Button: Remove comment button
	    @FindBy(xpath="(//span[@class = 'ques_box'])[1]//span[@class ='glyphicon glyphicon-remove glyphicon-red']")
	    public WebElement buttonOfRemoveComments;
	    
	    //Button: Confirm button for removing comments
	    @FindBy(xpath="(//button[@class= 'btn btn-success'])[1]")
	    public WebElement buttonofConfirmRemoveComments;
	    
	    //Button: Cancel button in dialog box
	    @FindBy(xpath="//button[@class = 'btn btn-danger']")
	    public WebElement buttonOfCancel;
	    
	    //Text: Text of confirmation for removing comments
	    @FindBy(xpath="//span[contains(text(),'delete')]")
	    public WebElement textOfconfirmationRemoveComments;
	    
	   
	    //Button: Button of add comments
	    @FindBy(xpath="((//span[@class = 'ques_box'])[1]//a[@class = 'cat_button'])[1]")
	    public WebElement buttonOfAddComments;
	    
	    //Input: input of comments
	    @FindBy(xpath="(//span[@class = 'ques_box'])[1]//input")
	    public WebElement inputOfComments;
	    
	    //icon: Plus icon for add comments
	    @FindBy(xpath="(//span[@class = 'ques_box'])[1]//span[@class = 'glyphicon glyphicon-plus glyphicon-green']")
	    public WebElement iconOfPlusForAddComments;
	    
	    //icon: Edit icon for modifying comments
	    @FindBy(xpath="(//span[@class = 'ques_box'])[1]//span[@class = 'glyphicon glyphicon-edit glyphicon-yellow']")
	    public WebElement iconOfEditComments;
	    
	    //icon: check icon for editing comments
	    @FindBy(xpath="(//span[@class = 'ques_box'])[1]//span[@class = 'glyphicon glyphicon-check glyphicon-green']")
	    public WebElement iconOfcheckModifyComments;
	    
	    //Text: Text of comments
	    @FindBy(xpath="((//span[@class = 'ques_box'])[1]//table//span)[1]")
	    public WebElement textOfComments;
	    
	    //Text: Error message when upload a non-image file.
	    @FindBy(xpath="(//h3/span)[2]")
	    public WebElement textOfErrorMessageForNonImage;
	    
	    
	    
	    
	   /**
	    * 
	    * Element in General Comments 
	    * 
	    * 
	    */
	    
	    //Input: input for general comments
	    @FindBy(xpath="//input[@name ='generalComment']")
	    public WebElement inputOfCommentsInGeneralComments;
	    
	  
	    //Icon: Plus button for add general comments
	    @FindBy(xpath="//td/span[contains(@class,'plus')]")
	    public WebElement iconOfPlusForAddGeneralComments;
	    
	    //Text: Text of general comments
	    @FindBy(xpath="(//div[@class = 'box-data'])[2]//table//span")
	    public WebElement textOfGeneralComments;
	    
	    
	    //icon: Edit icon for modifying General comments
	    @FindBy(xpath="(//div[@class ='box-data'])[2]//td/span[contains(@class,'yellow')]")
	    public WebElement iconOfEditgeneralComments;
	    
	    //icon: check icon for editing General comments
	    @FindBy(xpath="//td/span[contains(@class,'green')]")
	    public WebElement iconOfcheckModifyGeneralComments;
	    
	    //Button: Remove General comment button
	    @FindBy(xpath="(//div[@class ='box-data'])[2]//td/span[contains(@class,'red')]")
	    public WebElement btnOfRemoveGeneralComments;
	    

	    /**
	     * verify a label's value in PDF file.
	     * @author herby.he
	     * @param fileName
	     * @param label
	     * @param expected
	     * @throws Exception
	     */
	    public void verifyDownloadPDFInfo(String fileName, String label,String expected) throws Exception{
			String downloadDir = DOWNLOADDIR;
			PDFParser parser;
			PDFTextStripper stripper;
			COSDocument cosDoc;
			Thread.sleep(20000);
			FileInputStream fis = new FileInputStream(downloadDir + File.separator
					+ fileName);
			parser = new PDFParser(fis);
			parser.parse();
			cosDoc = parser.getDocument();
			stripper = new PDFTextStripper();
			String docText = stripper.getText(new PDDocument(cosDoc));

			//separate Text via line by line into an array.
			String[] rowValue = docText.split(System.getProperty("line.separator"));
			cosDoc.close();
			for(int i=0;i<rowValue.length;i++){
				if(rowValue[i].contains(label)) {
					System.out.println(rowValue[i].toUpperCase());
					System.out.println(expected.toUpperCase());
					customAssertion.assertTrue(rowValue[i].toUpperCase().contains(expected.toUpperCase()), "Label'value not matched with the expected value.");
					break;	
				}
			}
			emptyDownloadFolder();		
		}
	    
	    /**
	     * To retrieve the source value of the web elements in a list. 
	     * @return
	     * @throws Exception
	     */
	    public List<String> getPictureSource() throws Exception{
	    	List<String> returnList = new ArrayList<String>();
	    	for (int i = 0; i < listOfQuestionPics.size(); i++) {
				returnList.add(listOfQuestionPics.get(i).getAttribute("src").trim());
			}
	    	return returnList;
	    }
	    
	    /**
	     * To verify the first picture of a question is deleted from the screen.
	     * @param firstList  the src value of all the Web Elements before the deletion
	     * @param secondList the src value of all the Web Elements after the deletion
	     * @throws Exception
	     */
	    public void verifyTheFirstPicIsDeleted(List<String> firstList, List<String> secondList) throws Exception{
	    	String deletedPicSrc = firstList.get(0);
	    	for (int i = 0; i < secondList.size(); i++) {
				if (secondList.get(i).equals(deletedPicSrc)){
					customAssertion.assertTrue(false, "The first picture is NOT deleted");
					break;
				}
			}
	    }
	    
	    /**
	     * To click the Add Picture button in Edit Inspection tab
	     * @author christal.zhang
	     * @param list  the all buttons in a question, including Add Comment button, Add Picture button, View Picture button.
	     * @throws Exception 
	    */
	    public void clickAddPicture(List<WebElement> list) throws Exception {
	    	for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getText().contains("Add Picture")){
					click(list.get(i), "Click on the Add Picture button");
				}	
			}
	    	
//			if (isElementExist(buttonOfRemoveComments)) {
//				click(buttonOfRemoveComments,"Delete comment");
//				click((buttonOfCheckMark), "Click confirm button");
//				click(list.get(1), "Click Add Picture button");				
//			}
//			else {
//					click(list.get(1));				
//			}
		}
	    
	    
	    
	    
	    
	    /**
	     * Separate the list of text via split function.
	     * @author herby.he
	     * @param list
	     * @return
	     * @throws Exception
	     */
	    public List<String> separateListText( List<WebElement> webElement) throws Exception {
	    	List<String> list1 = new ArrayList<String>();
	    	for(int i=1;i<webElement.size();i++) {
	    		list1.add((webElement.get(i).getText().trim().split("\\u0028"))[0]);
	    	}
	    	return list1;
		}
	    
	    /**
	     * To delete all the existing pictures if they are visible in the expanded section
	     * @author matthew.feng
	     * @throws Exception
	     * @return true: if there are existing pictures and deleted
	     * 		   false: if there are no existing pictures.
	     */
	    public boolean deleteAllThePictures() throws Exception{
	    	int numOfButtons = listOfAddPictureInGeneralComments.size();
	    	if (numOfButtons>0 && listOfAddPictureInGeneralComments.get(numOfButtons-1).getText().contains("View Pictures")){
	    		click(listOfAddPictureInGeneralComments.get(numOfButtons-1), "Click on the View Pictures button in General Comments section.");
	    		waitForElementDisappear(iconOfPageLoading);
	    		Thread.sleep(1000);
	    		int numOfPics = listOfXIconForPictures.size();
	    		for (int i = 0; i < numOfPics; i++) {
					click(listOfXIconForPictures.get(0), "Click the X icon to delete a picture.");
					click(buttonOfConfirm, "Click on the OK button to confirm the delete");
					Thread.sleep(500);
				}
	    		return true;
	    	}
	    	else {
				return false;
			}
	    }
	    
	    
	    
	    
	    
	    
}
	    

