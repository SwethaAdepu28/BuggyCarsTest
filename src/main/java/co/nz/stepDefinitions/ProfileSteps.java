package co.nz.stepDefinitions;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.pages.HomePage;
import co.nz.pages.LoginPage;
import co.nz.pages.ProfilePage;
import co.nz.pages.RegistrationPage;
import co.nz.webelement.FindWebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

public class ProfileSteps {

    TestContext testContext;
    TestDriverManager testDriverManager;
    FindWebElement find;
    RegistrationPage registrationPage;
    HomePage homePage;
    ProfilePage profilePage;

    public ProfileSteps(TestContext testContext, TestDriverManager testDriverManager, FindWebElement find) throws MalformedURLException {
        this.testContext = testContext;
        this.testDriverManager = testDriverManager;
        this.find = find;
        registrationPage = testContext.getPageObjectManager().getRegistrationpage();
        homePage = testContext.getPageObjectManager().getHomePage();
        profilePage = testContext.getPageObjectManager().getProfilePage();
    }

    @Given("Logged In user")
    public void loggedInUser() {
        homePage.homePage();
        homePage.enterLoginDetails("abc","abc");
    }

    @And("I click on profile link")
    public void iClickOnProfileLink() {
        profilePage.clickProfileLink();
    }

    @When("I enter Gender {string}, Age {string}, Address {string}, phone {string}, hobby {string}")
    public void iEnterGenderAgeAddressPhoneHobby(String gender, String age, String address, String phone, String hobby) {
        profilePage.updateProfile(gender, age, address, phone, hobby);
    }

    @Then("profile updated success message")
    public void profileUpdatedSuccessMessage() {
        profilePage.profileUpdateSuccessMsg();
    }

}
