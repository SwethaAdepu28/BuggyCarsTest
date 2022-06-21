package co.nz.manager;

import co.nz.cucumber.TestContext;
import co.nz.enums.DriverType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static co.nz.utility.Utils.getConfigValue;

public class TestDriverManager {

    private WebDriver driver;
    private DriverType driverType;
    public static final String AUTOMATE_USERNAME = "sweet_mZs3Tk"; // here need to provide browser stack details for now I provided dummy values
    public static final String AUTOMATE_ACCESS_KEY = "U3eKgydfsafewrGVMrKzDJ2XDe3e"; // here need to provide browser stack details for now I provided dummy values
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private TestContext testContext;

    public TestDriverManager(TestContext context) {
        this.testContext = context;
    }

    public WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() throws MalformedURLException {
        driverType = getDeviceType();
        switch (driverType) {
            case CHROME:
                driver = createChromeDriver();
                driver.manage().window().maximize();
                break;
            case SAFARI:
                driver = createSafariDriver();
                driver.manage().window().maximize();
                break;
            default:
                break;
        }

        if (driver == null) {
            Assert.fail("Unable to initiate driver");
        }

        return driver;
    }

    private WebDriver createSafariDriver() {
        driver = new SafariDriver();
        return driver;
    }

    public DriverType getDeviceType() {
        String platformName = getConfigValue("default.browser") ;
        switch (platformName) {
            case "chrome":
                return DriverType.CHROME;
            case "safari":
                return DriverType.SAFARI;
            case "android":
                return DriverType.ANDROID;
            case "ios":
                return DriverType.IOS;
            default:
                throw new RuntimeException("Platform Name Key value in System.properties is not matching : " + platformName);
        }

    }

    public void closeDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
        }
    }

    private WebDriver createChromeDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "91.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
        caps.setCapability("build", "BStack Build Number 1"); // CI/CD job or build name
        final WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        return driver;
    }

    public DriverType getDriverType() {
        return driverType;
    }

}