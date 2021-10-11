@UI @FULL @DEMOQA
Feature: DemoQA UI tests
  some info here

Background:
  Given the browser is at the main page


  Scenario: Simple form complete
    Given the practice form is shown
    #This will be hardcoded instead of a table.
    When the user completes the data correctly
    Then a success message is shown and contains my user


  Scenario: Simple form, wrong email
      Given the practice form is shown
      When the user completes the data with wrong email
      Then a message is shown

  Scenario: Simple form, special characters
      Given the practice form is shown
      When the user completes the data with special characters
      Then a message is shown



