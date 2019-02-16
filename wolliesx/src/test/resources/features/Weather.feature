Feature: Holiday Maker

  Scenario: A happy holidaymaker
    Given I like to holiday in "Sydney"
    And   I only like to holiday on "Saturday"
    When  I look up and receive the weather forecast
    And   the temperature is warmer than '10' degrees
