package catalogservice.core.usecases

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway

class FindProductsByTermUseCase(private val productsGateway: ProductsGateway) {
    fun execute(term: String): List<Product> {
        return productsGateway.findByTerm(term);
    }
}
