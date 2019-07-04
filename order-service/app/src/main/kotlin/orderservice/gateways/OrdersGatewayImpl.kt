package orderservice.gateways

import orderservice.core.domains.Order
import orderservice.core.gateways.OrdersGateway
import orderservice.db.domains.OrderEntity
import javax.inject.Singleton

@Singleton
class OrdersGatewayImpl : OrdersGateway {

    private var orders = mutableListOf<String>()

    override fun createOrder(order: Order): String {
        OrderEntity.list()
        orders.add("id-${'A' + orders.size}")
        return orders.last()
    }
}