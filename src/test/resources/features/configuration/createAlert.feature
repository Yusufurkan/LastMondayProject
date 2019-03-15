@BRIT-2303 @createAlert
Feature: Manager should be able to create alerts(ALERTS)


  Background: Login and go to lunch page
    Given using valid credential sign in as a manager and click lunch
    And go to "Alerts"

  @BRIT-2737
  Scenario: Verify that manager can create an alert with the 'specific day'
    Then Get the size of alert
    Then click on create and fill the required fields
    And go to "Alerts"
    Then get the size of alerts after action
    Then verify the Alert size is increased by one


  @BRIT-2746
  Scenario: Verify manager can't create an 'Alert' without the message
    Then click on create
    Then select recurrence "Specific Day"
    Then enter a date
    Then enter between and end
    And click save
    Then verify error message is displayed


  @BRIT-2776
  Scenario: Verify manager can discard 'Alert'
    Then get the size of alerts before action
    Then click on create
    Then select recurrence "Specific Day"
    Then enter a date
    Then enter between and end
    Then enter a message
    And click on discard
    Then click okay in pop up
    And go to "Alerts"
    Then get the size of alerts after action
    Then verify the size is same

  @BRIT-2765
  Scenario: Verify manager can create an Alert for 'Every Day'
    Then get the size of alerts before action
    Then click on create
    Then select recurrence "Every Day"
    Then enter between and end
    Then enter a message
    And click save
    Then go to "Alerts"
    Then get the size of alerts after action
    And verify the Alert size is increased by one

  @BRIT-2757 @wip
  Scenario: Verify manager can create an Alert for specific day/days of the week
    Then get the size of alerts before action
    Then click on create
    Then select recurrence "Every Week"
    Then enter between and end
    Then enter a message
    And click save
    Then go to "Alerts"
    Then get the size of alerts after action
    And verify the Alert size is increased by one















