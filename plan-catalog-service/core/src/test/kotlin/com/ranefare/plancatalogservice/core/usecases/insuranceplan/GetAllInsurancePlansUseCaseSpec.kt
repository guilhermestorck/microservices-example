package com.ranefare.plancatalogservice.core.usecases.insuranceplan

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetAllInsurancePlansUseCaseSpec : Spek({

    val plansRepository by memoized { mockk<InsurancePlanRepository>() }
    val useCase by memoized { GetAllInsurancePlansUseCase(plansRepository) }

    describe("#execute") {

        it("should get all plans from repository") {
            val plans = mockk<List<InsurancePlan>>()

            every { plansRepository.getAll() } returns plans

            val result = useCase.execute()

            assertEquals(plans, result)
            verify(exactly = 1) { plansRepository.getAll() }
        }
    }
})