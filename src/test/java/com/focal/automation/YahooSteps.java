package com.focal.automation;

import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;

// Steps for Yahoo mailbox e-mail handling
// There is no Page Object here for this one since it is helper-like, just an external tool to help us handle external
// mailbox. These steps would be aggregated together into small amount / just one step to make cucumber scenarios more
// compact. No additional validation here, just the necessary minimum to get there and click on the proper password
// reset link.
public class YahooSteps {
    private WebDriver driver;
    private static final String YAHOO_MAILBOX_URL = "https://mail.yahoo.com/";

    public YahooSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @When("I open Yahoo Mail in the current tab")
    public void openYahooMail() {
        driver.get(YAHOO_MAILBOX_URL);
    }

    @And("I enter my Yahoo email {string}")
    public void enterYahooMailEmail(String email) {
        WebElement emailField = driver.findElement(By.id("login-username"));
        emailField.sendKeys(email);
    }

    @And("I confirm it by clicking Next button")
    public void clickYahooNextButton() {
        WebElement nextButton = driver.findElement(By.id("login-signin"));
        nextButton.click();
    }

    @And("I enter my Yahoo password {string}")
    public void enterYahooPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("login-passwd"));
        passwordField.sendKeys(password);
    }

    // Click on the Mailbox folder to refresh it
    @And("I click on the mailbox folder")
    public void clickOnTheMailboxFolder() {
        WebElement mailboxFolder = driver.findElement(By.xpath("//span[@data-test-id='folder-label']"));
        mailboxFolder.click();
    }

    @When("I click on the email with subject {string}")
    public void clickOnTheEmailWithSubject(String subject) {
        WebElement email = driver.findElement(By.xpath("//span[@data-test-id='message-subject' and text()='" + subject + "']"));
        email.click();
    }

    @And("I click on the link in the email body")
    public void clickLinkInEmailBody() {
        WebElement emailBodyLink = driver.findElement(By.xpath("//a[contains(@href, 'action.staging.focal.dev') and contains(text(), 'Reset my password')][last()]\n"));
        String linkUrl = emailBodyLink.getAttribute("href");

        // Open the link in the current tab
        driver.get(linkUrl);
    }
}