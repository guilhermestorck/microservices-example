import catalogservice.contract.converters.ProductToProductResponseConverter
import catalogservice.core.domains.Product
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ProductToProductResponseConverterSpec : Spek({

    val converter by memoized { ProductToProductResponseConverter() }

    describe("#convert") {

        it("should convert the product correctly") {
            val product = Product(
                id = "the-id",
                description = "the-description",
                price = 3.0
            )

            val result = converter.convert(product)

            assertThat(result.id, equalTo("the-id"))
            assertThat(result.description, equalTo("the-description"))
            assertThat(result.price, equalTo(3.0))
        }
    }
})