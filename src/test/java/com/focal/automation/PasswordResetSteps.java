package com.focal.automation;

import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

// Steps for the password reset page where user provides e-mail connected with his account
public class PasswordResetSteps {

    private WebDriver driver;
    private PasswordResetPage passwordResetPage;
    private static final String PASSWORD_RESET_URL = "https://action.staging.focal.dev/reset-password";

    public PasswordResetSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.passwordResetPage = new PasswordResetPage(driver);
    }

    @Then("I am on the password reset page")
    public void navigateToPasswordResetPage() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Expected URL: " + PASSWORD_RESET_URL + " but found: " + actualUrl, PASSWORD_RESET_URL, actualUrl);
        Assert.assertTrue("Email input field is not displayed.", passwordResetPage.isResetEmailFieldDisplayed());
        Assert.assertTrue("Send link to email button is not displayed.", passwordResetPage.isSendLinkToEmailButtonDisplayed());
        Assert.assertTrue("Back to login button is not displayed.", passwordResetPage.isBackToLoginButtonDisplayed());
    }

    @When("I enter my email for password reset {string}")
    public void enterResetEmail(String email) {
        passwordResetPage.enterResetEmail(email);
    }

    @When("I click the Send link to email button")
    public void clickSendLinkToEmailButton() {
        passwordResetPage.clickSendLinkToEmailButton();
    }

    @When("I click the Back to login button")
    public void clickBackToLoginButton() {
        passwordResetPage.clickBackToLoginButton();
    }

    @When("I click the Close button")
    public void clickCloseButton() {
        passwordResetPage.clickCloseButton();
    }

    @Then("I see the Email sent confirmation message")
    public void verifyPasswordResetSuccessMessage() {
        Assert.assertEquals("Check your email and open the link we sent to continue", passwordResetPage.getEmailConfirmationMessage().getText());
        Assert.assertTrue("Close button not found.", passwordResetPage.isCloseButtonDisplayed());
    }

    @Then("the error message on Password page should be {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        Assert.assertEquals(expectedErrorMessage, passwordResetPage.getIncorrectEmailMessageText());
    }
}