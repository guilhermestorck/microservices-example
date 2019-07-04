package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetInsurancePlanUseCaseSpec : Spek({

    val plansRepository by memoized { mockk<InsurancePlanRepository>() }
    val useCase by memoized { GetInsurancePlanUseCase(plansRepository) }

    describe("#execute") {

        it("should get the plan from the repository") {
            val id = "the-id"
            val insurancePlan = mockk<InsurancePlan>()

            every { plansRepository.get(id) } returns insurancePlan

            val result = useCase.execute(id)

            verify(exactly = 1) { plansRepository.get(id) }
            assertEquals(result, insurancePlan)
        }
    }
})