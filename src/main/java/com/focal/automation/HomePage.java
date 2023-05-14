package com.focal.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Page Object pattern class for Homepage, holding selectors and method on them
public class HomePage {

    // Locators
    @FindBy(id = "realm-form-username")
    private WebElement emailField;

    @FindBy(xpath = "//button[@data-testid='realm-submit-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement rememberMeText;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public String getEmailFieldText() {
        return emailField.getAttribute("value");
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public boolean isEmailFieldDisplayed() {
        return emailField.isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return continueButton.isDisplayed();
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
}
