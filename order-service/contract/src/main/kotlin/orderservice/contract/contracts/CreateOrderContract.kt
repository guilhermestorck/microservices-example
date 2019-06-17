package orderservice.contract.contracts

import io.micronaut.http.HttpResponse
import orderservice.contract.domains.createorder.CreateOrderRequest
import orderservice.contract.domains.createorder.CreateOrderResponse

interface CreateOrderContract {

    fun createOrder(order: CreateOrderRequest): HttpResponse<CreateOrderResponse>
}
