package com.ranefare.plancatalogservice.contract.domains.resources

data class InsurancePlanResource(
    val id: String?,
    val name: String,
    val costRate: Double,
    val coverageItems: List<InsuranceCoverageItemResource>?
)
