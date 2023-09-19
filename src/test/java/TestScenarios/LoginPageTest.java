package TestScenarios;

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

public class LoginPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage LPage;


    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LPage = new LoginPage(driver);
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

    //Test the Login Page UI
    @Test(priority = 1)
    public void TestLoginPageUI(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Verify if the Login image is displayed - use Assertions,
        //If the Login image is not displayed, print "Login image is not displayed"
        Assert.assertTrue(LPage.findTheLoginImage().isDisplayed(),"Login image is not displayed");
        //Verify the User ID label text - use Assertions,
        //Expected text: User ID
        //If the Label text does not match the expected text, print "User id label text does not match"
        Assert.assertEquals(LPage.getTextOfTheLabelSpecifiedIndex(0),"User ID","User id label text does not match");

        //Verify the Pin label text - use Assertions,
        //Expected text: PIN
        //If the Label text does not match the expected text, print "Pin label text does not match"

        Assert.assertEquals(LPage.getTextOfTheLabelSpecifiedIndex(1),"PIN","Pin label text does not match");

        //Verify the Heading text of the Login page - use Assertions,
        //Expected text: Welcome Back!
        //If the Heading text does not match the expected text, print "Heading text does not match"

        Assert.assertEquals(LPage.getTextContentLoginHeading(),"Welcome Back!","Heading text does not match");

    }

    //Test the submission with empty input fields
    @Test(priority = 2)
    public void TestSubmissionEmptyInput(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Click the "Login" button.
        LPage.ClickOnLoginButton();
        //Verify the error message - use Assertions,
        //Expected text: Invalid user ID
        //If the error message does not match the expected text, print "Error text with empty input fields does not match"
        //Close the browser window.
        Assert.assertEquals(LPage.getTextErrorMessage(),"Invalid user ID","Error text with empty input fields does not match");
    }

    //Test the submission with empty User ID field
    @Test(priority = 3)
    public void TestSubmissionWithUserId(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, only with the PIN 231225.
        LPage.EnterCredentialsAndClickLoginButton("","231225");
        //Verify the error message - use Assertions,
        //Expected text: Invalid user ID
        //If the error message does not match the expected text, print "Error text with empty input field do not match"
        Assert.assertEquals(LPage.getTextErrorMessage(),"Invalid user ID","Error text with empty input field do not match");
        //Close the browser window.

    }

    //Test the submission with empty PIN field

    @Test(priority = 4)
    public void TestSubmissionEmptyPinField(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, only with the User ID 142420.
        LPage.EnterCredentialsAndClickLoginButton("142420","");
        //Verify the error message - use Assertions,
        //Expected text: Invalid PIN
        //If the error message does not match the expected text, print "Error text with empty input field do not match"
        Assert.assertEquals(LPage.getTextErrorMessage(),"Invalid PIN","Error text with empty input field do not match");
        //Close the browser window.

    }

    //Test the submission with invalid PIN
    @Test(priority = 5)
    public void TestSubmissionInvalidPin(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, with the User ID 142420 and PIN 123456.
        LPage.EnterCredentialsAndClickLoginButton("142420","123456");
        //Verify the error message - use Assertions,
        //Expected text: User ID and PIN didn't match
        //If the error message does not match the expected text, print "Error text with invalid PIN do not match"
        Assert.assertEquals(LPage.getTextErrorMessage(),"User ID and PIN didn't match","Error text with invalid PIN do not match");
        //Close the browser window.

    }

    //Test the successful login

    @Test(priority = 6)
    public void TestSuccessfulLogin(){
        //Navigate to the URL https://qaebank.ccbp.tech/ebank/login
        //Login to the application, with the User ID 142420 and PIN 231225.
        LPage.EnterCredentialsAndClickLoginButton("142420","231225");
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));
        //Wait and verify the navigation to the home page - use Assertions,
        //Expected URL: https://qaebank.ccbp.tech/
        //If the current URL does not match the expected URL, print "URLs do not match"
        Assert.assertEquals(driver.getCurrentUrl(),"https://qaebank.ccbp.tech/","URLs do not match");
        //Close the browser window.

    }


}
