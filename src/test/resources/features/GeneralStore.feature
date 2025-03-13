Feature: General Store App Test

  Scenario: Add products to cart and verify total amount
    Given I open the General Store app
    When I select "Egypt" from the country dropdown
    And  I enter my name as "Sayed Shaaban"
    And  I select my gender as "Male"
    And  I click on the "Let's Shop" button
    And  I add two products to the cart
    And  I navigate to the cart screen
    Then I should see the products in the cart
    And the total amount should equal the sum of the two products' prices