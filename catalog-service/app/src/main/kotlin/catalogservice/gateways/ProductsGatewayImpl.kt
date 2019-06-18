package catalogservice.gateways

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import javax.inject.Singleton

@Singleton
class ProductsGatewayImpl : ProductsGateway {

    override fun findByTerm(term: String): List<Product> {
        return listOf(createProduct(term), createProduct(term + "AAAA"))
    }

    override fun getById(id: String): Product? {
        return createProduct(id)
    }

    private fun createProduct(term: String) = Product(
        id = "id-${term}",
        description = term,
        price = 3.0
    )
}