package domains

data class RequestDataTable(
    val method: String,
    val url: String,
    val body: Any?,
    val headers: Map<String, String>,
    val params: Map<String, String>
)