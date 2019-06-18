package catalogservice.conf.injection

import catalogservice.core.gateways.ProductsGateway
import catalogservice.core.usecases.FindProductsByTermUseCase
import catalogservice.core.usecases.GetProductByIdUseCase
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class CoreUseCasesInjector {

    @Singleton
    fun findProductsByTermUseCase(productsGateway: ProductsGateway): FindProductsByTermUseCase {
        return FindProductsByTermUseCase(productsGateway)
    }

    @Singleton
    fun getProductByIdUseCase(productsGateway: ProductsGateway): GetProductByIdUseCase {
        return GetProductByIdUseCase(productsGateway)
    }
}