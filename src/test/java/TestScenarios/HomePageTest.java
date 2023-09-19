package TestScenarios;

import ObjectPages.HomePage;
import ObjectPages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage LPage;
    HomePage HPage;


    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LPage = new LoginPage(driver);
        HPage = new HomePage(driver);
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterMethod
    public void TearDown(){
        driver.quit();
    }


    //Test the Home page UI
    @Test(priority = 7)
    public void TestHomePageUI(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, with the User ID 142420 and PIN 231225.
        LPage.EnterCredentialsAndClickLoginButton("142420","231225");
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));
        //Verify if the App logo is displayed - use Assertions,
        //If the App logo is not displayed, print "App logo is not displayed"
       Assert.assertTrue(HPage.findAppLogoImage().isDisplayed(),"App logo is not displayed");

        //Verify the Heading text of the home page - use Assertions
        //Expected text: Your Flexibility, Our Excellence
        //If the Heading text does not match the expected text, print "Heading text does not match"
        Assert.assertEquals(HPage.getTextContentHeading(),"Your Flexibility, Our Excellence","Heading text does not match");

        //Verify if the Digital card image is displayed - use Assertions,
        //If the Digital card image is not displayed, print "Digital card image is not displayed"
        //Close the browser window.

        Assert.assertTrue(HPage.findDigitalCardImage().isDisplayed(),"Digital card image is not displayed");
    }

    //Test the Logout functionality

    @Test(priority = 8)
    public void TestLogoutFunctionality(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, with the User ID 142420 and PIN 231225.
        //Wait until the web driver navigates to the home page
        //Home Page URL: https://qaebank.ccbp.tech/
        LPage.EnterCredentialsAndClickLoginButton("142420","231225");
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));
        HPage.ClickOnLogOutButton();

        //Verify the navigation to the login page - use Assertions,
        //Expected URL: https://qaebank.ccbp.tech/ebank/login/
        //If the current URL does not match the expected URL, print "URLs do not match"
        //Close the browser window.


        Assert.assertEquals(driver.getCurrentUrl(),"https://qaebank.ccbp.tech/ebank/login","URLs do not match");

    }
}
