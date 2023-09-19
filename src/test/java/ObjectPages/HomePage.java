package ObjectPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
     //Define the locators for the Home page using Page Factory
    //Heading text
    @FindBy(className= "heading")
    @CacheLookup
     WebElement HeadingTextLocator;

    //App logo image
    @FindBy(xpath = "//*[@id='root']/div/div[1]/img")
    @CacheLookup
    WebElement AppLogoImageLocator;

    //Digital card image
    @FindBy(xpath = "//*[@id='root']/div/div[2]/img")
    @CacheLookup
    WebElement DigitalCardImageLocator;


    //"Logout" button
    @FindBy(className = "logout-button")
    @CacheLookup
    WebElement LogOutButtonLocator;


    //--------------------------------------------------------------------------------------------

    WebDriver driver;

    WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver =driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    //Define the methods for performing actions on the elements
    //Get the text content of the Heading

    public String getTextContentHeading(){
        return HeadingTextLocator.getText();
    }
    //Find the App logo image

    public WebElement findAppLogoImage(){
        return AppLogoImageLocator;
    }
    //Find the Digital card image
    public WebElement findDigitalCardImage(){
        return DigitalCardImageLocator;
    }

    //Click the "Logout" button
    public void ClickOnLogOutButton(){
        LogOutButtonLocator.click();
    }


}
