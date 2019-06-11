package catalogservice.contract.contracts

import catalogservice.contract.domains.ProductResponse
import io.micronaut.http.HttpResponse

interface GetProductByIdContract {

    fun getProductById(id: String): HttpResponse<ProductResponse>
}
