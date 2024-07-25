
  Feature: Login test

    Scenario: Invalid login

      Given Go to login page
      When Enter invalid email and password
      Then An error message must be displayed

    Scenario: Successful login

      Given Go to login page
      When Enter valid email and password
      Then Verify the login was succesful