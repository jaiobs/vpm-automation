#Author: maithilis
Feature: Suspending a session

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Creator suspends a session
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
    When the creator clicks back from the session
    Then the creator clicks "cancel" on the session exit popup
    Then the creator should stay back in the session
    When the creator clicks back from the session
    Then the creator clicks "close" on the session exit popup
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds

  @web
  Scenario: Joiner suspends a session
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
    When the joiner clicks to go back to the live session
    Then the joiner should have entered into the created room

  @web
  Scenario: Warning On Active Session Close
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
    When the creator clicks on the "Feeds" menu
    Then the creator clicks "cancel" on the session exit popup
    Then the creator should stay back in the session
    When the creator clicks back from the session
    Then the creator clicks "close" on the session exit popup
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds

  @Android @IOS @Android-IOS @IOS-Android
  Scenario: Creator suspends a session on mobile
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
    When the creator clicks back from the session on mobile
    Then the creator clicks "cancel" on the session exit popup on mobile
    Then the creator should stay back in the session on mobile
    When the creator clicks back from the session on mobile
    Then the creator clicks "close" on the session exit popup on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds on mobile

  @Android @IOS @Android-IOS @IOS-Android
  Scenario: Joiner suspends a session on mobile
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
    When the joiner clicks back from the session on mobile
    Then the joiner should be displayed with the Live label in the feeds on mobile
    When the joiner clicks to go back to the live session on mobile
    Then the joiner should have entered into the created room on mobile

  @Chrome-Android @Chrome-IOS
  Scenario: Creator suspends a session on Web-Mobile
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
    When the creator clicks back from the session
    Then the creator clicks "cancel" on the session exit popup
    Then the creator should stay back in the session
    When the creator clicks back from the session
    Then the creator clicks "close" on the session exit popup
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds on mobile

  @Chrome-Android @Chrome-IOS
  Scenario: Joiner suspends a session on Web-Mobile
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
    When the joiner clicks back from the session on mobile
    Then the joiner should be displayed with the Live label in the feeds on mobile
    When the joiner clicks to go back to the live session on mobile
    Then the joiner should have entered into the created room on mobile

  @Chrome-Android @Chrome-IOS
  Scenario: Warning On Active Session Close on mobile
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
    When the creator clicks on the "Feeds" menu
    Then the creator clicks "cancel" on the session exit popup
    Then the creator should stay back in the session
    When the creator clicks back from the session
    Then the creator clicks "close" on the session exit popup
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds on mobile

  @IOS-Chrome @Android-Chrome
  Scenario: Creator suspends a session on Mobile-Web
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
    When the creator clicks back from the session on mobile
    Then the creator clicks "cancel" on the session exit popup on mobile
    Then the creator should stay back in the session on mobile
    When the creator clicks back from the session on mobile
    Then the creator clicks "close" on the session exit popup on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds

  @IOS-Chrome @Android-Chrome
  Scenario: Joiner suspends a session on Mobile-Web
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
    When the joiner clicks back from the session
    Then the joiner should be displayed with the Live label in the feeds
    When the joiner clicks to go back to the live session
    Then the joiner should have entered into the created room
