package co.nz.webelement;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import co.nz.enums.Wait;


public class FindWebElement {


    private static final Logger logger = LoggerFactory.getLogger(FindWebElement.class);
    private final By loadingIndicator = By.xpath("//div[@class='LoadingIndicator']");
    private FluentWait<WebDriver> wait;
    private WebDriverWait busyIndicatorWait;
    private WebDriverWait extendedWait;
    private TestContext testContext;
    private TestDriverManager testDriverManager;
    private WebDriver driver;

    public FindWebElement(TestContext context) throws MalformedURLException {
        this.testContext = context;
        this.testDriverManager = testContext.getTestDriverManager();
        this.driver = testDriverManager.getDriver();

        wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofMillis(100))
            .ignoring(NoSuchElementException.class);
    }

    public WebElement webElement(By elemLocator) {

        logger.info("Trying to find element " + elemLocator);

        WebElement element = wait.until(d -> d.findElement(elemLocator));

        highlightElement(element);

        logger.info("Found element " + elemLocator);

        return element;
    }

    public WebElement displayedElement(By elemLocator) {

        List<WebElement> elements = webElements(elemLocator);

        if (elements.size() > 0) {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return element;
                }
            }
        }

        elements.forEach(element -> System.out.println(element.getText()));

        throw new ElementNotVisibleException("No Visible element has been located");
    }

    public WebElement findElementByXpath(String xpath) {
        WebElement element = null;
        for (int i = 0; i <= 2; i++) {
            try {
                wait.withMessage(xpath);
                wait.until((ExpectedCondition<Boolean>) d -> (driver.findElement(By.xpath(xpath)).isDisplayed()));
                element = driver.findElement(By.xpath(xpath));
                highlightElement(element);
                break;
            } catch (StaleElementReferenceException se) {
                System.out.println(se.getMessage());
            }
        }
        return element;
    }

    public List<WebElement> findElementsByXpath(String xpath) {
        wait.withMessage(xpath);
        return driver.findElements(By.xpath(xpath));
    }

    public List<WebElement> webElements(By elemLocator) {


        logger.info("Trying to find elements " + elemLocator);

        List<WebElement> elements = wait.until(d -> d.findElements(elemLocator));

        if (elements.size() > 0) {
            logger.info("Elements found " + elemLocator + " is [" + elements.size() + "]");
        } else {
            logger.info("No elements found " + elemLocator);
        }
        return elements;
    }


    private void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**********************************************************************************
     **WAIT METHODS
     **********************************************************************************/
    public boolean WaitUntilWebElementIsVisible(WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        return true;

    }

    public void waitForAllElementVisible(List<WebElement> webElement, int timeOutSeconds) throws Error {
        new WebDriverWait(driver, timeOutSeconds).until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public void waitForTextToAppear(WebElement element, String textToAppear) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

    public void waitForAllPageElementsLoaded() {
        new WebDriverWait(driver, 30).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void scrollToAllElementByWebElementLocator(List<WebElement> element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfAllElements(element));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
            System.out.println("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }


    /**********************************************************************************
     **CLICK METHODS
     **********************************************************************************/
    public void waitAndClickElement(WebElement element) {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                clicked = true;
            } catch (Exception e) {
                System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }

    public void sleep(long lon) {
        try {
            Thread.sleep(lon);
        } catch (Exception e) {

        }
    }

    public void waitAndClickElementsUsingByLocator(By by) {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                System.out.println("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                System.out.println("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<" + by.toString() + ">");
            }
            attempts++;
        }
    }

    public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) {
        org.openqa.selenium.support.ui.Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            list.sendKeys(textToSearchFor);
            list.sendKeys(Keys.ENTER);
            System.out.println("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }


    public boolean isElementClickable(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }

}
