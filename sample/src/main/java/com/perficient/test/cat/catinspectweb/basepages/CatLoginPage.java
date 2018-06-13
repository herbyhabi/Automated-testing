package com.perficient.test.cat.catinspectweb.basepages;

import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.fengm1;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.getPswd;
import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;
import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.input;
import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.waitForPageLoadComplete;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatLoginPage extends BasicPage {
	
	//Input: CWS ID
	@FindBy(xpath="//input[@id='cwsUID']")
	public WebElement inputOfCWSID;
	
	//Input: Password
	@FindBy(xpath="//input[@id='cwsPwd']")
	public WebElement inputOfPswd;
	
	//button: Login
	@FindBy(xpath="//input[@id='submitButton']")
	public WebElement btnOfLogin;
	
	//login with cwsID 
	public void login(String cwsID) throws Exception{
		input(inputOfCWSID, cwsID, "Login in with CWS ID "+cwsID);
		input(inputOfPswd, getPswd(cwsID), "Input the password");
		click(btnOfLogin, "In Cat Login Page, click on the Login button.");
		waitForPageLoadComplete();
	}
	
	//Login Cat Inspect with a default account
	public void login() throws Exception{
		login(fengm1);
	}
	
}

