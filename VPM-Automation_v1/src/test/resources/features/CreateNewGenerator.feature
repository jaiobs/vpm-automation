#Author: maithili.s@optisol.com

Feature: Create New Generator
  Creating a new generator under Generators Master

  Scenario: Creating New Generator
    When I open AX D365
  	And I sign in
  	Then I navigate to "customer service management"  	
  	Then I select "All Generators"
  	Then I click on New to create a new generator
  	Then I fill all the mandatory fields and save generator