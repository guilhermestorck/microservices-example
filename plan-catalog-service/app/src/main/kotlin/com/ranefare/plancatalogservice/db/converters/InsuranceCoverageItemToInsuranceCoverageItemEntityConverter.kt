package com.ranefare.plancatalogservice.db.converters


import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.db.domains.InsuranceCoverageItemEntity
import javax.inject.Singleton

@Singleton
class InsuranceCoverageItemToInsuranceCoverageItemEntityConverter :
    EntityConverter<InsuranceCoverageItem, InsuranceCoverageItemEntity> {

    override fun convert(domain: InsuranceCoverageItem): InsuranceCoverageItemEntity {
        return InsuranceCoverageItemEntity(
            id = domain.id,
            name = domain.name,
            description = domain.description,
            insurancePlans = listOf()
        )
    }
}