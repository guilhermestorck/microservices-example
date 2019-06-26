package orderservice.clients

import catalogservice.contract.contracts.GetProductByIdContract
import catalogservice.contract.domains.ProductResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client(id = "catalog-service")
interface CatalogClient : GetProductByIdContract {

    @Get("/catalog/products/{id}")
    override fun getProductById(@PathVariable id: String): HttpResponse<ProductResponse>
}