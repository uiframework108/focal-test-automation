# focal-test-automation

Howto run tests:
git clone https://github.com/uiframework108/focal-test-automation.git
cd <path_to>/focal-test-automation
mvn clean install -DskipTests

For the tests there are 3 options:
mvn test // this will run all tests with @done tag - in our case all tests
mvn test -Dcucumber.options="--tags @end-to-end" // this will run all tests with @end-to-end && @done tags - all e2e tests
mvn test -Dcucumber.options="--tags @functional" // this will run all tests with @functional && @done tags - all functional tests

I will try to add Dockerfile during weekend in case you don't have maven installed to make it easier.

Current expected result is 3 tests will pass, one will fail because of an issue reported as a bug, for the sake of this, it is expected.
After the test run is finished, focal-test-automation/reports folder contains screenshot capturing failure during test run, 
focal-test-automation/target/cucumber-report/cucumber-html-reports/report-feature_*.html is a report of a test run, rn being overwritten each run.

Will report bugs here in github tomorrow morning.

TODO:
Framework:
I'd start adding some inheritance when it comes to error messages and elements that are shared between different Pages, such as checkboxes.
I'd need to play with this Cucumber implementation since for some reason multiple choice steps were not being recognized and I wasted a bit of time 
fighting with it because I remember I had the same problem in Ruby Cucumber and we are using it there. Those steps like some element 
is displayed/not would be transformed first to make the code cleaner.

Performance is something to be looked at.
Tests:
Well of course I'd get a proper access to the service so many full end to end tests would be added together with smaller functional positive 
and negative scenarios based on test plan in the PI.
Add more assertions in the current flows.
