package ObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    //Define the locators for the Login page using Page Factory(use how and using)

    //Login image
    @FindBy(how = How.CLASS_NAME, using="login-ebank-image")
    @CacheLookup
    WebElement loginImageLocator;

    //List of Label texts
    @FindBy(how = How.CLASS_NAME, using="input-label")
    @CacheLookup
    List<WebElement> ListOfLabelLocator;

    //Login heading
    @FindBy(how = How.CLASS_NAME, using="login-heading")
    @CacheLookup
    WebElement LoginHeadingLocator;



    //"User ID" input field
    @FindBy(how = How.ID, using="userIdInput")
    @CacheLookup
    WebElement UserIDInputLocator;

    //"PIN" input field
    @FindBy(how = How.ID,using="pinInput")
    @CacheLookup
    WebElement PinInputLocator;

    //"Login" button
    @FindBy(how = How.CLASS_NAME,using = "login-button")
    @CacheLookup
    WebElement LoginButtonLocator;

    //Error Message ()

    @FindBy(how = How.CLASS_NAME,using = "error-message-text")
    @CacheLookup
    WebElement ErrorMessageLocator;

    //_____________________________________________________________________________________________

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    //Define the methods for performing actions on the elements
    //Find the Login image

    public WebElement findTheLoginImage(){
        return loginImageLocator;
    }

    //Get the text content of the Login heading

    public String getTextContentLoginHeading(){
        return LoginHeadingLocator.getText();
    }

    //Get the text content of the label at a specified index
    public String getTextOfTheLabelSpecifiedIndex(int index){
        return ListOfLabelLocator.get(index).getText();
    }

    //Enter a text in the "User ID" input field
    public void EnterUserIdInput(String UserId){
        UserIDInputLocator.sendKeys(UserId);
    }

    //Enter a text in the "PIN" input field
    public void EnterPINInput(String pin){
        PinInputLocator.sendKeys(pin);
    }

    //Click the "Login" button

    public void ClickOnLoginButton(){
        LoginButtonLocator.click();
    }

    //Enter with the given credentials and click the "Login" button
    public void EnterCredentialsAndClickLoginButton(String UserId,String pin){
        EnterUserIdInput(UserId);
        EnterPINInput(pin);
        ClickOnLoginButton();
    }


    //Wait and get the text content of the error message
    public String getTextErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        return ErrorMessageLocator.getText();
    }

}
