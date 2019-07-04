package com.ranefare.plancatalogservice.db.repositories

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import com.ranefare.plancatalogservice.db.assemblers.InsurancePlanEntityToInsurancePlanAssembler
import com.ranefare.plancatalogservice.db.converters.InsurancePlanToInsurancePlanEntityConverter
import com.ranefare.plancatalogservice.db.domains.InsurancePlanEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.hibernate.Session
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

class InsurancePlanRepositoryImplSpec : Spek({

    val entityManager by memoized { mockk<Session>() }
    val entityConverter by memoized { mockk<InsurancePlanToInsurancePlanEntityConverter>() }
    val entityAssembler by memoized { mockk<InsurancePlanEntityToInsurancePlanAssembler>() }

    val repository by memoized { InsurancePlanRepositoryImpl(entityManager, entityConverter, entityAssembler) }

    describe("#save") {

        it("should save a plan successfully") {
            val domainInput = mockk<InsurancePlan>()
            val domainOutput = mockk<InsurancePlan>()
            val entity = mockk<InsurancePlanEntity>()
            val entitySlot = slot<InsurancePlanEntity>()

            every { entityConverter.convert(domainInput) } returns entity
            every { entityManager.saveOrUpdate(capture(entitySlot)) } answers {
                assertEquals(entitySlot.captured, entity)
                entitySlot.captured
            }
            every { entityAssembler.assemble(entity) } returns domainOutput

            val result = repository.save(domainInput)

            assertEquals(result, domainOutput)
            verify(exactly = 1) { entityConverter.convert(domainInput) }
            verify(exactly = 1) { entityManager.saveOrUpdate(capture(entitySlot)) }
            verify(exactly = 1) { entityAssembler.assemble(entity) }
        }
    }

    describe("#get") {

        it("should get a plan by id") {
            val id = "the-id"
            val domainOutput = mockk<InsurancePlan>()
            val entity = mockk<InsurancePlanEntity>()

            every { entityManager.find(InsurancePlanEntity::class.java, id) } returns entity
            every { entityAssembler.assemble(entity) } returns domainOutput

            val result = repository.get(id)

            assertEquals(result, domainOutput)
            verify(exactly = 1) { entityManager.find(InsurancePlanEntity::class.java, id) }
            verify(exactly = 1) { entityAssembler.assemble(entity) }
        }
    }

    describe("#getAll") {

        it("should get all plans") {
            val domainOutput = mockk<InsurancePlan>()
            val entities = listOf<InsurancePlanEntity>(
                mockk(), mockk(), mockk()
            )
            val criteriaBuilder = mockk<CriteriaBuilder>()
            val selection = mockk<Root<InsurancePlanEntity>>()
            val criteriaQuery = mockk<CriteriaQuery<InsurancePlanEntity>>()

            every { entityManager.criteriaBuilder } returns criteriaBuilder
            every { criteriaBuilder.createQuery(InsurancePlanEntity::class.java) } returns criteriaQuery
            every { criteriaQuery.from(InsurancePlanEntity::class.java) } returns selection
            every { criteriaQuery.select(selection) } returns criteriaQuery

            every { entityManager.createQuery(criteriaQuery).resultList } returns entities
            every { entityAssembler.assemble(any()) } returns domainOutput

            val result = repository.getAll()

            result.forEach {
                assertEquals(it, domainOutput)
            }
            assertEquals(result.size, 3)
            verify(exactly = 1) { entityManager.createQuery(criteriaQuery).resultList }
            verify(exactly = 3) { entityAssembler.assemble(any()) }
        }
    }
})