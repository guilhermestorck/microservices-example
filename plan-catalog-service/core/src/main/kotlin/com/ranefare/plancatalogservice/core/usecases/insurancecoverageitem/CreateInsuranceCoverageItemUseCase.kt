package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import com.ranefare.plancatalogservice.core.usecases.crud.CreateResourceUseCase

class CreateInsuranceCoverageItemUseCase(
    repository: InsuranceCoverageItemRepository
) : CreateResourceUseCase<InsuranceCoverageItem, String>(repository)