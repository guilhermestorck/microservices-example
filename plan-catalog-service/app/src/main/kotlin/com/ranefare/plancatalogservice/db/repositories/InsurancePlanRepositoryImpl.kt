package com.ranefare.plancatalogservice.db.repositories

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import com.ranefare.plancatalogservice.db.assemblers.InsurancePlanEntityToInsurancePlanAssembler
import com.ranefare.plancatalogservice.db.converters.InsurancePlanToInsurancePlanEntityConverter
import com.ranefare.plancatalogservice.db.domains.InsurancePlanEntity
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class InsurancePlanRepositoryImpl(
    entityManager: EntityManager,
    entityConverter: InsurancePlanToInsurancePlanEntityConverter,
    entityAssembler: InsurancePlanEntityToInsurancePlanAssembler
) : CrudRepositoryImpl<InsurancePlan, InsurancePlanEntity, String>(
    entityManager,
    InsurancePlanEntity::class.java,
    entityConverter,
    entityAssembler
), InsurancePlanRepository