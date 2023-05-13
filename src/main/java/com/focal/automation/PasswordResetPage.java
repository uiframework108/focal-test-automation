package com.focal.automation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetPage {

    // Locators
    @FindBy(id = "password-reset-form-password")
    private WebElement resetEmailField;

    @FindBy(css = "button[data-testid='password-reset-submit-button']")
    private WebElement sendLinkToEmailButton;

    @FindBy(xpath = "//span[contains(text(),'Back to login')]")
    private WebElement backToLoginButton;

    @FindBy(xpath = "//button/span[contains(text(),'Close')]")
    private WebElement closeButton;

    @FindBy(className = "MuiTypography-caption-206")
    private WebElement emailConfirmationMessage;

    @FindBy(id = "notistack-snackbar")
    private WebElement incorrectEmailMessage;

    public PasswordResetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        Assert.assertTrue("Reset e-mail field not found.", resetEmailField.isDisplayed());
    }

    public void enterResetEmail(String email) {
        resetEmailField.sendKeys(email);
    }

    public void clickSendLinkToEmailButton() {
        sendLinkToEmailButton.click();
    }

    public void clickBackToLoginButton() {
        backToLoginButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public boolean isResetEmailFieldDisplayed() {
        return resetEmailField.isDisplayed();
    }

    public boolean isSendLinkToEmailButtonDisplayed() {
        return sendLinkToEmailButton.isDisplayed();
    }

    public boolean isBackToLoginButtonDisplayed() {
        return backToLoginButton.isDisplayed();
    }

    public boolean isCloseButtonDisplayed() {
        return closeButton.isDisplayed();
    }

    public WebElement getEmailConfirmationMessage() {
        return emailConfirmationMessage;
    }

    public String getIncorrectEmailMessageText() {
        return incorrectEmailMessage.getText();
    }
}
