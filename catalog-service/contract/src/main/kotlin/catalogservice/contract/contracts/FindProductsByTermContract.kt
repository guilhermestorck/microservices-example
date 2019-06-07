package catalogservice.contract.contracts

import catalogservice.contract.domains.ProductResponse
import io.micronaut.http.HttpResponse

interface FindProductsByTermContract {

    fun findProductsByTerm(term: String): HttpResponse<List<ProductResponse>>
}
