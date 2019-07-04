package com.ranefare.plancatalogservice.conf

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("com.ranefare.plancatalogservice")
            .mainClass(Application.javaClass)
            .start()
    }
}