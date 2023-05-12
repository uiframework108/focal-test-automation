package com.focal.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    // Locators
    @FindBy(id = "login-form-username")
    private WebElement emailField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='password' and @id='login-form-password']")
    private WebElement hiddenPasswordInput;

    @FindBy(xpath = "//input[@type='text' and @id='login-form-password']")
    private WebElement visiblePasswordInput;

    @FindBy(xpath = "//button[@data-testid='login-submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[contains(@class, 'MuiButtonBase-root-196')]")
    private WebElement showPasswordButton;

    @FindBy(xpath = "//span[contains(text(),'Forgot your password?')]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//span[contains(text(),'Change username')]")
    private WebElement changeUsernameLink;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement rememberMeText;

    @FindBy(id = "notistack-snackbar")
    private WebElement invalidLoginMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        submitButton.click();
    }

    public boolean isLoginButtonEnabled() {
        return submitButton.isEnabled();
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void clickChangeUsernameLink() {
        changeUsernameLink.click();
    }

    public boolean isEmailFieldDisplayed() {
        return emailField.isDisplayed();
    }

    public String getEmailFieldText() {
        return emailField.getAttribute("value");
    }

    public boolean isRememberMeTextDisplayed() {
        return rememberMeText.isDisplayed();
    }

    public void clickRememberMeCheckbox() {
        rememberMeCheckbox.click();
    }

    public boolean isCheckboxSelected() {
        return rememberMeCheckbox.isSelected();
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitButton.isDisplayed();
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return forgotPasswordLink.isDisplayed();
    }

    public boolean isChangeUsernameButtonDisplayed() {
        return changeUsernameLink.isDisplayed();
    }

    public boolean isShowPasswordButtonDisplayed() {
        return showPasswordButton.isDisplayed();
    }

    public void clickShowPasswordButton() {
        showPasswordButton.click();
    }

    public boolean isPasswordVisible() {
        String inputType = passwordField.getAttribute("type");
        String inputId = passwordField.getAttribute("id");
        // Handle unexpected input type or id
        if (inputType.equals("password") && inputId.equals("login-form-password")) {
            return false;
        } else return inputType.equals("text") && inputId.equals("login-form-password");
    }

    public String getInvalidLoginMessageText() {
        return invalidLoginMessage.getText();
    }
}