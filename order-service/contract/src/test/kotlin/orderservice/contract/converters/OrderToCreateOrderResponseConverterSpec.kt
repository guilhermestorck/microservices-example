import orderservice.contract.converters.OrderToCreateOrderResponseConverter
import orderservice.core.domains.Order
import orderservice.core.domains.OrderStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class OrderToCreateOrderResponseConverterSpec : Spek({

    val converter by memoized { OrderToCreateOrderResponseConverter() }

    describe("#convert") {

        it("should convert successfully") {
            val order = Order(
                id = "the-id",
                total = 12.0,
                status = OrderStatus.IN_PROGRESS
            )

            val result = converter.convert(order)

            assertEquals(result.id, "the-id")
            assertEquals(result.total, 12.0)
        }

        it("should throw NPE for null fields") {
            val order = Order(
                id = null,
                total = 12.0,
                status = OrderStatus.IN_PROGRESS
            )

            assertThrows<NullPointerException> {
                converter.convert(order)
            }
        }
    }
})