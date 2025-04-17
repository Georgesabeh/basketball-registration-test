Feature: Basketball England Registration

  As a new user
  I want to register on the Basketball England website
  So that I can become a supporter

  Scenario Outline: Register new user with various inputs
    Given the user is on the registration page
    When the user enters first name "<firstname>"
    And the user enters last name "<lastname>"
    And the user enters email "<email>"
    And the user enters confirm email "<confirmemail>"
    And the user enters password "<password>"
    And the user enters confirm password "<confirmpassword>"
    And the user selects date of birth "<dob>"
    And the user accepts terms "<terms>"
    And the user clicks the register button
    Then the user should see "<message>"

    Examples:
      | firstname | lastname | email                    | confirmemail           | password      | confirmpassword | dob        | terms | message                        |
      | George    | Sabeh    | georgegasp123+A@gmail.com  | georgegasp123+A@gmail.com| georgetest123 | georgetest123   | 11/09/2005 | true  | Thank you for registering     |
      | George    |          | georgegasp123@gmail.com  | georgegasp123@gmail.com| georgetest123 | georgetest123   | 11/09/2005 | true  | Please enter your last name   |
      | George    | Sabeh    | georgegasp123@gmail.com  | georgegasp123@gmail.com| georgetest123 | wrongpass       | 11/09/2005 | true  | Passwords must match          |
      | George    | Sabeh    | georgegasp123@gmail.com  | georgegasp123@gmail.com| georgetest123 | georgetest123   | 11/09/2005 | false | You must accept the terms     |
