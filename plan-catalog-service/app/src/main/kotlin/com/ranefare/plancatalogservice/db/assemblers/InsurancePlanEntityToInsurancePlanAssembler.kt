package com.ranefare.plancatalogservice.db.assemblers

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.db.domains.InsurancePlanEntity
import javax.inject.Singleton

@Singleton
class InsurancePlanEntityToInsurancePlanAssembler(
    private val insuranceCoverageItemEntityToInsuranceCoverageItemAssembler: InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler
) :
    EntityAssembler<InsurancePlanEntity, InsurancePlan> {

    override fun assemble(entity: InsurancePlanEntity): InsurancePlan {
        return InsurancePlan(
            id = entity.id,
            name = entity.name,
            costRate = entity.costRate,
            coverageItems = entity.coverageItems.map(insuranceCoverageItemEntityToInsuranceCoverageItemAssembler::assemble)
        )
    }
}