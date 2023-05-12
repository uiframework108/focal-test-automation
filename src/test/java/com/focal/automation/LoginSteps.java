package com.focal.automation;

import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

// Steps for the login page that with email, password fields and other buttons suck as Forgot Password, Change Username...
public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final String LOGIN_PAGE_URL = "https://action.staging.focal.dev/";

    public LoginSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Then("I am on the login page")
    public void navigateToLoginPage() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Expected URL: " + LOGIN_PAGE_URL + " but found: " + actualUrl, LOGIN_PAGE_URL, actualUrl);

        // Validate if the elements are displayed
        Assert.assertTrue("Email field is not displayed", loginPage.isEmailFieldDisplayed());
        Assert.assertTrue("Password field is not displayed", loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue("Submit button is not displayed", loginPage.isSubmitButtonDisplayed());
        Assert.assertTrue("Checkbox is not displayed", loginPage.isRememberMeTextDisplayed());
        Assert.assertTrue("Forgot password link is not displayed", loginPage.isForgotPasswordLinkDisplayed());
        Assert.assertTrue("Change username button is not displayed", loginPage.isChangeUsernameButtonDisplayed());
        Assert.assertTrue("Show password button is not displayed", loginPage.isShowPasswordButtonDisplayed());
    }

    @When("I enter my email {string}")
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @When("I enter my password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I click the show password button")
    public void clickShowPasswordButtonStep() {
        loginPage.clickShowPasswordButton();
    }

    @Then("the password should be visible")
    public void verifyPasswordVisible() {
        Assert.assertTrue("Password is not visible", loginPage.isPasswordVisible());
    }

    @Then("the email field contains {string}")
    public void verifyEmailFieldText(String expectedText) {
        String actualText = loginPage.getEmailFieldText();
        Assert.assertEquals("Email field text does not match the expected value", expectedText, actualText);
    }

    @Then("the password is not visible")
    public void verifyPasswordNotVisible() {
        Assert.assertFalse("Password is visible", loginPage.isPasswordVisible());
    }

    @Then("the login button is enabled")
    public void verifyLoginButtonEnabled() {
        Assert.assertTrue("Login button is not enabled", loginPage.isLoginButtonEnabled());
    }

    @Then("the login button is disabled")
    public void verifyLoginButtonDisabled() {
        Assert.assertFalse("Login button is enabled", loginPage.isLoginButtonEnabled());
    }

    @When("I click the forgot password link")
    public void clickForgotPasswordLink() {
        loginPage.clickForgotPasswordLink();
    }

    @When("I click the change username link")
    public void clickChangeUsernameLink() {
        loginPage.clickChangeUsernameLink();
    }

    @When("I click on the Remember Me checkbox on the login page")
    public void clickRememberMeCheckboxOnLoginPage() {
        loginPage.clickRememberMeCheckbox();
    }

    @Then("the checkbox on the login page is selected")
    public void verifyLoginCheckboxSelected() {
        Assert.assertTrue("Checkbox is not selected", loginPage.isCheckboxSelected());
    }

    @Then("the checkbox on the login page is not selected")
    public void verifyLoginCheckboxNotSelected() {
        Assert.assertFalse("Checkbox is selected", loginPage.isCheckboxSelected());
    }

    @Then("the error message on Login page should be {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        Assert.assertEquals(expectedErrorMessage, loginPage.getInvalidLoginMessageText());
    }

}
