package com.ranefare.plancatalogservice.db.converters

import com.ranefare.plancatalogservice.core.domains.InsurancePlan
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsurancePlanToInsurancePlanEntityConverterSpec : Spek({

    val coverageItemsConverter by memoized { mockk<InsuranceCoverageItemToInsuranceCoverageItemEntityConverter>() }
    val converter by memoized { InsurancePlanToInsurancePlanEntityConverter(coverageItemsConverter) }

    describe("#convert") {

        val resource by memoized {
            InsurancePlan(
                name = "name",
                costRate = 0.05,
                id = "id",
                coverageItems = listOf(
                    mockk(), mockk()
                )
            )
        }

        it("should convert a plan") {
            every { coverageItemsConverter.convert(any()) } returns mockk()

            val result = converter.convert(resource)

            with(result) {
                assertEquals(name, "name")
                assertEquals(costRate, 0.05)
                assertEquals(id, "id")
                assertEquals(coverageItems.size, 2)
            }
            verify(exactly = 2) { coverageItemsConverter.convert(any()) }
        }

        it("should convert a plan with a null id") {
            every { coverageItemsConverter.convert(any()) } returns mockk()

            val result = converter.convert(resource.copy(id = null))

            with(result) {
                assertEquals(name, "name")
                assertEquals(costRate, 0.05)
                assertEquals(id, null)
                assertEquals(coverageItems.size, 2)
            }
            verify(exactly = 2) { coverageItemsConverter.convert(any()) }
        }
    }
})