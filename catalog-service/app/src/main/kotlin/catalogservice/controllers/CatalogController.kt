package catalogservice.controllers

import catalogservice.contract.contracts.FindProductsByTermContract
import catalogservice.contract.converters.ProductToProductResponseConverter
import catalogservice.contract.domains.ProductResponse
import catalogservice.core.usecases.FindProductsByTermUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/catalog")
class CatalogController @Inject constructor(
    private val findProductsByTermUseCase: FindProductsByTermUseCase,
    private val productToProductResponseConverter: ProductToProductResponseConverter
) : FindProductsByTermContract {

    @Get("/search/{term}")
    override fun findProductsByTerm(term: String): HttpResponse<List<ProductResponse>> {
        return HttpResponse.ok(
            findProductsByTermUseCase
                .execute(term)
                .map(productToProductResponseConverter::convert)
        )
    }
}