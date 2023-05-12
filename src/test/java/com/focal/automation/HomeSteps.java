package com.focal.automation;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;

// Steps for the main homepage that contains Keycloak realm username input field
public class HomeSteps {

    private WebDriver driver;
    private HomePage homePage;
    private static final String HOME_PAGE_URL = "https://action.staging.focal.dev/";

    public HomeSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.homePage = new HomePage(driver);
    }

    @Given("I am on the homepage")
    public void navigateToHomePage() {
        if (!driver.getCurrentUrl().equals(HOME_PAGE_URL)) {
            driver.get(HOME_PAGE_URL);
        }
        Assert.assertEquals("Expected URL: " + HOME_PAGE_URL + " but found: " + driver.getCurrentUrl(), HOME_PAGE_URL, driver.getCurrentUrl());

        // Validate if the elements are displayed
        Assert.assertTrue("Email field is not displayed", homePage.isEmailFieldDisplayed());
        Assert.assertTrue("Continue button is not displayed", homePage.isContinueButtonDisplayed());
        Assert.assertTrue("Checkbox is not displayed", homePage.isRememberMeTextDisplayed());
    }

    @When("I enter my Realm username or email {string}")
    public void enterEmail(String email) {
        homePage.enterEmail(email);
    }

    @Then("the Realm email field contains {string}")
    public void verifyEmailFieldText(String expectedText) {
        String actualText = homePage.getEmailFieldText();
        Assert.assertEquals("Email field text does not match the expected value", expectedText, actualText);
    }

    @When("I click the continue button")
    public void clickContinueButton() {
        homePage.clickContinueButton();
    }

    @Then("the continue button is enabled")
    public void verifyContinueButtonEnabled() {
        Assert.assertTrue("Continue button is not enabled", homePage.isContinueButtonEnabled());
    }

    @Then("the continue button is disabled")
    public void verifyContinueButtonDisabled() {
        Assert.assertFalse("Continue button is enabled", homePage.isContinueButtonEnabled());
    }

    @When("I click on the Remember Me checkbox on the homepage")
    public void clickRememberMeCheckboxOnHomePage() {
        homePage.clickRememberMeCheckbox();
    }

    @Then("the checkbox on the homepage is selected")
    public void verifyCheckboxSelected() {
        Assert.assertTrue("Checkbox is not selected", homePage.isCheckboxSelected());
    }

    @Then("the checkbox on the homepage is not selected")
    public void verifyCheckboxNotSelected() {
        Assert.assertFalse("Checkbox is selected", homePage.isCheckboxSelected());
    }
}