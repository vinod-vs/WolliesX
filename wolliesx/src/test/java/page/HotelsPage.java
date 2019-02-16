package page;

import helper.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class HotelsPage {
    WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(HotelsPage.class.getName());

    public HotelsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Search by Hotel or City Name')]")
    public WebElement citySearch;

    @FindBy(xpath = "//div[@id='select2-drop']//input[contains(@type,'text')]")
    public WebElement citySearchInput;

    @FindBy(xpath = "//ul[@class='select2-result-sub']//li[1]")
    public WebElement firstOption;


    @FindBy(xpath = "//div[@id='dpd1']//input[@placeholder='Check in']")
    public WebElement checkInDate;


    @FindBy(xpath = "//input[@placeholder='Check out']")
    public WebElement checkOutDate;


    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Search')]")
    public WebElement searchButton;


    @FindBy(xpath = "//table[@class='bgwhite table table-striped']/tbody/tr[1]/td[1]/div[3]/a")
    public WebElement hotelDetails;


    @FindBy(xpath = "//tbody//tr[1]//td[1]//div[2]//div[2]//div[1]//div[3]//div[1]//label[1]//div[1]")
    public WebElement label;

    public void selectHotel(String city) {
        LOGGER.info("Checking hotels in " + city);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        SeleniumHelper seleniumHelper = new SeleniumHelper();
        citySearch.click();
        citySearchInput.sendKeys(city);
        firstOption.click();
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        cal.add(Calendar.DATE, 2);
        String futureDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        checkInDate.click();
        checkInDate.sendKeys(date);
        checkOutDate.click();
        checkOutDate.sendKeys(futureDate);
        searchButton.click();
        seleniumHelper.waitForPageLoaded();
        hotelDetails.click();
        js.executeScript("window.scrollTo(0, 1000)");
        label.click();
    }
}
