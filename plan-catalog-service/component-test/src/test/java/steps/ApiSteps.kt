package steps

import cucumber.api.DataTable
import cucumber.api.java8.En
import gateways.FilesGateway
import gateways.HttpGateway
import khttp.responses.Response
import org.junit.Assert.assertEquals
import org.skyscreamer.jsonassert.JSONAssert
import parsers.ApiDataTableParser

class ApiSteps : En {

    private val responses = mutableMapOf<String, Response>()

    init {

        Before { ->
            responses.clear()
        }

        When("^the \"([^\"]*)\" API is called with:$") { apiName: String, dataTable: DataTable ->
            responses[apiName] = HttpGateway.request(apiName, dataTable)
        }

        Then("^the \"([^\"]*)\" API response has:$") { apiName: String, dataTable: DataTable ->
            val responseDataTable = ApiDataTableParser.parseResponseDataTable(dataTable)

            if (responseDataTable.body != null) {
                val expectedBody = FilesGateway.getResponseString(apiName, responseDataTable.body)
                JSONAssert.assertEquals(expectedBody, responses[apiName]?.text, false)
            }

            if (responseDataTable.status != null) {
                val assertErrorMessage = "The \"$apiName\" API response status is ${responseDataTable.status}"

                assertEquals(assertErrorMessage, responseDataTable.status.toInt(), responses[apiName]?.statusCode)
            }

            if (responseDataTable.headers != null) {
                val assertErrorMessage = "The \"$apiName\" API response headers are ${responseDataTable.headers}"

                assertEquals(assertErrorMessage, responseDataTable.headers, responses[apiName]?.headers)
            }

        }

    }
}