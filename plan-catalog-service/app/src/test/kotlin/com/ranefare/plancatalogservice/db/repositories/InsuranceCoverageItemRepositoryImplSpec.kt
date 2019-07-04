package com.ranefare.plancatalogservice.db.repositories

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import com.ranefare.plancatalogservice.db.assemblers.InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler
import com.ranefare.plancatalogservice.db.converters.InsuranceCoverageItemToInsuranceCoverageItemEntityConverter
import com.ranefare.plancatalogservice.db.domains.InsuranceCoverageItemEntity
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

class InsuranceCoverageItemRepositoryImplSpec : Spek({

    val entityManager by memoized { mockk<Session>() }
    val entityConverter by memoized { mockk<InsuranceCoverageItemToInsuranceCoverageItemEntityConverter>() }
    val entityAssembler by memoized { mockk<InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler>() }

    val repository by memoized { InsuranceCoverageItemRepositoryImpl(entityManager, entityConverter, entityAssembler) }

    describe("#save") {

        it("should save a coverage item successfully") {
            val domainInput = mockk<InsuranceCoverageItem>()
            val domainOutput = mockk<InsuranceCoverageItem>()
            val entity = mockk<InsuranceCoverageItemEntity>()
            val entitySlot = slot<InsuranceCoverageItemEntity>()

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

        it("should get a coverage item by id") {
            val id = "the-id"
            val domainOutput = mockk<InsuranceCoverageItem>()
            val entity = mockk<InsuranceCoverageItemEntity>()

            every { entityManager.find(InsuranceCoverageItemEntity::class.java, id) } returns entity
            every { entityAssembler.assemble(entity) } returns domainOutput

            val result = repository.get(id)

            assertEquals(result, domainOutput)
            verify(exactly = 1) { entityManager.find(InsuranceCoverageItemEntity::class.java, id) }
            verify(exactly = 1) { entityAssembler.assemble(entity) }
        }
    }

    describe("#getAll") {

        it("should get all coverage items") {
            val domainOutput = mockk<InsuranceCoverageItem>()
            val entities = listOf<InsuranceCoverageItemEntity>(
                mockk(), mockk(), mockk()
            )
            val criteriaBuilder = mockk<CriteriaBuilder>()
            val selection = mockk<Root<InsuranceCoverageItemEntity>>()
            val criteriaQuery = mockk<CriteriaQuery<InsuranceCoverageItemEntity>>()

            every { entityManager.criteriaBuilder } returns criteriaBuilder
            every { criteriaBuilder.createQuery(InsuranceCoverageItemEntity::class.java) } returns criteriaQuery
            every { criteriaQuery.from(InsuranceCoverageItemEntity::class.java) } returns selection
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