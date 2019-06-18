package catalogservice.conf

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("catalogservice")
            .mainClass(Application.javaClass)
            .start()
    }
}