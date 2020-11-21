#Author: maithilis
Feature: Ending A Call feature

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Ending A Call feature
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
    Then the joiner terminates the joined session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds

  @Android @IOS @Android-IOS @IOS-Android
  Scenario: Ending A Call feature on mobile
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
    Then the joiner terminates the joined session on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds on mobile

  @Chrome-Android @Chrome-IOS
  Scenario: Ending A Call feature on Web-Mobile
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the joiner terminates the joined session on mobile
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds on mobile
    
    @IOS-Chrome @Android-Chrome
    Scenario: Ending A Call feature on Mobile-Web
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the joiner terminates the joined session
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds
