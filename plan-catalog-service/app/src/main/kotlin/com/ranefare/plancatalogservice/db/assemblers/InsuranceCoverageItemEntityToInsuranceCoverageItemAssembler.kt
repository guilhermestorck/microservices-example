package com.ranefare.plancatalogservice.db.assemblers

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.db.domains.InsuranceCoverageItemEntity
import javax.inject.Singleton

@Singleton
class InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler :
    EntityAssembler<InsuranceCoverageItemEntity, InsuranceCoverageItem> {

    override fun assemble(entity: InsuranceCoverageItemEntity): InsuranceCoverageItem {
        return InsuranceCoverageItem(
            id = entity.id,
            name = entity.name,
            description = entity.description
        )
    }
}