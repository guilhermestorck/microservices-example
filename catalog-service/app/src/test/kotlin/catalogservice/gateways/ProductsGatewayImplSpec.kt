import catalogservice.gateways.ProductsGatewayImpl
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object ProductsGatewayImplSpec : Spek({

    val gateway by memoized { ProductsGatewayImpl() }

    describe("#findByTerm") {

        it("should return the search result for a term") {
            val result = gateway.findByTerm("the-term")

            assertThat(result.size, equalTo(2))
            assertThat(result[0].id, equalTo("id-the-term"))
            assertThat(result[0].description, equalTo("the-term"))
            assertThat(result[0].price, equalTo(3.0))
            assertThat(result[1].id, equalTo("id-the-termAAAA"))
            assertThat(result[1].description, equalTo("the-termAAAA"))
            assertThat(result[1].price, equalTo(3.0))
        }
    }

    describe("#getById") {
        it("should get a product by id") {
            val result = gateway.getById("the-id")

            assertThat(result, notNullValue())
            assertThat(result?.id, equalTo("id-the-id"))
            assertThat(result?.description, equalTo("the-id"))
            assertThat(result?.price, equalTo(3.0))
        }
    }
})