package orderservice.conf

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("orderservice")
            .mainClass(Application.javaClass)
            .start()
    }
}