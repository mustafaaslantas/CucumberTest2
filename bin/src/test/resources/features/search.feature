
  Feature: Search test
    Scenario: Search for a book and verify the results

      Given Go to home page
      When Search for Orhan Pamuk
      Then The results must be relevant to Orhan Pamuk