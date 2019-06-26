package orderservice.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import orderservice.contract.contracts.CreateOrderContract
import orderservice.contract.converters.OrderToCreateOrderResponseConverter
import orderservice.contract.domains.createorder.CreateOrderRequest
import orderservice.contract.domains.createorder.CreateOrderResponse
import orderservice.core.usecases.CreateOrderUseCase

@Controller("/orders")
class OrdersController(
    private val createOrderUseCase: CreateOrderUseCase,
    private val orderToCreateOrderResponseConverter: OrderToCreateOrderResponseConverter
) : CreateOrderContract {

    @Post("/")
    override fun createOrder(@Body order: CreateOrderRequest): HttpResponse<CreateOrderResponse> {
        val createdOrder = createOrderUseCase.execute(order.productIds)
        return HttpResponse.ok(
            orderToCreateOrderResponseConverter.convert(createdOrder)
        )
    }
}