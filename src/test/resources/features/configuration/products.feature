Feature: Create a product in Lunch function


  Background: Login and go to lunch page
    Given using valid credential sign in as a manager and click lunch
    And go to "alerts"


  Scenario Outline: Create a product
    Then click on create
    Then enter product name "<name>"
    Then enter product category "<category>"
    Then enter product vendor "<vendor>"
    Then enter product price <price>
    And click save

    Examples:
      | name    | category      | vendor                                   | price |
      | Lo Mei  | Pizza         | 1234 westside parkway, atlanta, georgia. | 14.15 |
      | Rib Eye | Mediterranean | Aiman                                    | 25.35 |
      | Doner   | Mediterranean | Aiman                                    | 19.15 |
      | Manti   | Mediterranean | ACD, Alex                                | 30.99 |

  @outlineDataBase
  Scenario Outline: Verify manager can create an Alert for 'Every Day'
    Then get the size of alerts before action
    Then click on create
    Then select recurrence "<Recurrence>"
    Then enter between "<between>" and end "<end>"
    Then enter a date "<date>"
    Then enter a message "<message>"
    And click save
    Then go to "Alerts"
    Then get the size of alerts after action
    And verify the Alert size is increased by one
    And verify information exist in database "<message>"

    Examples:
      | Recurrence   | between | end   | date       | message            |
      | Every Day    | 05.00   | 11.55 |            | Error date time    |
      | Specific Day | 11.00   | 11.55 | 03/16/2019 | This is message  2 |
      | Every Day    | 11.00   | 13.45 |            | This is message  3 |
      | Specific Day | 10.00   | 14.55 | 08/18/2019 | This is message  4 |
      | Every Day    | 09.00   | 23.30 |            | This is message  5 |
      | Specific Day | 08.00   | 21.20 | 04/20/2019 | This is message 6  |
      | Every Day    | 07.00   | 20.40 |            | This is message 7  |


  Scenario: Verify manager can create an Alert for 'Every Day'
    Then get the size of alerts before action
    Then click on create
    Then enter required spaces from excel
      | recurrence | between | end | date | message |
    And click save
    Then go to "Alerts"
    Then get the size of alerts after action
    And verify the Alert size is increased by one
    And verify information exist in database "<message>"


  @excelDatabase
  Scenario: Verify manager can create an Alert for 'Every Day'
    Then get the size of alerts before action
    Then click on create
    Then enter required spaces from excela
