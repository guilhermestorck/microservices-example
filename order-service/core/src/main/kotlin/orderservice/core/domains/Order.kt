package orderservice.core.domains

data class Order(val id: String?, val total: Double, val status: OrderStatus)

