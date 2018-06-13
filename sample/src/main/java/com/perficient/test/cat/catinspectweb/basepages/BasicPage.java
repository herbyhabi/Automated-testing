package com.perficient.test.cat.catinspectweb.basepages;

import static com.perficient.test.cat.catinspectweb.util.CustomizedFunctionUtil.click;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.perficient.test.cat.catinspectweb.reusablefunctions.ReusableFunctions;
import com.perficient.test.cat.catinspectweb.util.CustomAssertion;
import com.perficient.test.cat.catinspectweb.util.TestCaseBase;

public class BasicPage {
	protected CustomAssertion customAssertion = new CustomAssertion();
	protected ReusableFunctions reusableFunctionUtil = PageFactory.initElements(TestCaseBase.driver, ReusableFunctions.class);
	public BasicPage() {
		PageFactory.initElements(TestCaseBase.driver, this);
	}
	
	//list: Form Category Name list. It exists after clicking on a form name in the page to open a form.
	@FindBy(xpath="//label[contains(@for,'id')]")
	public List<WebElement> listOfFormCategoryNames;
	
	//List: list of column option.
	@FindBy(xpath="//label/span")
    public List<WebElement> listOfColumnsOption;
	
	//Icon: Hamburger
	@FindBy(xpath="//span[@class = 'glyphicon glyphicon-menu-hamburger']")
    public WebElement iconOfHamburger;
	
	//List: Sub menu in the hamburger menu
    @FindBy(xpath="//a[@data-toggle= 'dropdown']")
    public List<WebElement> listOfSubMenu;
    
    //Icon: Media uploaded icon 
    @FindBy(xpath="//div[@class='popup-dialog']")
    public WebElement iconOfLoading;
	
    
    /**
     * Show all columns in home page and advanced search page.
     * @author herby.he
     * @throws Exception
     */
	public void ShowAllColums() throws Exception {
		
		click(iconOfHamburger, "Click on hamburger icon");
		click(listOfSubMenu.get(0), "Click on Column Options");
		for(int i = 0 ; i< listOfColumnsOption.size(); i++) {
			
			if(listOfColumnsOption.get(i).getCssValue("background-color").contains("0, 0, 0, 0"))
			{
				click(listOfColumnsOption.get(i), "Click this cloumn for showing it");
			}
		}
		
	}
}
