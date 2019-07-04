package com.ranefare.plancatalogservice.core.usecases.insuranceplan


import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.repositories.InsurancePlanRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CreateInsurancePlanUseCaseSpec : Spek({
    val plansRepository by memoized { mockk<InsurancePlanRepository>() }
    val useCase by memoized { CreateInsurancePlanUseCase(plansRepository) }

    describe("#execute") {

        it("should save the plan in the repository") {
            val input = mockk<InsurancePlan>()
            val created = mockk<InsurancePlan>()

            every { plansRepository.save(input) } returns created

            val result = useCase.execute(input)

            assertEquals(result, created)
            verify(exactly = 1) { plansRepository.save(input) }
        }
    }

})