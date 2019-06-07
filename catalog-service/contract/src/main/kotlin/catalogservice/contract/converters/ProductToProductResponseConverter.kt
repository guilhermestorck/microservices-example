package catalogservice.contract.converters

import catalogservice.contract.domains.ProductResponse
import catalogservice.core.domains.Product

class ProductToProductResponseConverter {

    fun convert(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            description = product.description,
            price = product.price
        )
    }
}
