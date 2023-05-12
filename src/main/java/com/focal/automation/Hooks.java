package com.focal.automation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriverException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

// Class for holding context with some basic driver initialization and teardown.
public class Hooks {
    private WebDriver driver;

    // Getting rid of cache, maximizing window, deleting cookies
    // I've added 10 seconds implicit wait so that we have enough time for elements to appear
    private void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    @Before
    public void setup() {
        // No need to initialize the driver here since it's already done in the getInstance() method
    }

    @After
    public void teardown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                captureScreenshot(scenario.getName());
            }
            driver.quit();
            driver = null;
        }
    }

    private void captureScreenshot(String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("reports/" + screenshotName + ".png");
            FileHandler.copy(screenshot, destFile);
            System.out.println("Screenshot captured: " + destFile.getAbsolutePath());
        } catch (WebDriverException | IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}