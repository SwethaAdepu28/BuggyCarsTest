package co.nz.stepDefinitions;

import co.nz.cucumber.TestContext;
import co.nz.manager.TestDriverManager;
import co.nz.pages.HomePage;
import co.nz.pages.RegistrationPage;
import co.nz.pages.VotePage;
import co.nz.webelement.FindWebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.net.MalformedURLException;

public class VoteSteps {
    TestContext testContext;
    TestDriverManager testDriverManager;
    FindWebElement find;
    RegistrationPage registrationPage;
    HomePage homePage;
    VotePage votePage;

    public VoteSteps(TestContext testContext, TestDriverManager testDriverManager, FindWebElement find) throws MalformedURLException {
        this.testContext = testContext;
        this.testDriverManager = testDriverManager;
        this.find = find;
        registrationPage = testContext.getPageObjectManager().getRegistrationpage();
        homePage = testContext.getPageObjectManager().getHomePage();
        votePage = testContext.getPageObjectManager().getVotePage();
    }

    @Given("I'm on the Buggy cars rating page")
    public void iMOnTheBuggyCarsRatingPage() {
        homePage.homePage();
        homePage.enterLoginDetails("bcd","BcdBcd@123");
        homePage.clickLoginBtn();
        votePage.IclickAlfaRomeo();
    }

    @And("I select a car Model")
    public void iSelectACarModelModel() {
        votePage.selectCar();
    }

    @When("I enter comment")
    public void iEnterComment() {
        votePage.enterComment();
    }

    @And("I click on vote button")
    public void iClickOnVoteButton() {
        votePage.clickVoteBtn();
    }

    @Then("successful vote message displays and author details displays in the grid")
    public void successfulVoteMessageDisplaysAndAuthorDetailsDisplaysInTheGrid() {
        Assert.assertTrue(votePage.successVotemsg());
    }
}
