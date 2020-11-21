#Author: maithilis
Feature: 5 Chat sessions sequentially created
  I want to create 5 chat sessions completing one by one

  Scenario: Create 5 sequential chat sessions
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
    Then the creator terminates the session
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator terminates the session
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator terminates the session
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator terminates the session
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator terminates the session
    Then the creator is displayed with "5" session feeds on the history
    Then the joiner is displayed with "5" session feeds on the history
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    When the joiner clicks back from the session
    Then the joiner should be displayed with the live session at the top of the list
