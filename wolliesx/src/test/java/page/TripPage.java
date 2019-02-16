package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripPage {

    WebDriver driver;

    public TripPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search-input-From")
    private WebElement searchFrom;

    @FindBy(id = "search-input-To")
    private WebElement searchTo;

    @FindBy(id = "search-button")
    private WebElement searchButton;


    public void inputFromAndTo(String from, String to) throws InterruptedException {
        searchFrom.sendKeys(from);
        Thread.sleep(1000);
        searchTo.sendKeys(to);
        Thread.sleep(1000);
        searchButton.click();
    }

    public boolean ifResultsShowed() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='search-results-success']")));
        return searchResults.isDisplayed();
    }
}
