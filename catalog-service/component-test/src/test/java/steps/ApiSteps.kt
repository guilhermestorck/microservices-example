package steps

import conf.Hosts
import cucumber.api.DataTable
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import helpers.FileHelper
import khttp.get
import khttp.responses.Response
import org.skyscreamer.jsonassert.JSONAssert

class ApiSteps : En {

    var response: Response? = null

    @When("^I call the GetProductById API with params:$")
    fun whenICallTheApiWithParams(dataTable: DataTable) {
        val params = dataTable.asMap(String::class.java, String::class.java)
        response = get("${Hosts.CATALOG.address()}/catalog/products/${params.get("id")}")
    }

    @Then("^the GetProductById API response status should be (\\d+)$")
    fun thenTheApiResponseStatusShouldBe(status: Int) {
        assert(response?.statusCode == status)
    }

    @Then("^the GetProductById API response should have a body equals to \"([^\"]*)\"$")
    fun theTheApiResponseShouldHaveBodyEqualsTo(responseName: String) {
        val actual = response?.jsonObject
        val expected = FileHelper.getResponseString("get-product-by-id", responseName)

        JSONAssert.assertEquals(expected, actual, false)
    }
}