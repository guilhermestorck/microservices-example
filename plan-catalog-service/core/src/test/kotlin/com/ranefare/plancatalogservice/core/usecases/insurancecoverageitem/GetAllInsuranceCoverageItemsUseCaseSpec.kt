package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetAllInsuranceCoverageItemsUseCaseSpec : Spek({

    val coverageItemsRepository by memoized { mockk<InsuranceCoverageItemRepository>() }
    val useCase by memoized { GetAllInsuranceCoverageItemsUseCase(coverageItemsRepository) }

    describe("#execute") {

        it("should get all coverage items from repository") {
            val plans = mockk<List<InsuranceCoverageItem>>()

            every { coverageItemsRepository.getAll() } returns plans

            val result = useCase.execute()

            assertEquals(plans, result)
            verify(exactly = 1) { coverageItemsRepository.getAll() }
        }
    }
})