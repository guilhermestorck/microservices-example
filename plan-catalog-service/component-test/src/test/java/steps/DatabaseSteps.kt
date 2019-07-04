package steps

import cucumber.api.DataTable
import cucumber.api.java8.En
import gateways.DatabaseGateway
import junit.framework.Assert.assertEquals

class DatabaseSteps : En {

    init {

        Given("^that the database is empty$") {
            DatabaseGateway.cleanDatabase()
        }

        Given("^the \"([^\"]*)\" table has the following rows:$") { tableName: String, rows: DataTable ->
            rows.asMaps(String::class.java, String::class.java).forEach { row ->
                DatabaseGateway.insertRow(tableName, row)
            }
        }

        Then("^the \"([^\"]*)\" table contains (\\d+) rows$") { tableName: String, count: Int ->
            val rowCount = DatabaseGateway.countRows(tableName)
            val assertErrorMessage = "The table $tableName contains $rowCount rows"

            assertEquals(assertErrorMessage, rowCount, count)
        }

        Then("the \"([^\"]*)\" table contains the following rows:") { tableName: String, rows: DataTable ->
            rows.asMaps(String::class.java, String::class.java).forEach { row ->
                val assertErrorMessage = "The row $row was not found on table \"$tableName\""

                assertEquals(assertErrorMessage, DatabaseGateway.containsRow(tableName, row), true)
            }
        }

    }

}
