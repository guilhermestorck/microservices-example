package com.ranefare.plancatalogservice.controllers.assemblers

import com.ranefare.plancatalogservice.contract.domains.resources.InsurancePlanResource
import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import javax.inject.Singleton

@Singleton
class InsurancePlanResourceToInsurancePlanAssembler(
    private val insuranceCoverageItemResourceToInsuranceCoverageItemAssembler: InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler
) : ResourceAssembler<InsurancePlanResource, InsurancePlan> {

    override fun assemble(resource: InsurancePlanResource): InsurancePlan {
        return InsurancePlan(
            id = resource.id,
            name = resource.name,
            costRate = resource.costRate,
            coverageItems = resource.coverageItems?.map(
                insuranceCoverageItemResourceToInsuranceCoverageItemAssembler::assemble
            ) ?: listOf()
        )
    }
}