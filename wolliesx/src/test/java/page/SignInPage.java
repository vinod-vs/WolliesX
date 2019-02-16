package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class SignInPage {

    WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(CheckoutPage.class.getName());

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='userid']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='pass']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@id='sgnBt']")
    WebElement signInButton;

    public void login(String username, String password) {
        LOGGER.info(username + "Logging into Ebay");
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signInButton.click();
    }
}
