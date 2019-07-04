Feature: Insurance Plan API

  Background:
    Given that the database is empty

  Scenario: Create an insurance plan using the API
    When the "create insurance plan" API is called with:
      | method | POST                                 |
      | body   | create-simple-insurance-plan-request |
    Then the "create insurance plan" API response has:
      | status | 201                                   |
      | body   | create-simple-insurance-plan-response |
    And the "insurance_plans" table contains 1 rows
    And the "insurance_plans" table contains the following rows:
      | name          | cost_rate |
      | Plano Simples | 0.05      |

  Scenario: Get an existent insurance plan by ID using the API
    Given the "insurance_plans" table has the following rows:
      | id        | name          | cost_rate |
      | id-simple | Plano Simples | 0.05      |
    And the "insurance_coverage_items" table has the following rows:
      | id        | name   | description                            |
      | id-zombie | zombie | Protecao contra apocalipse zumbi       |
      | id-quake  | quake  | Protecao contra terremotos e maremotos |
    And the "insurance_plans_insurance_coverage_items" table has the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-simple         | id-zombie                  |
      | id-simple         | id-quake                   |
    When the "get insurance plan by id" API is called with:
      | method    | GET           |
      | pathParam | id: id-simple |
    Then the "get insurance plan by id" API response has:
      | status | 200                               |
      | body   | get-insurance-plan-by-id-response |

  Scenario: Get all the insurance plans using the API
    Given the "insurance_plans" table has the following rows:
      | id         | name           | cost_rate |
      | id-simple  | Plano Simples  | 0.05      |
      | id-complex | Plano Complexo | 0.08      |
    And the "insurance_coverage_items" table has the following rows:
      | id        | name   | description                            |
      | id-zombie | zombie | Protecao contra apocalipse zumbi       |
      | id-quake  | quake  | Protecao contra terremotos e maremotos |
    And the "insurance_plans_insurance_coverage_items" table has the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-complex        | id-zombie                  |
      | id-complex        | id-quake                   |
      | id-simple         | id-quake                   |
    When the "get all insurance plans" API is called with:
      | method | GET |
    Then the "get all insurance plans" API response has:
      | status | 200                              |
      | body   | get-all-insurance-plans-response |

  Scenario: Add coverage item to insurance plan using the API
    Given the "insurance_plans" table has the following rows:
      | id        | name          | cost_rate |
      | id-simple | Plano Simples | 0.05      |
    And the "insurance_coverage_items" table has the following rows:
      | id        | name   | description                            |
      | id-zombie | zombie | Protecao contra apocalipse zumbi       |
      | id-quake  | quake  | Protecao contra terremotos e maremotos |
    And the "insurance_plans_insurance_coverage_items" table has the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-simple         | id-quake                   |
    When the "add coverage item to plan" API is called with:
      | method    | POST                      |
      | pathParam | planId: id-simple         |
      | pathParam | coverageItemId: id-zombie |
    Then the "add coverage item to plan" API response has:
      | status | 200 |
    And the "insurance_plans_insurance_coverage_items" table contains 2 rows
    And the "insurance_plans_insurance_coverage_items" table contains the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-simple         | id-quake                   |
      | id-simple         | id-zombie                  |

  Scenario: Remove coverage item from insurance plan using the API
    Given the "insurance_plans" table has the following rows:
      | id        | name          | cost_rate |
      | id-simple | Plano Simples | 0.05      |
    And the "insurance_coverage_items" table has the following rows:
      | id        | name   | description                            |
      | id-zombie | zombie | Protecao contra apocalipse zumbi       |
      | id-quake  | quake  | Protecao contra terremotos e maremotos |
    And the "insurance_plans_insurance_coverage_items" table has the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-simple         | id-zombie                  |
      | id-simple         | id-quake                   |
    When the "remove coverage item from plan" API is called with:
      | method    | DELETE                    |
      | pathParam | planId: id-simple         |
      | pathParam | coverageItemId: id-zombie |
    Then the "remove coverage item from plan" API response has:
      | status | 200 |
    And the "insurance_plans_insurance_coverage_items" table contains 1 rows
    And the "insurance_plans_insurance_coverage_items" table contains the following rows:
      | insurance_plan_id | insurance_coverage_item_id |
      | id-simple         | id-quake                   |