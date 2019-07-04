package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository

class RemoveCoverageItemFromInsurancePlanUseCase(
    private val insurancePlanRepository: InsurancePlanRepository,
    private val insuranceCoverageItemRepository: InsuranceCoverageItemRepository
) {

    fun execute(planId: String, coverageItemId: String): InsurancePlan {
        val plan = insurancePlanRepository.get(planId)
        val coverageItem = insuranceCoverageItemRepository.get(coverageItemId)

        val updatedPlan = plan.copy(coverageItems = plan.coverageItems - coverageItem)
        insurancePlanRepository.save(updatedPlan)
        return updatedPlan
    }
}