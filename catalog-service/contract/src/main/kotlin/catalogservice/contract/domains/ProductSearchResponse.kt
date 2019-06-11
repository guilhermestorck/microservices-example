package catalogservice.contract.domains

data class ProductSearchResponse(val products: List<ProductResponse>, val errors: ErrorResponse? = null)