package com.ranefare.plancatalogservice.controllers

import com.ranefare.plancatalogservice.contract.domains.resources.InsuranceCoverageItemResource
import com.ranefare.plancatalogservice.controllers.assemblers.InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler
import com.ranefare.plancatalogservice.controllers.converters.InsuranceCoverageItemToInsuranceCoverageItemResourceConverter
import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.CreateInsuranceCoverageItemUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetAllInsuranceCoverageItemsUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetInsuranceCoverageItemUseCase
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsuranceCoverageItemCrudControllerSpec : Spek({

    val createUseCase by memoized { mockk<CreateInsuranceCoverageItemUseCase>() }
    val getUseCase by memoized { mockk<GetInsuranceCoverageItemUseCase>() }
    val getAllUseCase by memoized { mockk<GetAllInsuranceCoverageItemsUseCase>() }
    val resourceConverter by memoized { mockk<InsuranceCoverageItemToInsuranceCoverageItemResourceConverter>() }
    val resourceAssembler by memoized { mockk<InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler>() }

    val controller by memoized {
        InsuranceCoverageItemCrudController(
            createUseCase,
            getUseCase,
            getAllUseCase,
            resourceConverter,
            resourceAssembler
        )
    }

    describe("#create") {

        it("should create a new coverage item") {
            val resourceInput = mockk<InsuranceCoverageItemResource>()
            val resourceOutput = mockk<InsuranceCoverageItemResource>()
            val domainInput = mockk<InsuranceCoverageItem>()
            val domainOutput = mockk<InsuranceCoverageItem>()

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

        it("get a coverage item by id") {
            val id = "the-id"
            val domain = mockk<InsuranceCoverageItem>()
            val resource = mockk<InsuranceCoverageItemResource>()

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

        it("get all coverage items") {
            val domain = mockk<InsuranceCoverageItem>()
            val resource = mockk<InsuranceCoverageItemResource>()

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
})