package co.nz.manager;


import co.nz.cucumber.TestContext;
import co.nz.pages.*;
import co.nz.webelement.FindWebElement;

import java.net.MalformedURLException;

public class PageObjectManager {

    private TestContext testContext;
    private FindWebElement findWebElement;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private VotePage votePage;

    public PageObjectManager(TestContext context) {
        testContext = context;
    }

    public FindWebElement getFindWebElement() throws MalformedURLException {
        return (findWebElement == null) ? findWebElement = new FindWebElement(testContext) : findWebElement;
    }

    public HomePage getHomePage() throws MalformedURLException {
        return (homePage == null) ? homePage = new HomePage(testContext) : homePage;
    }

    public RegistrationPage getRegistrationpage() throws MalformedURLException {
        return (registrationPage == null) ? registrationPage = new RegistrationPage(testContext) : registrationPage;
    }

    public LoginPage getLoginPage() throws MalformedURLException {
        return (loginPage == null) ? loginPage = new LoginPage(testContext) : loginPage;
    }

    public ProfilePage getProfilePage() throws MalformedURLException {
        return (profilePage == null) ? profilePage = new ProfilePage(testContext) : profilePage;
    }

    public VotePage getVotePage() throws MalformedURLException {
        return (votePage == null) ? votePage = new VotePage(testContext) : votePage;
    }

}
