import orderservice.core.domains.Order
import orderservice.core.domains.OrderStatus
import orderservice.gateways.OrdersGatewayImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object OrdersGatewayImplSpec : Spek({

    val gateway by memoized { OrdersGatewayImpl() }

    describe("#createOrder") {

        it("should create orders in sequence") {
            val first = gateway.createOrder(Order(id = null, total = 12.0, status = OrderStatus.IN_PROGRESS))
            val second = gateway.createOrder(Order(id = null, total = 12.0, status = OrderStatus.IN_PROGRESS))

            assertEquals(first, "id-A")
            assertEquals(second, "id-B")
        }
    }
})