package com.ranefare.plancatalogservice.core.usecases.crud

import com.ranefare.plancatalogservice.core.repositories.CrudRepository

open class CreateResourceUseCase<T, ID>(
    private val repository: CrudRepository<T, ID>
) {

    fun execute(resource: T): T {
        return repository.save(resource)
    }

}