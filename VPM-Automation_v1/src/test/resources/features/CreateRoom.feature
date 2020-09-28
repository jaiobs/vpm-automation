#Author: maithili.s
#Keywords Summary :
#Feature: List of scenarios.

Feature: Creating a chat room
  I want to create a chat room
  
Background:
	Given I am on vpm landing page
    
  Scenario: Chat room creation
    When I enter into the create room form and check if all the default controls are enabled
    Then I enter the new room name and start
    Then I should have entered into the new room started
    Then the user verifies if the video cam works