package com.ranefare.plancatalogservice.db.repositories

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import com.ranefare.plancatalogservice.db.assemblers.InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler
import com.ranefare.plancatalogservice.db.converters.InsuranceCoverageItemToInsuranceCoverageItemEntityConverter
import com.ranefare.plancatalogservice.db.domains.InsuranceCoverageItemEntity
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class InsuranceCoverageItemRepositoryImpl(
    entityManager: EntityManager,
    entityConverter: InsuranceCoverageItemToInsuranceCoverageItemEntityConverter,
    entityAssembler: InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler
) : CrudRepositoryImpl<InsuranceCoverageItem, InsuranceCoverageItemEntity, String>(
    entityManager,
    InsuranceCoverageItemEntity::class.java,
    entityConverter,
    entityAssembler
), InsuranceCoverageItemRepository