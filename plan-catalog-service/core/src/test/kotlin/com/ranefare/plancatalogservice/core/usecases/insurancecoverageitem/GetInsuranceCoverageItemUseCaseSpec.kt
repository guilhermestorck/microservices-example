package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetInsuranceCoverageItemUseCaseSpec : Spek({

    val coverageItemsRepository by memoized { mockk<InsuranceCoverageItemRepository>() }
    val useCase by memoized { GetInsuranceCoverageItemUseCase(coverageItemsRepository) }

    describe("#execute") {

        it("should get the coverage item from the repository") {
            val id = "the-id"
            val insuranceCoverageItem = mockk<InsuranceCoverageItem>()

            every { coverageItemsRepository.get(id) } returns insuranceCoverageItem

            val result = useCase.execute(id)

            verify(exactly = 1) { coverageItemsRepository.get(id) }
            assertEquals(result, insuranceCoverageItem)
        }
    }
})