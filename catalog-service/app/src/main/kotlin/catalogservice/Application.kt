package catalogservice

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("catalog.service")
                .mainClass(Application.javaClass)
                .start()
    }
}