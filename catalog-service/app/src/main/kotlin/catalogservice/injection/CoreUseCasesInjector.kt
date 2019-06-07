package catalogservice.injection

import catalogservice.core.gateways.ProductsGateway
import catalogservice.core.usecases.FindProductsByTermUseCase
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class CoreUseCasesInjector {

    @Singleton
    fun findProductsByTermUseCase(productsGateway: ProductsGateway): FindProductsByTermUseCase {
        return FindProductsByTermUseCase(productsGateway)
    }
}