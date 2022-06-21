package co.nz.pages;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.webelement.FindWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;

public class ProfilePage {
    TestContext testContext;
    TestDriverManager testDriverManager;
    private WebDriver webDriver;
    private FindWebElement find;
    private HomePage homePage;
    @FindBy(id = "gender")
    private WebElement gender;
    @FindBy(id = "age")
    private WebElement age;
    @FindBy(id = "address")
    private WebElement address;
    @FindBy(id = "phone")
    private WebElement phone;
    @FindBy(id = "hobby")
    private WebElement hobby;
    @FindBy(xpath = "//div[@class='result alert alert-success hidden-md-down']")
    private WebElement profileUpdateSuccMsg;
    @FindBy(partialLinkText = "Profi")
    private WebElement profileLink;
    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement voteBtn;
    @FindBy(id = "comment")
    private WebElement voteComment;

    public boolean result = false;

    public ProfilePage(TestContext context) throws MalformedURLException {
        testContext = context;
        this.find = testContext.getPageObjectManager().getFindWebElement();
        testDriverManager = testContext.getTestDriverManager();
        this.webDriver = testDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    public void clickProfileLink(){
        profileLink.click();
    }

    public void updateProfile(String gen, String UAge, String userAdd, String userPhone, String userHobby){
        gender.sendKeys(gen);
        age.sendKeys(UAge);
        address.sendKeys(userAdd);
        phone.sendKeys(userPhone);
        Select hobbyDropDwn = new Select(hobby);
        hobbyDropDwn.selectByVisibleText(userHobby);
    }

    public boolean profileUpdateSuccessMsg(){
        if(profileUpdateSuccMsg.equals("The profile has been saved successful ")){
            result = true;
        }
        return result;
    }

}
