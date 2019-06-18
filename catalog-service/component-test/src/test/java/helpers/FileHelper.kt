package helpers

object FileHelper {

    private val BASE_FOLDER = "/json"

    fun getResponseString(apiName: String, responseName: String): String {
        return this::class.java.getResource("$BASE_FOLDER/$apiName-responses/$responseName.json")
            .readText(Charsets.UTF_8)
    }
}