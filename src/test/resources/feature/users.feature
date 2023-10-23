Feature: Users data retrieving

  Scenario: Get users
    When get all users
    Then users not empty

  Scenario: Post users
    When post user
    Then user is created

  Scenario: Put user
    Given post user
    When put user
    Then user data is updated

  Scenario: Delete user
    Given post user
    When delete user
    And get all users
    Then user does not exists
