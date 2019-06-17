package orderservice.gateways

import orderservice.clients.CatalogClient
import orderservice.core.exceptions.CatalogIntegrationException
import orderservice.core.gateways.CatalogGateway
import javax.inject.Singleton

@Singleton
class CatalogGatewayImpl(private val catalogClient: CatalogClient) : CatalogGateway {

    override fun getPriceById(id: String): Double {
        val responseBody = catalogClient.getProductById(id).body()
        if (responseBody != null) {
            return responseBody.price
        }
        throw CatalogIntegrationException()
    }
}