package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.CrudRepository
import com.ranefare.plancatalogservice.core.usecases.crud.GetAllResourcesUseCase

class GetAllInsurancePlansUseCase(
    repository: CrudRepository<InsurancePlan, String>
) : GetAllResourcesUseCase<InsurancePlan, String>(repository)