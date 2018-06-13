package com.perficient.test.cat.catinspectweb.testcase.advancedsearch;

import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.input;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.verifyDateOrdercorrectly;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.verifyOrderCorrectly;

import org.testng.annotations.Test;

import com.perficient.test.cat.catinspectweb.basepages.AdvancedSearchPage;
import com.perficient.test.cat.catinspectweb.basepages.HomePage;
import com.perficient.test.cat.catinspectweb.reusablefunctions.ReusableFunctions;
import com.perficient.test.cat.catinspectweb.util.TestCaseBase;
import com.relevantcodes.extentreports.LogStatus;

public class TC2932_CheckSortingInAdvancedSearchPage extends TestCaseBase {
	ReusableFunctions reusableFunctions;
	HomePage homePage;
	AdvancedSearchPage advancedSearchPage;
	
	@Test
	 public void Verify_TC2623_ViewHelpFileAfterAdded() throws Exception{
		homePage = new HomePage();
		reusableFunctions = new ReusableFunctions();
		advancedSearchPage = new AdvancedSearchPage();
		
		String sn="12345678";
		
		test.log(LogStatus.INFO,"//Step 1: Login Cat Inspect Web.");
	    reusableFunctions.setupCatInspect();
	    click(homePage.tabOfAdvancedSearch,"Click on Advanced Search tab");
	    Thread.sleep(4000);
	    
	    test.log(LogStatus.INFO,"//Step 2: Input a valid serial number in the Search input field. Click on the search icon.");
	    input(advancedSearchPage.inputOfSearch, sn);
		click(advancedSearchPage.btnOfSearch,"Click on Search button");
		Thread.sleep(3000);
		
		test.log(LogStatus.INFO,"//Step 3: Click on the header of every column.");
		
		//Before sorting all columns, need to show all columns.
		advancedSearchPage.ShowAllColums();
		
		//In order to close the display of columns option.
		click(advancedSearchPage.listOfHeaderColumns.get(1), "Click on serial number column");
		
		//Verify sorting by Complete Date
		click(advancedSearchPage.listOfHeaderColumns.get(7),"Click on Complete Date column");
		verifyDateOrdercorrectly(advancedSearchPage.listOfCompleteDate, 0, "The list of Complete Date", false);
		click(advancedSearchPage.listOfHeaderColumns.get(7),"Click on Complete Date column again");
		verifyDateOrdercorrectly(advancedSearchPage.listOfCompleteDate, 0, "The list of Complete Date", true);
		
		//Verify Sorting by Asset ID
		click(advancedSearchPage.listOfHeaderColumns.get(2), "Click on Asset ID column");
		verifyOrderCorrectly(advancedSearchPage.listOfAssetId, 0, "The list of Asset Id", false);
		click(advancedSearchPage.listOfHeaderColumns.get(2),"Click on Asset ID column again");
		verifyOrderCorrectly(advancedSearchPage.listOfAssetId, 0, "The list of Asset Id", true);
		
		//Verify sorting by Type
		click(advancedSearchPage.listOfHeaderColumns.get(3),"Click on Type column");
		verifyOrderCorrectly(advancedSearchPage.listOfType, 0, "The list of Type", false);
		click(advancedSearchPage.listOfHeaderColumns.get(3),"Click on Type column again");
		verifyOrderCorrectly(advancedSearchPage.listOfType, 0, "The list of Type", true);
		
		//Verify sorting by Form name
		click(advancedSearchPage.listOfHeaderColumns.get(4),"Click on Form Name column");
		verifyOrderCorrectly(advancedSearchPage.listOfFormName, 0, "The list of Form name", false);
		click(advancedSearchPage.listOfHeaderColumns.get(4),"Click on Form Name column again");
		verifyOrderCorrectly(advancedSearchPage.listOfFormName, 0, "The list of Form name", true);
		
		//verify sorting by Inspector name
		click(advancedSearchPage.listOfHeaderColumns.get(5),"Click on Inspector Name column");
		verifyOrderCorrectly(advancedSearchPage.listOfInspectorName, 0, "The list of Inspector name", false);
		click(advancedSearchPage.listOfHeaderColumns.get(5),"Click on Inspector Name column again");
		verifyOrderCorrectly(advancedSearchPage.listOfInspectorName, 0, "The list of Inspector name", true);
		
		//Verify sorting by Assign Date
		click(advancedSearchPage.listOfHeaderColumns.get(6),"Click on Assign Date column");
		verifyDateOrdercorrectly(advancedSearchPage.listOfAssignDate, 0, "The list of Assgin Date", false);
		click(advancedSearchPage.listOfHeaderColumns.get(6),"Click on Assign Date column again");
		verifyDateOrdercorrectly(advancedSearchPage.listOfAssignDate, 0, "The list of Assgin Date", true);
		

		/**
		 * 
		 * No need to Do: Verify sorting by Sync Time. It is NOT required.
		 * 
		 */
		
		
		//Verify sorting by Customer
		click(advancedSearchPage.listOfHeaderColumns.get(9),"Click on Customer column");
		verifyOrderCorrectly(advancedSearchPage.listOfCustomer, 0, "The list of customer", false);
		click(advancedSearchPage.listOfHeaderColumns.get(9),"Click on Customer column again");
		verifyOrderCorrectly(advancedSearchPage.listOfCustomer, 0, "The list of customer", true);
		
		
	    verifyFinalAssert();
	    
	    
		
	}
	

}
