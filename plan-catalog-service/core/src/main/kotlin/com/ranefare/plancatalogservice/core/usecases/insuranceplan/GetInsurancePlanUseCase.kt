package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.CrudRepository
import com.ranefare.plancatalogservice.core.usecases.crud.GetResourceUseCase

class GetInsurancePlanUseCase(
    repository: CrudRepository<InsurancePlan, String>
) : GetResourceUseCase<InsurancePlan, String>(repository)