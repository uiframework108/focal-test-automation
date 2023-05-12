package com.focal.automation;

import cucumber.api.java.en.And;

// Class that would include some generic steps like this timeout step below.
public class HelperSteps {

    @And("I wait for {int} seconds")
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
    }
}
