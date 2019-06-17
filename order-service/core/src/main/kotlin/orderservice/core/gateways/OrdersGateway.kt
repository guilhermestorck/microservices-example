package orderservice.core.gateways

import orderservice.core.domains.Order

interface OrdersGateway {

    fun createOrder(order: Order): String
}