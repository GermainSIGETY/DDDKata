Feature: When user update a Todo, Todo is saved

  Background:
    Given a Todo with title "Go to the greengrocer" and description "Buy some pickles"

  Scenario: user read Todo
    When User read previously created Todo
    Then title is "Go to the greengrocer" and description is "Buy some pickles"