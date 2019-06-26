import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import orderservice.contract.converters.OrderToCreateOrderResponseConverter
import orderservice.contract.domains.createorder.CreateOrderRequest
import orderservice.contract.domains.createorder.CreateOrderResponse
import orderservice.controllers.OrdersController
import orderservice.core.domains.Order
import orderservice.core.usecases.CreateOrderUseCase
import org.junit.jupiter.api.Assertions.assertEquals

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class OrdersControllerSpec : Spek({

    val createOrderUseCase by memoized { mockk<CreateOrderUseCase>() }
    val orderToCreateOrderResponseConverter by memoized { mockk<OrderToCreateOrderResponseConverter>() }

    val controller by memoized { OrdersController(createOrderUseCase, orderToCreateOrderResponseConverter) }

    describe("#createOrder") {

        it("should handle a createOrder request") {
            val request = mockk<CreateOrderRequest>()
            val productIds = listOf("id-A", "id-B")
            val createdOrder = mockk<Order>()
            val orderResponse = mockk<CreateOrderResponse>()

            every { request.productIds } returns productIds
            every { createOrderUseCase.execute(productIds) } returns createdOrder
            every { orderToCreateOrderResponseConverter.convert(createdOrder) } returns orderResponse

            val result = controller.createOrder(request)

            assertEquals(result.status(), HttpStatus.OK)
            assertEquals(result.body(), orderResponse)
        }
    }
})