package com.ranefare.plancatalogservice.controllers.converters

import com.ranefare.plancatalogservice.contract.domains.resources.InsuranceCoverageItemResource
import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import javax.inject.Singleton

@Singleton
class InsuranceCoverageItemToInsuranceCoverageItemResourceConverter :
    ResourceConverter<InsuranceCoverageItem, InsuranceCoverageItemResource> {

    override fun convert(domain: InsuranceCoverageItem): InsuranceCoverageItemResource {
        return InsuranceCoverageItemResource(
            id = domain.id,
            name = domain.name,
            description = domain.description
        )
    }
}