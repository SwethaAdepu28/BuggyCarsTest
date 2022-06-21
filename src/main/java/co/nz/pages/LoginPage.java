package co.nz.pages;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.webelement.FindWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class LoginPage {
    TestContext testContext;
    TestDriverManager testDriverManager;
    private WebDriver webDriver;
    private FindWebElement find;
    @FindBy(xpath = "//span[@class='label label-warning']")
    private WebElement loginValnMsg;
    public boolean result = false;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li[1]")
    private WebElement usernameOnNav;

    public LoginPage(TestContext context) throws MalformedURLException {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
        this.webDriver = testDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public boolean loginValidationErrMsg(){
        if(loginValnMsg.isDisplayed()){
            result = true;
        }
        return result;
    }

    public boolean displayUserName(String userName){
        if(usernameOnNav.getText().equals("Hi, "+userName)){
            result = true;
        }
        return result;
    }


}
