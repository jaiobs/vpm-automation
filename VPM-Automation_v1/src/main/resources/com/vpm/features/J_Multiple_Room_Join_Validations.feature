#Author: maithilis
Feature: Create 5 sequential chat sessions
  I want to create 5 chat sessions completing one by one

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
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
    
     @Android @IOS
  Scenario: Create 5 sequential chat sessions on mobile
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator terminates the session on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator terminates the session on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator terminates the session on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator terminates the session on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator terminates the session on mobile
    Then the creator is displayed with "5" session feeds on the history on mobile
    Then the joiner is displayed with "5" session feeds on the history on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    When the joiner clicks back from the session on mobile
    Then the joiner should be displayed with the live session at the top of the list on mobile
