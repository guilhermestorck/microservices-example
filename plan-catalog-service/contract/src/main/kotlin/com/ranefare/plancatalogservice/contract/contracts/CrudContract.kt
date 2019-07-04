package com.ranefare.plancatalogservice.contract.contracts

import com.ranefare.plancatalogservice.contract.domains.GetAllResponse
import io.micronaut.http.HttpResponse

interface CrudContract<T, ID> {

    fun create(resource: T): HttpResponse<T>

    fun get(id: ID): HttpResponse<T>

    fun getAll(): HttpResponse<GetAllResponse<T>>

}