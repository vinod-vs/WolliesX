package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helper.SeleniumHelper;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class ServiceHooks {

    public static WebDriver driver;

    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void openBrowser() throws MalformedURLException {
        driver = SeleniumHelper.openBrowser();
        driver.manage().deleteAllCookies();
    }
    @After


    public void embedScreenshot(Scenario scenario) {

        driver.quit();

        if (scenario.isFailed()) {
            try {
                // Code to capture and embed images in test reports (if scenario fails)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
