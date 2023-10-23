Feature: Users data retrieving

  Scenario: Get users
    Given debug
    When get all users
    Then users not empty

  Scenario: Post users
    Given debug
    When post user
    Then user is created

  Scenario: Put user
    Given debug
    Given post user
    When put user
    Then user data is updated

  Scenario: Delete user
    Given debug
    Given post user
    When delete user
    And get all users
    Then user does not exists
