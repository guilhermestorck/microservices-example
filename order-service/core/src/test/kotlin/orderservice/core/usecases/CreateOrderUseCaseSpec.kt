import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import orderservice.core.domains.Order
import orderservice.core.domains.OrderStatus
import orderservice.core.gateways.CatalogGateway
import orderservice.core.gateways.OrdersGateway
import orderservice.core.usecases.CreateOrderUseCase
import org.junit.jupiter.api.Assertions.assertEquals

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CreateOrderUseCaseSpec : Spek({

    val ordersGateway by memoized { mockk<OrdersGateway>() }
    val catalogGateway by memoized { mockk<CatalogGateway>() }

    val useCase by memoized { CreateOrderUseCase(ordersGateway, catalogGateway) }

    describe("#execute") {

        it("should create order") {
            val productIds = listOf("id-A", "id-B")
            val orderSlot = slot<Order>()
            val order = Order(
                id = null,
                status = OrderStatus.IN_PROGRESS,
                total = 32.0
            )

            every { catalogGateway.getPriceById("id-A") } returns 15.0
            every { catalogGateway.getPriceById("id-B") } returns 17.0

            every {
                ordersGateway.createOrder(capture(orderSlot))
            } answers {
                assertEquals(order, orderSlot.captured)
                "order-A"
            }

            val result = useCase.execute(productIds)

            assertEquals(result.total, 32.0)
            assertEquals(result.id, "order-A")
            assertEquals(result.status, OrderStatus.IN_PROGRESS)

            verify(exactly = 2) { catalogGateway.getPriceById(any()) }
            verify(exactly = 1) { ordersGateway.createOrder(order) }
        }
    }

})