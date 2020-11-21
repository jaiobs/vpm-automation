#Author: maithilis
Feature: Joiner with multiple live sessions

#Scenario Name Change will have impact on Hooks initiating drivers
  Scenario: Joiner switching between multiple live sessions
    Given the user is on vpm landing page
     When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option   
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    When the joiner clicks back from the session
    Then the joiner should be displayed with the Live label in the feeds
    When the creator creates a new session
    Then the joiner enters into the second new session created
    When the joiner clicks back from the session
    Then the joiner should be displayed with Live label on 2 sessions    
    Then the joiner should be able to go into any of the live session