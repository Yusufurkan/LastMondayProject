@manageAlerts  @BRIT-2311
Feature: Manager should able to manage alerts.

  Background: Login and go to lunch page
    Given using valid credential sign in as a manager and click lunch
    And go to "Alerts"

  @BRIT-2705
  Scenario: Manager should able to delete alerts
    And click on create and fill the required fields
    And go to "Alerts"
    Then Get the size of alert
    And click on "delete" in filters
    Then Verify alert size decreased by one


    #TODO fix the delete and unarchive there is a null pointer exception

  @BRIT-2717
  Scenario: Verify manager can archive any Alert/Alerts
    Then click on the magnet on the search button
    And click on filter
    And click on "Archived" in filters
    Then get the size of alerts before action
    And go to "Alerts"
    Then select the last alert
    And "Archive" the selected Alert
    And click on filter
    And click on "archived" in filters
    Then get the size of alerts after action
    Then verify the Alert size is increased by one


  @BRIT-2731
  Scenario: Verify manager can unarchive any Alert/Alerts
    Then get the size of alerts before action
    Then click on the magnet on the search button
    And click on filter
    And click on "Archived" in filters
    Then select the last alert
    And "unarchive" the selected Alert
    And go to "Alerts"
    Then get the size of alerts after action
    Then verify the Alert size is increased by one


  @BRIT-2311
  Scenario: Manager should be able to manage alerts(ALERTS)
    Then select the last alert
    Then get the size of alerts before action
    And "delete" the selected Alert
    Then get the size of alerts after action
    Then verify the Alert size is decreased by one










