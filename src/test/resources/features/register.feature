Feature: User registration on Basketball England

  Scenario Outline: Register user with different inputs
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
    Then the user should see "<expectedMessage>"

    Examples:
      | firstname | lastname | email                   | confirmemail            | password      | confirmpassword | dob        | terms | expectedMessage                    |
      | George    | Sabeh    | georgegasp123@gmail.com | georgegasp123@gmail.com | georgetest123 | georgetest123   | 11/09/2005 | true  | check your email                  |
      | George    |          | georgegasp123@gmail.com | georgegasp123@gmail.com | georgetest123 | georgetest123   | 11/09/2005 | true  | this field is required            |
      | George    | Sabeh    | georgegasp123@gmail.com | georgegasp123@gmail.com | test123       | test321         | 11/09/2005 | true  | password confirmation must match |
      | George    | Sabeh    | georgegasp123@gmail.com | georgegasp123@gmail.com | georgetest123 | georgetest123   | 11/09/2005 | false | please accept the terms          |
