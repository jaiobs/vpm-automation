#Author: maithilis
Feature: Creating a chat room with multiple combination of controls

  #Scenario Name Change will have impact on Hooks initiating drivers
  @Chrome
  Scenario Outline: Chat room creation with media control combinations LA-"<LocalAudio>" LV-"<LocalVideo>" RA-"<RemoteAudio>" RV-"<RemoteVideo>"
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>"
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>"
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
    Then the creator verifies if the media controls works
    Then the joiner verify if the media controls works
    Then the creator terminates the session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds

    Examples: 
      | LocalAudio | LocalVideo | RemoteAudio | RemoteVideo |
      | Yes        | Yes        | Yes         | Yes         |
      | Yes        | No         | No          | No          |
      | No         | Yes        | No          | No          |
      | No         | No         | Yes         | No          |
      | No         | No         | No          | Yes         |
      | No         | No         | No          | No          |
      | No         | Yes        | Yes         | Yes         |
      | Yes        | No         | Yes         | Yes         |
      | Yes        | Yes        | No          | Yes         |

  @Android @IOS @Android-IOS @IOS-Android
  Scenario Outline: Chat room creation with media control combinations on mobile LA-"<LocalAudio>" LV-"<LocalVideo>" RA-"<RemoteAudio>" RV-"<RemoteVideo>"
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>" on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>" on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator verifies if the media controls works on mobile
    Then the joiner verify if the media controls works on mobile
    Then the creator terminates the session on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds on mobile

    Examples: 
      | LocalAudio | LocalVideo | RemoteAudio | RemoteVideo |
      | Yes        | Yes        | Yes         | Yes         |
      | Yes        | No         | No          | No          |
      | No         | Yes        | No          | No          |
      | No         | No         | Yes         | No          |
      | No         | No         | No          | Yes         |
      | No         | No         | No          | No          |
      | No         | Yes        | Yes         | Yes         |
      | Yes        | No         | Yes         | Yes         |
      | Yes        | Yes        | No          | Yes         |

  @Chrome-Android @Chrome-IOS
  Scenario Outline: Chat room creation with media control combinations on Web-Mobile LA-"<LocalAudio>" LV-"<LocalVideo>" RA-"<RemoteAudio>" RV-"<RemoteVideo>"
    Given the user is on vpm landing page
    When the creator navigates to "Settings" page
    And the creator selects the server in the Use SL Server option
    When the creator navigates to "Feeds" page
    When the creator enter into the create room form
    Then the creator select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>"
    Then the creator enter the new room name and start
    Then the creator should have entered into the new room started
    Given the joiner on a vpm landing page on mobile
    When the joiner navigates to "Settings" page on mobile
    And the joiner selects the server in the Server option on mobile
    When the joiner navigates to "Feeds" page on mobile
    When the joiner enter into the join room form on mobile
    Then the joiner select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>" on mobile
    Then the joiner enter the created room name and start on mobile
    Then the joiner should have entered into the created room on mobile
    Then the creator verifies if the media controls works
    Then the joiner verify if the media controls works on mobile
    Then the creator terminates the session
    Then the creator verifies if the video info displays in the feeds
    Then the joiner verify if the joined video info displays in the feeds on mobile
    
     Examples: 
      | LocalAudio | LocalVideo | RemoteAudio | RemoteVideo |
      | Yes        | Yes        | Yes         | Yes         |
      | Yes        | No         | No          | No          |
      | No         | Yes        | No          | No          |
      | No         | No         | Yes         | No          |
      | No         | No         | No          | Yes         |
      | No         | No         | No          | No          |
      | No         | Yes        | Yes         | Yes         |
      | Yes        | No         | Yes         | Yes         |
      | Yes        | Yes        | No          | Yes         |
    

  @IOS-Chrome @Android-Chrome
  Scenario Outline: Chat room creation with media control combinations on Mobile-Web LA-"<LocalAudio>" LV-"<LocalVideo>" RA-"<RemoteAudio>" RV-"<RemoteVideo>"
    Given the user is on vpm landing page on mobile
    When the creator navigates to "Settings" page on mobile
    And the creator selects the server in the Server option on mobile
    When the creator navigates to "Feeds" page on mobile
    When the creator enter into the create room form on mobile
    Then the creator select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>" on mobile
    Then the creator enter the new room name and start on mobile
    Then the creator should have entered into the new room started on mobile
    Given the joiner on a vpm landing page
    When the joiner navigates to "Settings" page
    And the joiner selects the server in the Use SL Server option
    When the joiner navigates to "Feeds" page
    When the joiner enter into the join room form
    Then the joiner select the controls "<LocalAudio>" and "<LocalVideo>" and "<RemoteAudio>" and "<RemoteVideo>"
    Then the joiner enter the created room name and start
    Then the joiner should have entered into the created room
		Then the creator verifies if the media controls works on mobile
    Then the joiner verify if the media controls works
    Then the creator terminates the session on mobile
    Then the creator verifies if the video info displays in the feeds on mobile
    Then the joiner verify if the joined video info displays in the feeds
    
     Examples: 
      | LocalAudio | LocalVideo | RemoteAudio | RemoteVideo |
      | Yes        | Yes        | Yes         | Yes         |
      | Yes        | No         | No          | No          |
      | No         | Yes        | No          | No          |
      | No         | No         | Yes         | No          |
      | No         | No         | No          | Yes         |
      | No         | No         | No          | No          |
      | No         | Yes        | Yes         | Yes         |
      | Yes        | No         | Yes         | Yes         |
      | Yes        | Yes        | No          | Yes         |