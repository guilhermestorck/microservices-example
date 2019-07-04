package com.ranefare.plancatalogservice.controllers.assemblers

import com.ranefare.plancatalogservice.contract.domains.resources.InsuranceCoverageItemResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsuranceCoverageItemResourceToInsuranceCoverageItemAssemblerSpec : Spek({

    val assembler by memoized { InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler() }

    describe("#assemble") {

        val resource by memoized {
            InsuranceCoverageItemResource(
                name = "name",
                description = "description",
                id = "id"
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