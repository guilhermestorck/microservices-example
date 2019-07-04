package gateways

import conf.Hosts
import cucumber.api.DataTable
import khttp.responses.Response
import parsers.ApiDataTableParser

object HttpGateway {


    private val APIS = mapOf(
        "create insurance plan" to "/insurance/plans",
        "get all insurance plans" to "/insurance/plans",
        "get insurance plan by id" to "/insurance/plans/{id}",
        "add coverage item to plan" to "/insurance/plans/{planId}/coverage/{coverageItemId}",
        "remove coverage item from plan" to "/insurance/plans/{planId}/coverage/{coverageItemId}",
        "create insurance coverage item" to "/insurance/coverage-items",
        "get all insurance coverage items" to "/insurance/coverage-items",
        "get insurance coverage item by id" to "/insurance/coverage-items/{id}"
    )

    fun request(apiName: String, dataTable: DataTable): Response {

        val requestDataTable =
            ApiDataTableParser.parseRequestDataTable("${Hosts.APP.address()}${APIS[apiName]}", apiName, dataTable)

        return khttp.request(
            method = requestDataTable.method,
            url = requestDataTable.url,
            headers = requestDataTable.headers,
            json = requestDataTable.body,
            params = requestDataTable.params
        )
    }
}