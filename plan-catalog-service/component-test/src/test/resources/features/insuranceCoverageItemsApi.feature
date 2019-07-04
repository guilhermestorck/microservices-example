Feature: Insurance Coverage Items API

  Background:
    Given that the database is empty

  Scenario: Create an coverage item using the API
    When the "create insurance coverage item" API is called with:
      | method | POST                               |
      | body   | create-quake-coverage-item-request |
    Then the "create insurance coverage item" API response has:
      | status | 201                                 |
      | body   | create-quake-coverage-item-response |
    And the "insurance_coverage_items" table contains 1 rows
    And the "insurance_coverage_items" table contains the following rows:
      | name  | description                            |
      | quake | Protecao contra terremotos e maremotos |

  Scenario: Get an existent coverage item by ID using the API
    Given the "insurance_coverage_items" table has the following rows:
      | id       | name  | description                            |
      | id-quake | quake | Protecao contra terremotos e maremotos |
    When the "get insurance coverage item by id" API is called with:
      | method    | GET          |
      | pathParam | id: id-quake |
    Then the "get insurance coverage item by id" API response has:
      | status | 200                                        |
      | body   | get-insurance-coverage-item-by-id-response |

  Scenario: Get all the coverage items using the API
    Given the "insurance_coverage_items" table has the following rows:
      | id        | name   | description                            |
      | id-zombie | zombie | Protecao contra apocalipse zumbi       |
      | id-quake  | quake  | Protecao contra terremotos e maremotos |
    When the "get all insurance coverage items" API is called with:
      | method | GET |
    Then the "get all insurance coverage items" API response has:
      | status | 200                                       |
      | body   | get-all-insurance-coverage-items-response |
