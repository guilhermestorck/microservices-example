package parsers

import cucumber.api.DataTable
import domains.RequestDataTable
import domains.ResponseDataTable
import gateways.FilesGateway
import gherkin.deps.com.google.gson.Gson

object ApiDataTableParser {

    private val gson = Gson()

    fun parseRequestDataTable(baseUrl: String?, apiName: String, dataTable: DataTable): RequestDataTable {
        val requestData = dataTable.asLists(String::class.java).groupBy { it[0] }

        val pathParams = parseMultipleValue("pathParam", requestData) ?: mapOf()
        val body = parseSingleValue("body", requestData) ?: ""
        val method = parseSingleValue("method", requestData) ?: ""

        val url = pathParams.entries.fold(baseUrl!!) { url, pathParam ->
            url.replace("{${pathParam.key}}", pathParam.value)
        }
        val json = if (body != "" && method != "GET") {
            gson.fromJson(FilesGateway.getRequestString(apiName, body), Map::class.java)
        } else {
            null
        }

        return RequestDataTable(
            method = method,
            url = url,
            body = json,
            headers = parseMultipleValue("header", requestData) ?: mapOf(),
            params = parseMultipleValue("param", requestData) ?: mapOf()
        )
    }

    fun parseResponseDataTable(dataTable: DataTable): ResponseDataTable {
        val responseData = dataTable.asLists(String::class.java).groupBy { it[0] }
        
        return ResponseDataTable(
            status = parseSingleValue("status", responseData)?.toInt(),
            body = parseSingleValue("body", responseData),
            headers = parseMultipleValue("headers", responseData)
        )
    }

    private fun parseSingleValue(key: String, requestData: Map<String, List<MutableList<String>>>): String? {
        return requestData[key]?.get(0)?.get(1)
    }

    private fun parseMultipleValue(
        key: String, requestData: Map<String, List<MutableList<String>>>
    ): Map<String, String>? {
        return requestData[key]?.map {
            val parts = it[1].split(":", limit = 2)
            parts[0].trim() to parts[1].trim()
        }?.toMap()
    }
}