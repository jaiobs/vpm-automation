#Author: maithili.s
Feature: Chat room with default controls
  I want to create a chat room

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Chat room with default controls
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator check if all the default controls are enabled
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner check if all the joiner default controls are enabled
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator verifies if the media controls works
    Then the joiner verify if the media controls works
    Then the creator terminates the session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds

  @Android @IOS @Android-IOS @IOS-Android
  Scenario: Chat room with default controls on mobile
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator check if all the default controls are enabled on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner check if all the joiner default controls are enabled on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
   # Then the creator verifies if the media controls works on mobile
   # Then the joiner verify if the media controls works on mobile
    Then the creator terminates the session on mobile
   # Then the creator verifies if the video info displays in the feeds on mobile
   # Then the joiner verify if the joined video info displays in the feeds on mobile

  @Chrome-Android @Chrome-IOS
  Scenario: Chat room with default controls on Web-Mobile
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator check if all the default controls are enabled
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner check if all the joiner default controls are enabled on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator verifies if the media controls works
    Then the joiner verify if the media controls works on mobile
    Then the creator terminates the session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds on mobile

  @IOS-Chrome @Android-Chrome
  Scenario: Chat room with default controls on Mobile-Web
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator check if all the default controls are enabled on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner check if all the joiner default controls are enabled
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator verifies if the media controls works on mobile
    Then the joiner verify if the media controls works
    Then the creator terminates the session on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds
