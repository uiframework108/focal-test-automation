package com.focal.automation;

import cucumber.api.Scenario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Main test runner, besides CucumberOptions configuration it also initializes driver, and then afterwards
// generates report, which is later copied to html-reports folder in the parent project directory.
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/focal/automation/login.feature",
        glue = "com.focal.automation",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber-json-report/cucumber.json"
        },
        tags = "(@action-tool or @end-to-end or @functional) and @done"
)
public class RunCucumberTest {
        private Hooks hooks;
        private HomeSteps homeSteps;
        private YahooSteps yahooSteps;

        @Before
        public void setup() {
                hooks = new Hooks();
                homeSteps = new HomeSteps(hooks);
                yahooSteps = new YahooSteps(hooks);
        }

        @After
        public void teardown(Scenario scenario) {
                hooks.teardown(scenario);
        }

        @AfterClass
        public static void generateReport() {
                File reportOutputDirectory = new File("target/cucumber-report");
                List<String> jsonFiles = new ArrayList<>();
                jsonFiles.add("target/cucumber-json-report/cucumber.json");

                Configuration configuration = new Configuration(reportOutputDirectory, "focal-test-automation");
                configuration.setBuildNumber("1");

                ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
                reportBuilder.generateReports();
        }
}
