package com.ranefare.plancatalogservice.controllers.converters

import com.ranefare.plancatalogservice.contract.domains.resources.InsurancePlanResource
import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import javax.inject.Singleton

@Singleton
class InsurancePlanToInsurancePlanResourceConverter(
    private val insuranceCoverageItemToInsuranceCoverageItemResourceConverter: InsuranceCoverageItemToInsuranceCoverageItemResourceConverter
) : ResourceConverter<InsurancePlan, InsurancePlanResource> {

    override fun convert(domain: InsurancePlan): InsurancePlanResource {
        return InsurancePlanResource(
            id = domain.id,
            name = domain.name,
            costRate = domain.costRate,
            coverageItems = domain.coverageItems.map(insuranceCoverageItemToInsuranceCoverageItemResourceConverter::convert)
        )
    }
}