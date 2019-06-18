import catalogservice.contract.converters.ProductToProductResponseConverter
import catalogservice.contract.domains.ProductResponse
import catalogservice.controllers.CatalogController
import catalogservice.core.domains.Product
import catalogservice.core.usecases.FindProductsByTermUseCase
import catalogservice.core.usecases.GetProductByIdUseCase
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CatalogControllerSpec : Spek({
    val findProductsByTermUseCase by memoized { mockk<FindProductsByTermUseCase>() }
    val getProductByIdUseCase by memoized { mockk<GetProductByIdUseCase>() }
    val productToProductResponseConverter by memoized { mockk<ProductToProductResponseConverter>() }

    val controller by memoized {
        CatalogController(
            findProductsByTermUseCase,
            getProductByIdUseCase,
            productToProductResponseConverter
        )
    }

    describe("#getProductById") {

        it("should return a product by id") {
            val id = "the-id"
            val product = mockk<Product>()
            val productResponse = mockk<ProductResponse>()

            every { getProductByIdUseCase.execute(id) } returns product
            every { productToProductResponseConverter.convert(product) } returns productResponse

            val result = controller.getProductById(id)

            assertThat(result.status, equalTo(HttpStatus.OK))
            assertThat(result.body(), notNullValue())
            assertThat(result.body(), equalTo(productResponse))

            verify(exactly = 1) { getProductByIdUseCase.execute(id) }
            verify(exactly = 1) { productToProductResponseConverter.convert(product) }
        }

        it("should return 404 when no product is found") {
            val id = "the-id"

            every { getProductByIdUseCase.execute(id) } returns null

            val result = controller.getProductById(id)

            assertThat(result.status, equalTo(HttpStatus.NOT_FOUND))
            assertThat(result.body(), nullValue())

            verify(exactly = 1) { getProductByIdUseCase.execute(id) }
            verify(exactly = 0) { productToProductResponseConverter.convert(any()) }
        }
    }

    describe("#findProductsByTerm") {

        it("should return the search results given a term") {
            val products = listOf(
                mockk<Product>(),
                mockk<Product>()
            )
            val productResponses = listOf(
                mockk<ProductResponse>(),
                mockk<ProductResponse>()
            )
            val term = "the-term"

            every { findProductsByTermUseCase.execute(term) } returns products

            products.forEachIndexed { index, _ ->
                every { productToProductResponseConverter.convert(products[index]) } returns productResponses[index]
            }

            val result = controller.findProductsByTerm(term)

            assertThat(result.status, equalTo(HttpStatus.OK))
            assertThat(result.body(), notNullValue())
            assertThat(result.body()?.products, `is`(equalTo(productResponses)))

            verify(exactly = 1) { findProductsByTermUseCase.execute(term) }
            products.forEachIndexed { index, _ ->
                verify(exactly = 1) { productToProductResponseConverter.convert(products[index]) }
            }
        }
    }
})