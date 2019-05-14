Feature: When user update a Todo, Todo is saved

  Background:
    Given a Todo with title "Go to the greengrocer" and description "Buy some pickles"

  Scenario: user read Todo
    When User Change description of previously created Todo with Title "Go to the Supermarket" and description to "Buy some cucumbers"
    When User read previously created Todo
    Then title is "Go to the Supermarket" and description is "Buy some cucumbers"