#Author: maithilis

Feature: Validations of Server settings
  Server settings validations

  Scenario: Validations of Server Settings
    Given the user is on vpm landing page
    When the user navigates to Settings page
    And the user selects "No" in the Use SL Server option
    Then the user verifies if the server ip address box is "disabled"
    When the user selects "Yes" in the Use SL Server option
    Then the user verifies if the server ip address box is "enabled"