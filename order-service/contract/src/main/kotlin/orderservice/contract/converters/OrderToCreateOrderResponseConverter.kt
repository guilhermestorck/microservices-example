package orderservice.contract.converters

import orderservice.contract.domains.createorder.CreateOrderResponse
import orderservice.core.domains.Order

class OrderToCreateOrderResponseConverter {

    fun convert(order: Order): CreateOrderResponse {
        return CreateOrderResponse(
            id = order.id!!,
            total = order.total
        )
    }
}