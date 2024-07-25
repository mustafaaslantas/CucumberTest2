
  Feature: Cart test

    Scenario: Add random book to the cart and verify

      Given Go to homepage
      When Navigate to literature category
      And Add a random book to the cart
      Then The book in the cart must match the selected book
      And Remove the item from the cart
      Then The cart must be empty