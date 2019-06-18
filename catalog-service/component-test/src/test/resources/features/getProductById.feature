Feature: Get Product By Id

  Scenario: Getting an existent product by id
    When I call the GetProductById API with params:
      | id | 13421 |
    Then the GetProductById API response status should be 200
    And the GetProductById API response should have a body equals to "id-13421"
