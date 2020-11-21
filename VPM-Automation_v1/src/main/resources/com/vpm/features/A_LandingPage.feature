#Author: maithilis
Feature: Landing page
  I want to verify the landing page menus and icons

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Landing page
    Given the user is on vpm landing page
    When the user enter into the landing page
    Then the user verifies if all the menus and the screen displays as expected

  @Android @IOS
  Scenario: Landing page on mobile
    Given the user is on vpm landing page on mobile
    When the user enter into the landing page on mobile
    Then the user on mobile verifies if all the menus and the screen displays as expected