package com.ranefare.plancatalogservice.conf.injection

import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.CreateInsuranceCoverageItemUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetAllInsuranceCoverageItemsUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetInsuranceCoverageItemUseCase
import com.ranefare.plancatalogservice.core.usecases.insuranceplan.*
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class CoreUseCasesInjector {

    @Singleton
    fun createInsurancePlanUseCase(repository: InsurancePlanRepository): CreateInsurancePlanUseCase {
        return CreateInsurancePlanUseCase(repository)
    }

    @Singleton
    fun getInsurancePlanUseCase(repository: InsurancePlanRepository): GetInsurancePlanUseCase {
        return GetInsurancePlanUseCase(repository)
    }

    @Singleton
    fun getAllInsurancePlansUseCase(repository: InsurancePlanRepository): GetAllInsurancePlansUseCase {
        return GetAllInsurancePlansUseCase(repository)
    }

    @Singleton
    fun createInsuranceCoverageItemUseCase(repository: InsuranceCoverageItemRepository): CreateInsuranceCoverageItemUseCase {
        return CreateInsuranceCoverageItemUseCase(repository)
    }

    @Singleton
    fun getInsuranceCoverageItemUseCase(repository: InsuranceCoverageItemRepository): GetInsuranceCoverageItemUseCase {
        return GetInsuranceCoverageItemUseCase(repository)
    }

    @Singleton
    fun getAllInsuranceCoverageItemsUseCase(repository: InsuranceCoverageItemRepository): GetAllInsuranceCoverageItemsUseCase {
        return GetAllInsuranceCoverageItemsUseCase(repository)
    }

    @Singleton
    fun addCoverageItemToInsurancePlanUseCase(
        planRepository: InsurancePlanRepository,
        coverageItemRepository: InsuranceCoverageItemRepository
    ): AddCoverageItemToInsurancePlanUseCase {
        return AddCoverageItemToInsurancePlanUseCase(planRepository, coverageItemRepository)
    }

    @Singleton
    fun removeCoverageItemFromInsurancePlanUseCase(
        planRepository: InsurancePlanRepository,
        coverageItemRepository: InsuranceCoverageItemRepository
    ): RemoveCoverageItemFromInsurancePlanUseCase {
        return RemoveCoverageItemFromInsurancePlanUseCase(planRepository, coverageItemRepository)
    }


}