#Author: maithilis
Feature: ZoomIn_ZoomOut feature
  I want to use this template for my feature file

  Scenario: Title of your scenario
    Given the user is on vpm landing page
     When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator select the controls "Yes" and "Yes" and "Yes" and "Yes"
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner select the controls "Yes" and "Yes" and "Yes" and "Yes"
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
		When the creator zoom "in" the video
		Then the creator verifies if the video zoom "in"
		When the creator zoom "out" the video
		Then the creator verifies if the video zoom "out"
		When the creator zoom "in" the video
		When the creator zoom "reset" the video
		Then the creator verifies if the video zoom "reset"