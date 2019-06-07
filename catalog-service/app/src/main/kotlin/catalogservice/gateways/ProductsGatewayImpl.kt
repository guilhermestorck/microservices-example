package catalogservice.gateways

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class ProductsGatewayImpl : ProductsGateway {

    private val random = Random(7)

    override fun findByTerm(term: String): List<Product> {
        return listOf(createProduct(term), createProduct(term + "AAAA"))
    }

    private fun createProduct(term: String) = Product(
        id = "id-${term}",
        description = term,
        price = random.nextDouble()
    )
}