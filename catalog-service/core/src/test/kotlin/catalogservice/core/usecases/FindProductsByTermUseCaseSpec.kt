import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import catalogservice.core.usecases.FindProductsByTermUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class FindProductsByTermUseCaseSpec : Spek({

    val productsGateway by memoized { mockk<ProductsGateway>() }
    val useCase by memoized { FindProductsByTermUseCase(productsGateway) }

    describe("#execute") {

        it("should call the gateway correctly") {
            val term = "the-term"
            val products = listOf<Product>(mockk(), mockk())

            every { productsGateway.findByTerm(term) } returns products

            val result = useCase.execute(term)

            assertThat(result, equalTo(products))

            verify(exactly = 1) { productsGateway.findByTerm(term) }
        }

    }
})