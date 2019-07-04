package com.ranefare.plancatalogservice.core.usecases.crud

import com.ranefare.plancatalogservice.core.repositories.CrudRepository

open class GetAllResourcesUseCase<T, ID>(
    private val repository: CrudRepository<T, ID>
) {

    fun execute(): List<T> {
        return repository.getAll()
    }

}