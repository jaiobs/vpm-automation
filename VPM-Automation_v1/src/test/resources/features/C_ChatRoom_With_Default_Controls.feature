#Author: maithili.s
#Keywords Summary :
#Feature: List of scenarios.
Feature: Chat room with default controls
  I want to create a chat room

  @ChatRoom_With_Default_Controls
  Scenario: Chat room creation with default controls
    Given the user is on vpm landing page
    When the creator enter into the create room form
    Then the creator check if all the default controls are enabled
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner enter into the join room form
    Then the joiner check if all the joiner default controls are enabled
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator verifies if the media controls works
    Then the joiner verify if the media controls works
    Then the creator terminates the session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds