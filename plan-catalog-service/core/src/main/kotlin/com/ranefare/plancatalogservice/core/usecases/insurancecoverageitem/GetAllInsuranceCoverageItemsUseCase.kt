package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.CrudRepository
import com.ranefare.plancatalogservice.core.usecases.crud.GetAllResourcesUseCase

class GetAllInsuranceCoverageItemsUseCase(
    repository: CrudRepository<InsuranceCoverageItem, String>
) : GetAllResourcesUseCase<InsuranceCoverageItem, String>(repository)