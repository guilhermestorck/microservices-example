package com.ranefare.plancatalogservice.core.repositories

interface CrudRepository<T, ID> {

    fun save(resource: T): T

    fun get(id: ID): T

    fun getAll(): List<T>
}
