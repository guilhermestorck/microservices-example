package orderservice.conf.injection

import io.micronaut.context.annotation.Factory
import orderservice.core.gateways.CatalogGateway
import orderservice.core.gateways.OrdersGateway
import orderservice.core.usecases.CreateOrderUseCase
import javax.inject.Singleton

@Factory
class CoreUseCasesInjector {

    @Singleton
    fun createOrderUseCase(ordersGateway: OrdersGateway, catalogGateway: CatalogGateway): CreateOrderUseCase {
        return CreateOrderUseCase(ordersGateway, catalogGateway)
    }
}