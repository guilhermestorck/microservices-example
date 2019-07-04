package com.ranefare.plancatalogservice.controllers

import com.ranefare.plancatalogservice.contract.domains.resources.InsurancePlanResource
import com.ranefare.plancatalogservice.controllers.assemblers.InsurancePlanResourceToInsurancePlanAssembler
import com.ranefare.plancatalogservice.controllers.converters.InsurancePlanToInsurancePlanResourceConverter
import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.core.usecases.insuranceplan.*
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsurancePlanCrudControllerSpec : Spek({

    val createUseCase by memoized { mockk<CreateInsurancePlanUseCase>() }
    val getUseCase by memoized { mockk<GetInsurancePlanUseCase>() }
    val getAllUseCase by memoized { mockk<GetAllInsurancePlansUseCase>() }
    val addCoverageUseCase by memoized { mockk<AddCoverageItemToInsurancePlanUseCase>() }
    val removeCoverageUseCase by memoized { mockk<RemoveCoverageItemFromInsurancePlanUseCase>() }
    val resourceConverter by memoized { mockk<InsurancePlanToInsurancePlanResourceConverter>() }
    val resourceAssembler by memoized { mockk<InsurancePlanResourceToInsurancePlanAssembler>() }

    val controller by memoized {
        InsurancePlanCrudController(
            createUseCase,
            getUseCase,
            getAllUseCase,
            addCoverageUseCase,
            removeCoverageUseCase,
            resourceConverter,
            resourceAssembler
        )
    }

    describe("#create") {

        it("should create a new plan") {
            val resourceInput = mockk<InsurancePlanResource>()
            val resourceOutput = mockk<InsurancePlanResource>()
            val domainInput = mockk<InsurancePlan>()
            val domainOutput = mockk<InsurancePlan>()

            every { resourceAssembler.assemble(resourceInput) } returns domainInput
            every { createUseCase.execute(domainInput) } returns domainOutput
            every { resourceConverter.convert(domainOutput) } returns resourceOutput

            val result = controller.create(resourceInput)

            assertEquals(result.status, HttpStatus.CREATED)
            assertEquals(result.body(), resourceOutput)
            verify(exactly = 1) { resourceAssembler.assemble(resourceInput) }
            verify(exactly = 1) { createUseCase.execute(domainInput) }
            verify(exactly = 1) { resourceConverter.convert(domainOutput) }
        }
    }

    describe("#get") {

        it("get a plan by id") {
            val id = "the-id"
            val domain = mockk<InsurancePlan>()
            val resource = mockk<InsurancePlanResource>()

            every { getUseCase.execute(id) } returns domain
            every { resourceConverter.convert(domain) } returns resource

            val result = controller.get(id)

            assertEquals(result.status, HttpStatus.OK)
            assertEquals(result.body(), resource)
            verify(exactly = 1) { getUseCase.execute(id) }
            verify(exactly = 1) { resourceConverter.convert(domain) }
        }
    }

    describe("#getAll") {

        it("get all plans") {
            val domain = mockk<InsurancePlan>()
            val resource = mockk<InsurancePlanResource>()

            every { getAllUseCase.execute() } returns listOf(domain, domain, domain)
            every { resourceConverter.convert(domain) } returns resource

            val result = controller.getAll()

            assertEquals(result.status, HttpStatus.OK)
            assertNotNull(result.body())
            assertEquals(result.body()?.items?.size, 3)
            result.body()?.items?.forEach { assertEquals(it, resource) }

            verify(exactly = 1) { getAllUseCase.execute() }
            verify(exactly = 3) { resourceConverter.convert(domain) }
        }
    }

    describe("#addCoverageItem") {

        it("should add a coverage item to a plan") {
            val domain = mockk<InsurancePlan>()
            val resource = mockk<InsurancePlanResource>()
            val planId = "plan-id"
            val coverageItemId = "coverage-item-id"

            every { addCoverageUseCase.execute(planId, coverageItemId) } returns domain
            every { resourceConverter.convert(domain) } returns resource

            val result = controller.addCoverageItem(planId, coverageItemId)

            assertEquals(result.status, HttpStatus.OK)
            assertEquals(result.body(), resource)

            verify(exactly = 1) { addCoverageUseCase.execute(planId, coverageItemId) }
            verify(exactly = 1) { resourceConverter.convert(domain) }
        }
    }

    describe("#removeCoverageItem") {

        it("should remove a coverage item from a plan") {
            val domain = mockk<InsurancePlan>()
            val resource = mockk<InsurancePlanResource>()
            val planId = "plan-id"
            val coverageItemId = "coverage-item-id"

            every { removeCoverageUseCase.execute(planId, coverageItemId) } returns domain
            every { resourceConverter.convert(domain) } returns resource

            val result = controller.removeCoverageItem(planId, coverageItemId)

            assertEquals(result.status, HttpStatus.OK)
            assertEquals(result.body(), resource)

            verify(exactly = 1) { removeCoverageUseCase.execute(planId, coverageItemId) }
            verify(exactly = 1) { resourceConverter.convert(domain) }
        }
    }
})