package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup () {
        String workingDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", workingDir+".\\lib\\chromedriver.exe");
        driver = new ChromeDriver();

        // Create a wait and maximize window, suggest all test classes to use this
        wait = new WebDriverWait(driver,15);
        driver.manage().window().maximize();

        driver.get("https://www.baidu.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown () {
        try {
            //close the process of browser
            driver.close();
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}