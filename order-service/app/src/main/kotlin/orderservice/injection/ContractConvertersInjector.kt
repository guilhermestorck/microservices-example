package orderservice.injection

import io.micronaut.context.annotation.Factory
import orderservice.contract.converters.OrderToCreateOrderResponseConverter
import javax.inject.Singleton

@Factory
class ContractConvertersInjector {

    @Singleton
    fun orderToCreateOrderResponseConverter(): OrderToCreateOrderResponseConverter {
        return OrderToCreateOrderResponseConverter()
    }
}