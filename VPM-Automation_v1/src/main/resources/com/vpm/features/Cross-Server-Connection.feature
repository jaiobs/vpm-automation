#Author: maithili.s
Feature: Cross Server connection check
  I want to validate the failure of cross server connection

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario: Cross Server connection check
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    When the creator selects the "SL" server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    When the joiner selects the "WebRTC" server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner enter the created room name and start
    Then the joiner should not have entered into the created room

#TBC this scenario needed on mobile
  @Android @IOS @Android-IOS
  Scenario: Cross Server connection check on mobile
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    When the creator selects the "SL" server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    When the joiner selects the "WebRTC" server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should not have entered into the created room on mobile
