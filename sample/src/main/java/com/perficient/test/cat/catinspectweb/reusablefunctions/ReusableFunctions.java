package com.perficient.test.cat.catinspectweb.reusablefunctions;

import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.DOWNLOADDIR;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.DevURL;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.ProdURL;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.QAURL;
import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.setClipboardData;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.perficient.test.cat.catinspectweb.basepages.BasicPage;
import com.perficient.test.cat.catinspectweb.basepages.CatLoginPage;
import com.perficient.test.cat.catinspectweb.util.TestCaseBase;


public class ReusableFunctions extends TestCaseBase{
	CatLoginPage catLoginPage;
	BasicPage basicPage;
	
	
	public void setupCatInspect() throws Exception{
		catLoginPage = new CatLoginPage();
		String targetURLString = getCurrentEnvURL();
		driver.navigate().to(targetURLString);
		catLoginPage.login();
	}
	
	
	public String getCurrentEnvURL(){
		String targetURL = "";
		if (envFlag.equals("QA")){
			targetURL = QAURL;
		}
		else if (envFlag.equals("DEV")){
			targetURL = DevURL;
		}
		else if (envFlag.equals("PROD")){
			targetURL = ProdURL;
		}
		else{
			customAssertion.assertFalseAndExit(targetURL.isEmpty(), "The Environment flag is NOT correct. Test Abort.");
		}
		return targetURL;
	}
	
	   /**
     * Click on the Drop down and select an specified options.
     * @author matthew.feng
     * @param selector
     * @param toSelect
     * @throws Exception
     */
    public void selectFromSelector(WebElement selector, String toSelect) throws Exception {
        click(selector, "Click on a dropdown");
        Thread.sleep(2000);
        List<WebElement> optionList = selector.findElements(By.tagName("option"));
        String valueString = "";
        boolean isElementSelected = false;
        for (int i = 0; i < optionList.size(); i++) {
            valueString = optionList.get(i).getAttribute("value").trim();
            if (valueString.contains(toSelect)) {
                click(optionList.get(i));
                isElementSelected = true;
                break;
            }
        }
        customAssertion.assertTrue(isElementSelected, "The " + toSelect + " option does NOT exist in the dropdown.");
    }
    
    
    /**
     * Generates the million seconds of Today. 
     * Cut this million seconds string into the length as specified in the numLength parameter
     * then return this cutted string  
     * @author matthew.feng
     * @param numLength
     * @return
     */
    public String getDifferentNumbers(int numLength){
    	Calendar today = Calendar.getInstance();
    	String secondsToday = today.getTimeInMillis()+"";
    	int length = secondsToday.length();
    	String returnString = secondsToday.substring(length-numLength, length);
    	System.out.println(returnString);
    	return returnString;
    }
    
    
    /**
     * Get a list of category names in a form
     * @return
     */
    public List<String> getFormCategoryNames() throws Exception{
    	basicPage = new BasicPage();
    	List<String> returnList = new ArrayList<String>();
    	for (int i = 0; i < basicPage.listOfFormCategoryNames.size(); i++) {
    		returnList.add(basicPage.listOfFormCategoryNames.get(i).getText().trim());
		}
    	return returnList;
    }
    
    /**
     * Objective: Add parts through upload CSV function
     * @param filepath
     * @author matthew.feng
     * @throws Exception
     */
    public void uploadFile(String filepath) throws Exception{
        Thread.sleep(1000);

        //Setting clip board with file location
        setClipboardData(filepath);
        //native key strokes for CTRL, V and ENTER keys
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    
    /**
     * Returns the expected number of rows for a specific form
     * @param sheetName
     * @return
     */
    public int getExpectedRowNum(String sheetName) {
        if (sheetName.equalsIgnoreCase("Dealer Retailer Experience"))
            return 18;
        else
            return -1;
    }
    
    
	
	/**
	 * Obtain Header and sections scores from the PDF of Dealer Retails Experience Form
	 * @author herby.he
	 * @param pdfFileName
	 * @param excelFileName
	 * @param sheetname
	 * @return
	 * @throws Exception
	 */
  public List<String> obtainHeaderAndSectionScoreFromPDF(String pdfFileName) throws Exception {
		String downloadDir = DOWNLOADDIR;
		PDFParser parser;
		PDFTextStripper stripper;
		COSDocument cosDoc;
		Thread.sleep(20000);
		List<String> actualList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(downloadDir + File.separator
				+ pdfFileName);
		parser = new PDFParser(fis);
		parser.parse();
		cosDoc = parser.getDocument();
		stripper = new PDFTextStripper();
		String docText = stripper.getText(new PDDocument(cosDoc));

		//separate Text via line by line into an array.
		String[] rowValue = docText.split(System.getProperty("line.separator"));
		//Close the PDF file.
		cosDoc.close();
		
		for(int a = 0; a<rowValue.length; a++) {
			if(rowValue[a].contains("Maximum") && rowValue[a].contains("Score")) {
				for(int b=a;b< a+5; b++) {
					String[] colValue = rowValue[b].split(" ");
					actualList.add(colValue[colValue.length-1]);
				}
			}
			
		}
		
		//verify if the % for initial rating is empty.
		if(actualList.get(2).contains("Rating:")) {
			actualList.set(2, "");
		}
		
		for(int i = 0; i<rowValue.length; i++) {
			if(rowValue[i].contains("Section") && rowValue[i].contains("Percentage")) {
				for(int j=i;j<rowValue.length;j++) {
					String[] colValue = rowValue[j].split(" ");
					for(int k=0;k<colValue.length;k++) {
						if(colValue[k].contains("%")) {	
							actualList.add(colValue[k-1]);
						}
					}	
				}
			}
			
		}
		
		//output those value to verify if it is right.
//		for(int n =0; n<actualList.size();n++) {
//			System.out.println("collll "+actualList.get(n));
//		}

		return actualList;
	
  }
  
  /**
   * Search a keyword in list1, find the position of this keyword
   * then click on the element whose position is same to the found position.
   * @author matthew.feng
   * @param keyword
   * @param list1
   * @param list2
   * @throws Exception
   */
  public void searchKeywordInAListAndClickAnother(String keyword, List<WebElement> list1, List<WebElement> list2) throws Exception{
	  int pos = -1;
	  for (int i = 0; i < list1.size(); i++) {
		if (list1.get(i).getText().contains(keyword)) 
			pos = i;
		break;
	  }
	  if (pos==-1) 
		  customAssertion.assertTrue(false, "The keyword "+keyword+" does NOT exist in the target list");
	  else{
		  for (int i = 0; i < list2.size(); i++) {
			  click(list2.get(pos),"Click on the "+i+"th element in another list.");
		}
	  }
  }
    
  
  public List<String> getAttibutesFromList(List<WebElement> list, String attributeName) throws Exception{
	  List<String> stringList = new ArrayList<String>();
	  
	  if (attributeName.equals("text")){
		  for (int i = 0; i < list.size(); i++) {
			stringList.add(list.get(i).getText());
		}
	  }
	  else{
		  for (int i = 0; i < list.size(); i++) {
			  stringList.add(list.get(i).getAttribute(attributeName));
			}
	  }
	  return stringList; 
  }
    
    


    
    
    
}
