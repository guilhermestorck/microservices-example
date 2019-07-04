package com.ranefare.plancatalogservice.db.assemblers

import com.ranefare.plancatalogservice.db.domains.InsuranceCoverageItemEntity
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsuranceCoverageItemEntityToInsuranceCoverageItemAssemblerSpec : Spek({

    val assembler by memoized { InsuranceCoverageItemEntityToInsuranceCoverageItemAssembler() }

    describe("#assemble") {

        val resource by memoized {
            InsuranceCoverageItemEntity(
                name = "name",
                description = "description",
                id = "id",
                insurancePlans = listOf(
                    mockk(), mockk()
                )
            )
        }

        it("should assemble a coverage item") {
            val result = assembler.assemble(resource)

            with(result) {
                assertEquals(name, "name")
                assertEquals(description, "description")
                assertEquals(id, "id")
            }
        }

        it("should assemble a coverage item with a null id") {
            val result = assembler.assemble(resource.copy(id = null))

            with(result) {
                assertEquals(name, "name")
                assertEquals(description, "description")
                assertEquals(id, null)
            }
        }
    }
})