# focal-test-automation

Howto run tests:
git clone https://github.com/uiframework108/focal-test-automation.git
cd focal-test-automation
mvn clean install -DskipTests

For the tests there are 3 options:
mvn test // this will run all tests with @done tag - in our case all tests
mvn test -Dcucumber.options="--tags @end-to-end" // this will run all tests with @end-to-end && @done tags - all e2e tests
mvn test -Dcucumber.options="--tags @functional" // this will run all tests with @functional && @done tags - all functional tests

Current expected result is 3 tests will pass, one will fail because of an issue reported as a bug, for the sake of this, it is expected.
After the test run is finished, focal-test-automation/reports folder contains screenshot capturing failure during test run, 
focal-test-automation/target/cucumber-report/cucumber-html-reports/report-feature_*.html is a HTML detailed report of a test run, rn being overwritten each run.

Focal Action Tool Bugs are reported here: https://github.com/uiframework108/focal-test-automation/issues

TODO:
Framework:
I'd start adding some inheritance when it comes to error messages and elements that are shared between different Pages, such as checkboxes.
I'd need to play with this Cucumber implementation since for some reason multiple choice steps were not being recognized and I wasted a bit of time 
fighting with it because I remember I had the same problem in Ruby Cucumber and we are using it there. Those steps like some element 
is displayed/not would be transformed first to make the code cleaner.
Dockerize it, prepare a pipeline. Please let me know if you prefer to have it in a docker, I can try to fight those compatibility chrome driver issues on Monday, maybe changing it to firefox would quickly solve it.
Also tackle Maven resource or Ant plugins as I couldn't count on proper moving of test reports to the main folder.
Add Lombok and setup some additional logger to help debugging potential problems.

Tests:
With a proper access to the service there would be many end to end tests added together with smaller functional positive 
and negative scenarios based on test plan in the PI.
Add more assertions in the current flows.
