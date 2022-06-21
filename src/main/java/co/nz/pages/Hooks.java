package co.nz.pages;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.time.LocalDateTime;


public class Hooks {

    WebDriver driver;
    TestContext testContext;
    TestDriverManager testDriverManager;
    static LocalDateTime lDateTime  = LocalDateTime.now();

    public Hooks(TestContext context) {
        testContext = context;
        testDriverManager = testContext.getTestDriverManager();
    }

    @Before
    public void beforeScenario(Scenario scenario) throws MalformedURLException {
        driver = testDriverManager.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws MalformedURLException {
        if(scenario.isFailed()) {
            System.out.println("failed scenarios =======      " + scenario.getName());
            byte[] imageBytes = ((TakesScreenshot) testDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(imageBytes, "image/png", "image");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        testDriverManager.closeDriver();
    }
}
