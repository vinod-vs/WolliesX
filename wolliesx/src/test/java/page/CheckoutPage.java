package page;

import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.logging.Logger;

public class CheckoutPage {
    private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(CheckoutPage.class.getName());

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Book Now')]")
    public WebElement bookNowButton;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement email;

    @FindBy(xpath = "//input[@placeholder='Confirm Email']")
    public WebElement confirmEmail;

    @FindBy(xpath = "//input[@placeholder='Contact Number']")
    public WebElement contactNumber;


    @FindBy(xpath = "//input[@placeholder='Address']")
    public WebElement adress;

    @FindBy(xpath = "//button[contains(text(),'CONFIRM THIS BOOKING')]")
    public WebElement confirmBooking;

    @FindBy(xpath = "//div[contains(text(),'Unpaid')]")
    public WebElement unpaid;

    public void checkout() {
        SeleniumHelper seleniumHelper = new SeleniumHelper();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        bookNowButton.click();
        firstName.sendKeys("abcd");
        lastName.sendKeys("efgh");
        email.sendKeys("abcd@gmail.com");
        confirmEmail.sendKeys("abcd@gmail.com");
        contactNumber.sendKeys("1313232");
        adress.sendKeys("asfafaf afaaf");
        js.executeScript("window.scrollTo(0, 1000)");
        confirmBooking.click();
        seleniumHelper.waitForPageLoaded();
        Assert.assertTrue(unpaid.isDisplayed(), "There is no Invoice ");
        LOGGER.info("Successfully Booked and Invoice Recieved ");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'abcd efgh')]")).isDisplayed(), "There is no Invoice for the name ");


    }

}
