package catalogservice.core.gateways

import catalogservice.core.domains.Product

interface ProductsGateway {

    fun findByTerm(term: String): List<Product>

    fun getById(id: String): Product?
}