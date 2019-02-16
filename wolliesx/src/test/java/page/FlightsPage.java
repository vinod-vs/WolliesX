package page;

import helper.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class FlightsPage {
    private static final Logger LOGGER = Logger.getLogger(FlightsPage.class.getName());
    WebDriver driver;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='Flights']")
    public WebElement flightsLink;

    @FindBy(xpath = "//div[@id='s2id_location_from']")
    public WebElement fromCityLink;

    @FindBy(xpath = "//div[@id='select2-drop']//input[@type='text']")
    public WebElement fromCityInput;

    @FindBy(xpath = "//div[@id='select2-drop']//ul[@class='select2-results']/li[1]")
    public WebElement firstOption;

    @FindBy(xpath = "//div[@id='s2id_location_to']")
    public WebElement toCityLink;

    @FindBy(xpath = "//input[@placeholder='Depart']")
    public WebElement departDate;

    @FindBy(xpath = "//div[contains(@class,'bgfade col-md-3 col-xs-12 search-button')]//button[contains(@type,'submit')][contains(text(),'Search')]")
    public WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Available Flights')]")
    public WebElement availableFlights;

    public void bookFlight(String fromCity, String toCity) throws InterruptedException {
        SeleniumHelper seleniumHelper = new SeleniumHelper();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        flightsLink.click();
        fromCityLink.click();
        LOGGER.info("Entering Destination details ");
        fromCityInput.sendKeys(fromCity);
        Thread.sleep(5000);
        firstOption.click();
        Thread.sleep(5000);
        toCityLink.click();
        fromCityInput.sendKeys(toCity);
        Thread.sleep(5000);
        firstOption.click();
        Thread.sleep(5000);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        LOGGER.info("Entering Date for Travel");
        departDate.click();
        departDate.sendKeys(date);
        submitButton.click();
        seleniumHelper.waitForPageLoaded();
        js.executeScript("window.scrollTo(0, 500)");
        Assert.assertTrue(availableFlights.isDisplayed(), "There are no flights available");
        LOGGER.info("There are flights available between " + fromCity + " and " + toCity + " on " + date);

    }
}
