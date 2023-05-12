@action-tool @JIRA:AT-01
Feature: Login and Password reset functionality
  # When it comes to end2end, we have limitations here, basically being stuck around login page without
  # a real account, so the only semi-proper end2end I would consider is the following password reset flow,
  # which uses some custom Yahoo mailbox functionality. Description under the test.
  # I added one more end2end-like test which will test different basic functionality on Home, Login
  # and PasswordReset pages.
  #
  # However to do more than just this, I'm including few additional tests that could be included in regression suite.
  # I'll use tag @functional on them so that you can separately run end-to-end tests or the rest

  @done @end-to-end
  Scenario: User is able to perform password reset flow
    Given I am on the homepage
        And I enter my Realm username or email "uiframework108108@yahoo.com"
        And I click the continue button
        And I am on the login page
        And I click the forgot password link
        And I am on the password reset page
        And I enter my email for password reset "uiframework108108@yahoo.com"
      When I click the Send link to email button
      Then I see the Email sent confirmation message
        And I open Yahoo Mail in Chrome browser
        And I enter my Yahoo email "uiframework108108@yahoo.com"
        And I confirm it by clicking Next button
        And I enter my Yahoo password "Helloworld123!"
        And I confirm it by clicking Next button
      # Custom wait here and then clicking on the mailbox folder to refresh e-mails. production wise I'd make it more elegant
        And I wait for 5 seconds
        And I click on the mailbox folder
        And I click on the email with subject "Reset my password - Focal Action Tool"
      When I click on the link in the email body
      Then I am on the homepage
  # I should be obviously redirected to a page where I could reset the password but since
  # I don't have a real account and this continuation with mailbox is just to demonstrate possibilities.
  # After I click on the fake, prepared e-mail, it redirects back to the Action Tool's homepage.
  # When it comes to real tests, I would most likely aggregate those e-mail (Yahoo) related steps into just one step as we
  # do not care about details of some external service. Just open the latest reset e-mail you waited for, click
  # the link and validate what comes next. Also most likely something else than Yahoo, but it was pleasant to
  # use for the sake of this demonstration. Right now also those e-mail related steps are generic-sentence looking which would
  # start to be problematic when more steps are added for the framework, so this one-step-handle-mailbox would also solve this.
  # Feel free to access Yahoo mailbox manually: uiframework108108@yahoo.com / Helloworld123!


  @done @end-to-end
  Scenario: User checks Remember me, Change username and confirm button functionality on the Home and Login pages
    Given I am on the homepage
      And the continue button is disabled
      And the checkbox on the homepage is not selected
      And I enter my Realm username or email "uiframework108108@yahoo.com"
    When I click the continue button
    Then I am on the login page
      And the login button is disabled
      And the checkbox on the homepage is not selected
      And the email field contains "uiframework108108@yahoo.com"
    When I click the change username link
    Then I am on the homepage
      And the continue button is disabled
      And the checkbox on the homepage is not selected
    When I click on the Remember Me checkbox on the homepage
    Then the checkbox on the homepage is selected
      And I enter my Realm username or email "uiframework108108@yahoo.com"
    When I click the continue button
    Then I am on the login page
      And the email field contains "uiframework108108@yahoo.com"
    When I enter my password "whatever"
    Then the password is not visible
    When I click the show password button
    Then the password should be visible


  @done @functional
  Scenario: Login is not possible with incorrect username or password
    Given I am on the homepage
      And I enter my Realm username or email "uiframework108108@yahoo.com"
      And I click the continue button
      And I am on the login page
    When I enter my password "incorrectPassword"
      And I click the login button
    Then the error message on Login page should be "Incorrect username/password. Please try again!"


  @done @functional
  Scenario: [KNOWN ISSUE] User is unable to use Send Link to Email with incorrect e-mail
    Given I am on the homepage
      And I enter my Realm username or email "uiframework108108@yahoo.com"
      And I click the continue button
      And I am on the login page
      And I click the forgot password link
      And I am on the password reset page
    When I enter my email for password reset "adminok?"
      And I click the Send link to email button
    Then the error message on Password page should be "Wrong argument (email) value."
