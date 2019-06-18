import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import catalogservice.core.usecases.GetProductByIdUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class GetProductByIdUseCaseSpec : Spek({

    val productsGateway by memoized { mockk<ProductsGateway>() }
    val useCase by memoized { GetProductByIdUseCase(productsGateway) }

    describe("#execute") {

        it("should call the gateway correctly") {
            val id = "the-id"
            val product = mockk<Product>()

            every { productsGateway.getById(id) } returns product

            val result = useCase.execute(id)

            assertThat(result, equalTo(product))

            verify(exactly = 1) { productsGateway.getById(id) }
        }
 
    }
})