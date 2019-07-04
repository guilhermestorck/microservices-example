package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.CrudRepository
import com.ranefare.plancatalogservice.core.usecases.crud.GetResourceUseCase

class GetInsuranceCoverageItemUseCase(
    repository: CrudRepository<InsuranceCoverageItem, String>
) : GetResourceUseCase<InsuranceCoverageItem, String>(repository)