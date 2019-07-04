package com.ranefare.plancatalogservice.core.usecases.crud

import com.ranefare.plancatalogservice.core.repositories.CrudRepository

open class GetResourceUseCase<T, ID>(
    private val repository: CrudRepository<T, ID>
) {

    fun execute(id: ID): T {
        return repository.get(id)
    }

}