package com.ranefare.plancatalogservice.controllers.assemblers

import com.ranefare.plancatalogservice.contract.domains.resources.InsuranceCoverageItemResource
import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import javax.inject.Singleton

@Singleton
class InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler :
    ResourceAssembler<InsuranceCoverageItemResource, InsuranceCoverageItem> {

    override fun assemble(resource: InsuranceCoverageItemResource): InsuranceCoverageItem {
        return InsuranceCoverageItem(
            id = resource.id,
            name = resource.name,
            description = resource.description
        )
    }
}