package co.nz.pages;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.webelement.FindWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import co.nz.utility.Utils;
import java.net.MalformedURLException;


public class HomePage {
    TestContext testContext;
    TestDriverManager testDriverManager;
    private WebDriver webDriver;
    private FindWebElement find;

    @FindBy(partialLinkText = "Regist")
    WebElement registerBtn;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;
    @FindBy(xpath = "//input[@type='text']")
    WebElement loginUserName;
    @FindBy(xpath = "//input[@type='password']")
    WebElement loginPwd;

    public HomePage(TestContext context) throws MalformedURLException {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
        this.webDriver = testDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void homePage(){
        webDriver.get(Utils.getConfigValue("url"));
    }

    public void enterLoginDetails(String logUserName, String logPwd){
        loginUserName.sendKeys(logUserName);
        loginPwd.sendKeys(logPwd);
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

}
