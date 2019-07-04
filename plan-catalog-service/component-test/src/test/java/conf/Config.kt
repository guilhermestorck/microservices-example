package conf

object Config {

    fun getEnv(propName: String, default: String): String {
        val env = System.getenv(propName)
        return if (env.isNullOrBlank() || env == "null") default else env
    }
}