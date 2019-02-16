package page;

import helper.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToursPage {

    WebDriver driver;

    public ToursPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='Tours']")
    private WebElement toursLink;

    @FindBy(xpath = "//span[contains(text(),'Search by Listing or City Name')]")
    private WebElement citySearchLink;

    @FindBy(xpath = "//div[@id='select2-drop']//input[@type='text']")
    private WebElement citySearchInput;

    @FindBy(xpath = "//ul[@class='select2-result-sub']/li[1]")
    private WebElement firstOption;

    @FindBy(xpath = "//div[@id='tours']//button[@type='submit'][contains(text(),'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//button[contains(text(),'Details')]")
    private WebElement detailsButton;

    public void bookTour(String cityName) {
        SeleniumHelper seleniumHelper = new SeleniumHelper();
        toursLink.click();
        citySearchLink.click();
        citySearchInput.sendKeys(cityName);
        firstOption.click();
        searchButton.click();
        seleniumHelper.waitForPageLoaded();
        detailsButton.click();

    }

}
