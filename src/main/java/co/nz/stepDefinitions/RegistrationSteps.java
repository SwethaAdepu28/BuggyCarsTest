package co.nz.stepDefinitions;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.pages.HomePage;
import co.nz.pages.RegistrationPage;
import co.nz.webelement.FindWebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.net.MalformedURLException;

public class RegistrationSteps {
    TestContext testContext;
    TestDriverManager testDriverManager;
    FindWebElement find;
    RegistrationPage registrationPage;
    HomePage homePage;

    public RegistrationSteps(TestContext testContext, TestDriverManager testDriverManager, FindWebElement find) throws MalformedURLException {
        this.testContext = testContext;
        this.testDriverManager = testDriverManager;
        this.find = find;
        registrationPage = testContext.getPageObjectManager().getRegistrationpage();
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Given("I'm on Registration Page")
    public void iMOnRegistrationPage() {
        homePage.homePage();
        registrationPage.IClickOnRegistrationBtn();
    }

    @When("I enter Login {string}, First Name {string}, Last Name {string}, Password {string}, Confirm Password {string}")
    public void iEnterLoginFirstNameLastNamePasswordConfirmPassword(String userName, String firstName, String lastName, String pwd, String confirPwd) {
        registrationPage.IEnterAllRequiredFields(userName, firstName,lastName, pwd, confirPwd);
    }

    @And("I click on register button")
    public void iClickOnRegisterButton() {
        registrationPage.iClickRegisterBtn();
    }

    @Then("successful registration message displays")
    public void successfulRegistrationMessageDisplays() {
        Assert.assertTrue(registrationPage.successFullRegistrationMsg());
    }


    @Then("Error message should display for duplicate")
    public void errorMessageShouldDisplayForDuplicate() {
        Assert.assertTrue(registrationPage.faileRegMsg());
    }

    @And("I delete all the entered values")
    public void iDeleteAllTheEnteredValues() {
        registrationPage.deleteFields();
    }

    @Then("I should see all the error messages displaying for empty fields")
    public void iShouldSeeAllTheErrorMessagesDisplayingForEmptyFields() {
        Assert.assertTrue(registrationPage.errorMsgDisplayForAllTheFields());
    }

    @Then("password validation error msg displays")
    public void passwordValidationErrorMsgDisplays() {
        Assert.assertTrue(registrationPage.regErrorMsg());
    }
}
