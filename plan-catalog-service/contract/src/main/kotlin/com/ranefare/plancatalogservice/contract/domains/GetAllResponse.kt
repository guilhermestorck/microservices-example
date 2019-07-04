package com.ranefare.plancatalogservice.contract.domains

data class GetAllResponse<T>(
    val items: List<T>
)