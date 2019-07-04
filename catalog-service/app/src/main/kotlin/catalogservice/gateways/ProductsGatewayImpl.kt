package catalogservice.gateways

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import catalogservice.db.repositories.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsGatewayImpl @Inject constructor(
    private val productRepository: ProductRepository
) : ProductsGateway {

    override fun findByTerm(term: String): List<Product> {
        return listOf(createProduct(term), createProduct(term + "AAAA"))
    }

    override fun getById(id: String): Product? {
        val productEntity = productRepository.getById(id)
        return Product(
            id = productEntity.id,
            description = productEntity.description,
            price = productEntity.price
        )
    }

    private fun createProduct(term: String) = Product(
        id = "id-${term}",
        description = term,
        price = 3.0
    )
}