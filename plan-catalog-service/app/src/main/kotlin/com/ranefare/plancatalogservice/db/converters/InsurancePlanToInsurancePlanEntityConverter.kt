package com.ranefare.plancatalogservice.db.converters

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.db.domains.InsurancePlanEntity
import javax.inject.Singleton

@Singleton
class InsurancePlanToInsurancePlanEntityConverter(
    private val insuranceCoverageItemToInsuranceCoverageItemEntityConverter: InsuranceCoverageItemToInsuranceCoverageItemEntityConverter
) : EntityConverter<InsurancePlan, InsurancePlanEntity> {

    override fun convert(domain: InsurancePlan): InsurancePlanEntity {
        return InsurancePlanEntity(
            id = domain.id,
            name = domain.name,
            costRate = domain.costRate,
            coverageItems = domain.coverageItems.map(insuranceCoverageItemToInsuranceCoverageItemEntityConverter::convert)
        )
    }
}