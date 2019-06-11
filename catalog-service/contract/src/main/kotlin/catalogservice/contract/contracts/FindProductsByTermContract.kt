package catalogservice.contract.contracts

import catalogservice.contract.domains.ProductSearchResponse
import io.micronaut.http.HttpResponse

interface FindProductsByTermContract {

    fun findProductsByTerm(term: String): HttpResponse<ProductSearchResponse>
}
