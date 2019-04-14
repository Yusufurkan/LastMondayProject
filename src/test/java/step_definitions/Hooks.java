package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("I am setting up the test from the Hooks class!!!");
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        if (ConfigurationReader.getProperty("browser").contains("headless")) {
            Driver.getDriver().manage().window().setSize(new Dimension(1440, 900));
        } else {
            Driver.getDriver().manage().window().maximize();

        }
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        System.out.println("Closing driver");
//     DriverPojo.closeDriver();
    }


}