@PersonalSettings
Feature: Check if the personal settings working correctly
  I want to check whether the personal settings that user use to update is working properly

  @UpdateCheck
  Scenario: Trying to update personal settings using valid test case
    Given I was in the home page
    When I navigate to personal settings
    #And I fill valid "<FirstName>", "<LastName>", "<Email>", "<Fax>", "<ProfileName>", "<PublicWishlist>", "<NewsletterSub>", "<TransMailSub>", "<PromoMailSub>", "<CountryCode>", "<MobileNumber>" credentials and update
    And I fill credentials and update
    Then the details should be updated