Feature: Ebay user

  Scenario: I want to be able to add 2 items to the cart/trolley and go to checkout
    Given I am on the website with URL "https://www.ebay.com.au/"
    When "ebaytestuser11@gmail.com" logs into his account
    And when that user goes to "MyEbay" page
    Then adds '3' items in  basket and checkout
    And Removes the items from the cart




