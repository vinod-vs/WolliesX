package stepdefs;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.RestAssuredHelper;
import helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class StepDefinitions {

    private static final Logger LOGGER = Logger.getLogger(StepDefinitions.class.getName());
    private RestAssuredHelper restAssuredHelper = new RestAssuredHelper();
    SeleniumHelper seleniumHelper = new SeleniumHelper();
    private Response response;
    private WebDriver driver;
    public String day;
    public String city;


    @Given("^I am on the website with URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String url) {
        SeleniumHelper.baseUrl = url;
        driver = SeleniumHelper.getDriver();
        LOGGER.info("Opening the url " + url);
        driver.get(url);
    }

    @Given("^\"([^\"]*)\" logs into his account$")
    public void logsIntoHisAccount(String username) {
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = new SignInPage(driver);
        homePage.signInLink.click();
        signInPage.login(username, "EbayUser!");
    }

    @And("^when that user goes to \"([^\"]*)\" page$")
    public void whenThatUserGoesToPage(String page) throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToPage(page);
        homePage.navigateToPage("Recently viewed");
    }

    public void findAndClick(int index) {
        seleniumHelper.waitForPageLoaded();
        List<WebElement> cartItems = driver.findElements(By.xpath("//ul[contains(@class,'rvi-itemslist')]/li"));
        cartItems.get(index).click();
        seleniumHelper.waitForPageLoaded();
        driver.findElement(By.xpath("//a[@id='atcRedesignId_btn']")).click();
        seleniumHelper.waitForPageLoaded();
    }


    @Then("^adds '(\\d+)' items in  basket and checkout$")
    public void addsItemsInBasketAndCheckout(int itemsInCart) {
        HomePage homePage = new HomePage(SeleniumHelper.driver);
        CartPage cartPage = new CartPage(SeleniumHelper.driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int itemNo = 0; itemNo <= itemsInCart - 1; itemNo++) {
            findAndClick(itemNo);
            driver.navigate().back();
            seleniumHelper.waitForPageLoaded();
        }
        homePage.cart.click();
        seleniumHelper.waitForPageLoaded();
        cartPage.checkoutButton.click();
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[contains(text(),'Select a payment option')]")));
        Assert.assertTrue(element.isDisplayed());
        driver.navigate().back();
    }

    @And("^Removes the items from the cart$")
    public void removesTheItemsFromTheCart() throws Throwable {
        seleniumHelper.waitForPageLoaded();
        CartPage cartPage = new CartPage(SeleniumHelper.driver);
        while (true) {
            try {
                cartPage.removeItem.isDisplayed();
            } catch (Exception e) {
                System.out.println("items are not present in the cart ");
                break;
            }
            cartPage.removeItem.click();
        }
    }

    @When("^Newton wants to book a hotel in \"([^\"]*)\"$")
    public void newtonWantsToBookAHotelIn(String city) throws Throwable {
        HotelsPage hotelsPage = new HotelsPage(driver);
        hotelsPage.selectHotel(city);
    }

    @Then("^Gets the Invoice and confirmation$")
    public void getsTheInvoiceAndConfirmation() throws Throwable {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkout();
    }

    @When("^Newton wants to book a Flight from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void newtonWantsToBookAFlightFromTo(String fromCity, String toCity) throws Throwable {

        FlightsPage flightsPage = new FlightsPage(driver);
        LOGGER.info("Booking Flights for Newton");
        flightsPage.bookFlight(fromCity, toCity);
    }

    @When("^Newton wants to book a tour in \"([^\"]*)\"$")
    public void newtonWantsToBookATourIn(String city) throws Throwable {
        ToursPage toursPage = new ToursPage(driver);
        toursPage.bookTour(city);
    }

    @Given("^I like to holiday in \"([^\"]*)\"$")
    public void cityToHoliday(String cityHoliday) throws Throwable {

        if (cityHoliday.toLowerCase().equals("Sydney")) {
            city = "2147714";
        }
    }

    @And("^I only like to holiday on \"([^\"]*)\"$")
    public void iOnlyLikeToHolidayOn(String dayToHoliday) throws Throwable {
        day = dayToHoliday;
    }


    @And("^the temperature is warmer than '(\\d+)' degrees$")
    public void theTemperatureIsWarmerThanDegrees(int degrees) throws Throwable {
        boolean flag = true;
        String url = "https://openweathermap.org/data/2.5/forecast/?appid=b6907d289e10d714a6e88b30761fae22&id=2147714&units=metric";
        Response response = restAssuredHelper.getRequest(url);
        List<String> dateResponse = response.jsonPath().getList("list.dt_txt");
        List<String> tempResponse = response.jsonPath().getList("list.main.temp_min");
        //Collecting date and time information
        for (int next = 0; next <= dateResponse.size() - 1; next++) {
            String date = dateResponse.get(0).substring(0, 10);
            String time = dateResponse.get(0).substring(11, 19);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            String dayOfWeekl = dayOfWeek.toString().toLowerCase();
            // Logic to find if the temparature is less than the expected temp
            if (dayOfWeekl.equals(day.toLowerCase())) {
                float minTemp = new Float(String.valueOf(tempResponse.get(next)));
                String[] hour = time.split(":");
                if (minTemp < (float) degrees && (Integer.parseInt(hour[0]) > 6)) {
                    System.out.println("Its cold at " + time);
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            LOGGER.info("Weather is fine on " + day);
        }
    }


    @When("^I look up and receive the weather forecast$")
    public void iLookUpAndReceiveTheWeatherForecast() throws Throwable {

    }
}