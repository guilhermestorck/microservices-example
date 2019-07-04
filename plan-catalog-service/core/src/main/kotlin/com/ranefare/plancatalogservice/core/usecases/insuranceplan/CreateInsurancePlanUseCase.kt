package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import com.ranefare.plancatalogservice.core.usecases.crud.CreateResourceUseCase

class CreateInsurancePlanUseCase(
    repository: InsurancePlanRepository
) : CreateResourceUseCase<InsurancePlan, String>(repository)