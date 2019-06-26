import catalogservice.contract.domains.ProductResponse
import io.micronaut.http.HttpResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import orderservice.clients.CatalogClient
import orderservice.core.domains.exceptions.CatalogIntegrationException
import orderservice.gateways.CatalogGatewayImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CatalogGatewayImplSpec : Spek({

    val catalogClient by memoized { mockk<CatalogClient>() }
    val gateway by memoized { CatalogGatewayImpl(catalogClient) }

    describe("#getPriceById") {

        it("should get the price by product id") {
            val id = "the-id"
            val price = 12.0
            val httpResponse = mockk<HttpResponse<ProductResponse>>()

            every { httpResponse.body() } returns ProductResponse(id, "", price)
            every { catalogClient.getProductById(id) } returns httpResponse

            val result = gateway.getPriceById(id)

            assertEquals(result, 12.0)

            verify(exactly = 1) { catalogClient.getProductById(id) }
        }


        it("should throw exception when the body is null") {
            val id = "the-id"
            val httpResponse = mockk<HttpResponse<ProductResponse>>()

            every { httpResponse.body() } returns null
            every { catalogClient.getProductById(id) } returns httpResponse

            assertThrows<CatalogIntegrationException> {
                gateway.getPriceById(id)
            }

            verify(exactly = 1) { catalogClient.getProductById(id) }
        }

    }

})