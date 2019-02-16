Feature: PhpTravels

  Scenario: I want to be able to book a flight
    Given I am on the website with URL "https://phptravels.net"
    When Newton wants to book a Flight from "SYD" to "MELB"
    Then Gets the Invoice and confirmation

  Scenario: I want to be able to book a hotel
    Given I am on the website with URL "https://phptravels.net"
    When Newton wants to book a hotel in "Bali"
    Then Gets the Invoice and confirmation

  Scenario: I want to be able to book a tour
    Given I am on the website with URL "https://phptravels.net"
    When Newton wants to book a tour in "Sydney"
    Then Gets the Invoice and confirmation

