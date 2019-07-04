package domains

data class ResponseDataTable(
    val status: Int?,
    val headers: Map<String, String>?,
    val body: String?
)