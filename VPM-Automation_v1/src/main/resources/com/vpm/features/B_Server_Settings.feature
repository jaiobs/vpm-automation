#Author: maithilis
Feature: Validations of Server Settings
  Server settings validations

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Validations of Server Settings
    Given the user is on vpm landing page
    When the user navigates to Settings page
    And the user selects "No" in the Use SL Server option
    Then the user verifies if the server ip address box is "disabled"
    When the user selects "Yes" in the Use SL Server option
    Then the user verifies if the server ip address box is "enabled"

  @Android @IOS
  Scenario: Validations of Server Settings on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    Then the user verifies the server ip address box on mobile