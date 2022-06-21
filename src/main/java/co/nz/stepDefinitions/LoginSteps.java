package co.nz.stepDefinitions;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.pages.HomePage;
import co.nz.pages.LoginPage;
import co.nz.pages.RegistrationPage;
import co.nz.webelement.FindWebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.net.MalformedURLException;

public class LoginSteps {
    TestContext testContext;
    TestDriverManager testDriverManager;
    FindWebElement find;
    HomePage homePage;
    LoginPage loginPage;

    public LoginSteps(TestContext testContext, TestDriverManager testDriverManager, FindWebElement find) throws MalformedURLException {
        this.testContext = testContext;
        this.testDriverManager = testDriverManager;
        this.find = find;
        homePage = testContext.getPageObjectManager().getHomePage();
        loginPage =testContext.getPageObjectManager().getLoginPage();
    }

    @Given("I'm on Buggy Cars Rating home page")
    public void iMOnBuggyCarsRatingHomePage() {
        homePage.homePage();
    }

    @When("I enter username {string}, password {string}")
    public void iEnterUsernamePassword(String userName, String pwd) {
        homePage.enterLoginDetails(userName,pwd);
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        homePage.clickLoginBtn();
    }

    @Then("validation Error message displays")
    public void validationErrorMessageDisplays() {
        Assert.assertTrue(loginPage.loginValidationErrMsg());
    }

    @Then("logged In username displays in the navigation bar")
    public void loggedInUsernameDisplaysInTheNavigationBar() {
        Assert.assertTrue(loginPage.displayUserName("cde"));
    }
}
