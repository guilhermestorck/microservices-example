package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsuranceCoverageItemRepository
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class RemoveCoverageItemFromInsurancePlanUseCaseSpec : Spek({

    val insurancePlanRepository by memoized { mockk<InsurancePlanRepository>() }
    val insuranceCoverageItemRepository by memoized { mockk<InsuranceCoverageItemRepository>() }
    val useCase by memoized {
        RemoveCoverageItemFromInsurancePlanUseCase(
            insurancePlanRepository,
            insuranceCoverageItemRepository
        )
    }

    describe("#execute") {

        it("should remove a coverage item from an insurance plan") {
            val coverageItem = InsuranceCoverageItem(
                id = "id-item",
                name = "name",
                description = "description"
            )

            val plan = InsurancePlan(
                id = "id-plan",
                name = "name",
                costRate = 0.008,
                coverageItems = listOf(
                    mockk(), coverageItem
                )
            )

            val updatedPlanSlot = slot<InsurancePlan>()

            every { insurancePlanRepository.get("id-plan") } returns plan
            every { insuranceCoverageItemRepository.get("id-item") } returns coverageItem
            every { insurancePlanRepository.save(capture(updatedPlanSlot)) } answers {
                assertEquals(updatedPlanSlot.captured.coverageItems.size, 1)
                updatedPlanSlot.captured
            }

            val result = useCase.execute("id-plan", "id-item")

            assertEquals(result.coverageItems.size, 1)
            verify(exactly = 1) { insurancePlanRepository.get("id-plan") }
            verify(exactly = 1) { insuranceCoverageItemRepository.get("id-item") }
            verify(exactly = 1) { insurancePlanRepository.save(capture(updatedPlanSlot)) }

        }
    }
})