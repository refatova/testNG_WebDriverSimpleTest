import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginPageTest {
    static WebDriver driver;

    @BeforeTest
    void initWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/Users/Saniye/Projects/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterTest
    void teardown() {
        driver.quit();
    }

    @Test
    void checkSuccessLogin() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        HomePage home = loginPage.typeLogin("mngr177707")
                .typePassword("pUhutet")
                .submitLoginForm();
        assertEquals("Guru99 Bank Manager HomePage", home.getTitle());
        assertTrue(home.isElementPresent("mngr177707"));
        takeScreenshot(Reporter.getCurrentTestResult());
    }


    @Test(dataProviderClass = DataProvidersForLoginTest.class ,dataProvider ="invalidloginCred")
    void checkValidationMessage(String login, String pass) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.typeLogin(login)
                .typePassword(pass)
                .submitLoginForm();
        assertEquals("User or Password is not valid", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }


    public static void takeScreenshot(ITestResult result) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
            File destFile = new File((String) reportDirectory+"/screenshots/"+methodName+formater.format(calendar.getTime())+".png");
            FileUtils.copyFile(scrFile, destFile);
            Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
