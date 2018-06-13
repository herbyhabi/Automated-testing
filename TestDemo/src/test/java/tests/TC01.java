package tests;

import org.testng.annotations.Test;
import pages.BasicPage;
import utilities.Log;
import utils.TestBase;

public class TC01 extends TestBase {
    BasicPage basicPage;

    @Test
    public void verify_TC01() throws Exception {

        basicPage = new BasicPage();

        Log.info("Step 1: Enter keywords into search field");
        basicPage.inputOfSearch.sendKeys("Automated Testing");

        Log.info("Step 2: Click on Submit button");
        basicPage.btnOfSubmit.click();






    }

}
