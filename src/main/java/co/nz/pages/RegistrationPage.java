package co.nz.pages;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.webelement.FindWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class RegistrationPage{
    TestContext testContext;
    TestDriverManager testDriverManager;
    private WebDriver webDriver;
    private FindWebElement find;

    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "firstname")
    private WebElement firstName;
    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[contains(text(),'Register')]")
    private WebElement register;
    @FindBy(xpath = "//a[@class='btn']")
    private WebElement regCancelBtn;
    private HomePage homePage;
    @FindBy(xpath = "//div[@class='result alert alert-success']")
    private WebElement successRegMsg;
    @FindBy(xpath = "//div[@class='result alert alert-danger']")
    private WebElement faileRegMsg;
    private boolean result = false;
    @FindBy(xpath = "//div[contains(text(),'Passwords do not match')]")
    private WebElement confirmPwdErrMsg;
    @FindBy(xpath = "//form/div[3]/div[1]")
    private WebElement LastNameErrMsg;
    @FindBy(xpath = "//form/div[2]/div[1]")
    private WebElement firstNameErrMsg;
    @FindBy(xpath = "//div[contains(text(),'Login cannot be more than 50 characters long')]")
    private WebElement loginFieldCharactersLength;
    @FindBy(xpath = "//div[contains(text(),'Login is required')]")
    private WebElement loginReqMsg;
    @FindBy(xpath = "//div[contains(text(),'Password is required')]")
    private WebElement pwdErrMsg;

    public RegistrationPage(TestContext context) throws MalformedURLException {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
        this.webDriver = testDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    public void IClickOnRegistrationBtn(){
        homePage.registerBtn.click();
    }

    public void IEnterAllRequiredFields(String userName, String fName, String LName, String pwd, String CPwd){
        if(username.isDisplayed()){
            username.sendKeys(userName);
            firstName.sendKeys(fName);
            lastName.sendKeys(LName);
            password.sendKeys(pwd);
            confirmPassword.sendKeys(CPwd);
        }
    }

    public void iClickRegisterBtn(){
        register.click();
    }
    public boolean successFullRegistrationMsg(){
        if(successRegMsg.equals("Registration is successful")){
            result = true;
        }
        return result;
    }

    public boolean faileRegMsg(){
        if(faileRegMsg.isDisplayed()){
            result = true;
        }
        return result;
    }

    public void deleteFields(){
        username.clear();
        firstName.clear();
        lastName.clear();
        password.clear();
        confirmPassword.clear();
    }

    public boolean errorMsgDisplayForAllTheFields(){
        if(confirmPwdErrMsg.isDisplayed() && LastNameErrMsg.isDisplayed() && firstNameErrMsg.isDisplayed() && loginReqMsg.isDisplayed()&& pwdErrMsg.isDisplayed()){
            result = true;
        }
        return result;
    }

    public boolean regErrorMsg(){
        if(faileRegMsg.isDisplayed()){
            result = true;
        }
        return result;
    }
}
