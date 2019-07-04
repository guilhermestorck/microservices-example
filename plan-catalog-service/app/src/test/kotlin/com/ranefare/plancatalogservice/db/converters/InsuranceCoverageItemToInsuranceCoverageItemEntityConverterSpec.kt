package com.ranefare.plancatalogservice.db.converters

import com.ranefare.plancatalogservice.core.domains.InsuranceCoverageItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsuranceCoverageItemToInsuranceCoverageItemEntityConverterSpec : Spek({

    val converter by memoized { InsuranceCoverageItemToInsuranceCoverageItemEntityConverter() }

    describe("#convert") {

        val domain by memoized {
            InsuranceCoverageItem(
                id = "id",
                name = "name",
                description = "description"
            )
        }

        it("should convert a coverage item") {

            val result = converter.convert(domain)

            with(result) {
                assertEquals(id, "id")
                assertEquals(name, "name")
                assertEquals(description, "description")
            }
        }

        it("should convert a coverage item with a null id") {

            val result = converter.convert(domain.copy(id = null))

            with(result) {
                assertEquals(id, null)
                assertEquals(name, "name")
                assertEquals(description, "description")
            }
        }
    }
})