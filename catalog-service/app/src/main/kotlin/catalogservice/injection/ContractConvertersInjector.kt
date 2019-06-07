package catalogservice.injection

import catalogservice.contract.converters.ProductToProductResponseConverter
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ContractConvertersInjector {

    @Singleton
    fun productToProductResponseConverter(): ProductToProductResponseConverter {
        return ProductToProductResponseConverter()
    }
}