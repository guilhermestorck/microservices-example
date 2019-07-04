package com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CreateInsuranceCoverageItemUseCaseSpec : Spek({
    val coverageItemRepository by memoized { mockk<InsuranceCoverageItemRepository>() }
    val useCase by memoized { CreateInsuranceCoverageItemUseCase(coverageItemRepository) }

    describe("#execute") {

        it("should save the coverage item in the repository") {
            val input = mockk<InsuranceCoverageItem>()
            val created = mockk<InsuranceCoverageItem>()

            every { coverageItemRepository.save(input) } returns created

            val result = useCase.execute(input)

            Assertions.assertEquals(result, created)
            verify(exactly = 1) { coverageItemRepository.save(input) }
        }
    }

})