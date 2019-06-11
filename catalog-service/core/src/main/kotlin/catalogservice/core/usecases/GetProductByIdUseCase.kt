package catalogservice.core.usecases

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway

class GetProductByIdUseCase(private val productsGateway: ProductsGateway) {
    fun execute(id: String): Product? {
        return productsGateway.getById(id)
    }
}
