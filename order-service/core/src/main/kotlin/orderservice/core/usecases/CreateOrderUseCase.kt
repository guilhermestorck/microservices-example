package orderservice.core.usecases

import orderservice.core.domains.Order
import orderservice.core.domains.OrderStatus
import orderservice.core.gateways.CatalogGateway
import orderservice.core.gateways.OrdersGateway

class CreateOrderUseCase(
    private val ordersGateway: OrdersGateway,
    private val catalogGateway: CatalogGateway
) {

    fun execute(productIds: List<String>): Order {
        val order = Order(
            id = null,
            status = OrderStatus.IN_PROGRESS,
            total = productIds
                .map(catalogGateway::getPriceById)
                .fold(0.0) { total, price ->
                    total + price
                }
        )
        return order.copy(id = ordersGateway.createOrder(order))
    }
}