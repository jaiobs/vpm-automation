#Author: maithili.s
Feature: Video player functionalities
  Functionalities of a video player

  @Android @IOS
  Scenario: Video player lists as per settings on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load from vpm/videos" from settings on mobile
    Then the videos should display only the "local" videos on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load HTTP from Json" from settings on mobile
    Then the videos should display only the "HTTP" videos on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load RTSP from Json" from settings on mobile
    Then the videos should display only the "RTSP" videos on mobile

  @Android @IOS
  Scenario: Video player for a Local file on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load from vpm/videos" from settings on mobile
    When the user clicks on the video named "foreman_assest.mp4" on mobile
    Then the user should be displayed with the video "foreman_assest.mp4" playing on mobile
    Then the user verifies if the video plays on clicking the play button on mobile
    When the user clicks on Forward 10 seconds on the video on mobile
    Then the user verifies if the video is forwarded 10 seconds on mobile
    When the user clicks on Backward 10 seconds on the video on mobile
    Then the user verifies if the video is pushed backward 10 seconds on mobile
    Then the user verifies if the video info for "foreman_assest.mp4" displays on mobile
    Then the user verifies if the video play back returns to ready state at the end of the video "foreman_assest.mp4" on mobile

  @Android @IOS
  Scenario: Video player to play RTSP file on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load RTSP from Json" from settings on mobile
    When the user clicks on the video named "RTSP Logos TV" on mobile
    Then the user should be displayed with the video "RTSP Logos TV" playing on mobile
    Then the user verifies if the video plays on clicking the play button on mobile
    When the user clicks on Forward 10 seconds on the video on mobile
    Then the user verifies if the video is forwarded 10 seconds on mobile
    When the user clicks on Backward 10 seconds on the video on mobile
    Then the user verifies if the video is pushed backward 10 seconds on mobile
    Then the user verifies if the video info for "RTSP Logos TV" displays on mobile
    Then the user verifies if the video play back returns to ready state at the end of the video "RTSP Logos TV" on mobile

  @Android @IOS
  Scenario: Video player to play HTTPS file on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load HTTP from Json" from settings on mobile
    When the user clicks on the video named "HTTP Elephants Dream" on mobile
    Then the user should be displayed with the video "HTTP Elephants Dream" playing on mobile
    Then the user verifies if the video plays on clicking the play button on mobile
    When the user clicks on Forward 10 seconds on the video on mobile
    Then the user verifies if the video is forwarded 10 seconds on mobile
    When the user clicks on Backward 10 seconds on the video on mobile
    Then the user verifies if the video is pushed backward 10 seconds on mobile
    Then the user verifies if the video info for "HTTP Elephants Dream" displays on mobile
    Then the user verifies if the video play back returns to ready state at the end of the video "HTTP Elephants Dream" on mobile

  @Android @IOS
  Scenario: Video player to play HTTP file on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load HTTP from Json" from settings on mobile
    When the user clicks on the video named "HTTP Sample Small" on mobile
    Then the user should be displayed with the video "HTTP Sample Small" playing on mobile
    Then the user verifies if the video plays on clicking the play button on mobile
    When the user clicks on Forward 10 seconds on the video on mobile
    Then the user verifies if the video is forwarded 10 seconds on mobile
    When the user clicks on Backward 10 seconds on the video on mobile
    Then the user verifies if the video is pushed backward 10 seconds on mobile
    Then the user verifies if the video info for "HTTP Sample Small" displays on mobile
    Then the user verifies if the video play back returns to ready state at the end of the video "HTTP Sample Small" on mobile

  @Android @IOS
  Scenario: Video player to play HLS file on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "Load HTTP from Json" from settings on mobile
    When the user clicks on the video named "HLS HTTPS Apple 4x3 30 min" on mobile
    Then the user should be displayed with the video "HLS HTTPS Apple 4x3 30 min" playing on mobile
    Then the user verifies if the video plays on clicking the play button on mobile
    When the user clicks on Forward 10 seconds on the video on mobile
    Then the user verifies if the video is forwarded 10 seconds on mobile
    When the user clicks on Backward 10 seconds on the video on mobile
    Then the user verifies if the video is pushed backward 10 seconds on mobile
    Then the user verifies if the video info for "HLS HTTPS Apple 4x3 30 min" displays on mobile
    Then the user verifies if the video play back returns to ready state at the end of the video "HLS HTTPS Apple 4x3 30 min" on mobile

  @Android @IOS
  Scenario Outline: Video player for invalid URL files on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "All" from settings on mobile
    When the user plays the video File "RTSPS to No Where"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "RTSP to No Where"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "RTSP invalid with valid server"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "HTTPS to No Where"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "HTTP to No Where"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "HTTPS invalid with valid server"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "HLS to No Where"
    Then the user should be displayed with the invalid url message
    When the user plays the video File "HLS invalid with valid server"
    Then the user should be displayed with the invalid url message

  @Android @IOS
  Scenario Outline: Video player for Audio/Video only files on mobile
    Given the user is on vpm landing page on mobile
    When the user navigates to Settings page on mobile
    When the user selects "All" from settings on mobile
    When the user plays the video File "HLS Apple AAC Audio Only"
    Then the user should be displayed with only the audio of the file
    When the user plays the video File "HTTPS SmashedLabs Video Only"
    Then the user should be displayed with only the video of the file
    When the user plays the video File "HTTPS SmashedLabs 1935 Audio Only"
    Then the user should be displayed with only the audio of the file
    When the user plays the video File "RTSP Video Only MP4"
    Then the user should be displayed with only the video of the file
    When the user plays the video File "HTTP SmashedLabs Video Only"
    Then the user should be displayed with only the video of the file
    When the user plays the video File "HTTP SmashedLabs 1935 Audio Only"
    Then the user should be displayed with only the audio of the file
