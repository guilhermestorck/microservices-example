package  conf

enum class Hosts {

    CATALOG {
        override fun address() = Config.getEnv("catalog_service_url", default = "http://localhost:8080")
    };

    abstract fun address(): String
}