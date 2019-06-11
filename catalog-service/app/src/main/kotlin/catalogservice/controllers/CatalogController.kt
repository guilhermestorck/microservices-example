package catalogservice.controllers

import catalogservice.contract.contracts.FindProductsByTermContract
import catalogservice.contract.contracts.GetProductByIdContract
import catalogservice.contract.converters.ProductToProductResponseConverter
import catalogservice.contract.domains.ProductResponse
import catalogservice.contract.domains.ProductSearchResponse
import catalogservice.core.usecases.FindProductsByTermUseCase
import catalogservice.core.usecases.GetProductByIdUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/catalog")
class CatalogController @Inject constructor(
    private val findProductsByTermUseCase: FindProductsByTermUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val productToProductResponseConverter: ProductToProductResponseConverter
) : FindProductsByTermContract, GetProductByIdContract {

    @Get("/products/{id}")
    override fun getProductById(id: String): HttpResponse<ProductResponse> {
        val product = getProductByIdUseCase.execute(id)
        return if (product != null) {
            HttpResponse.ok(productToProductResponseConverter.convert(product = product))
        } else {
            HttpResponse.notFound()
        }
    }

    @Get("/search/{term}")
    override fun findProductsByTerm(term: String): HttpResponse<ProductSearchResponse> {
        return HttpResponse.ok(
            ProductSearchResponse(
                products = findProductsByTermUseCase
                    .execute(term)
                    .map(productToProductResponseConverter::convert)
            )
        )
    }
}