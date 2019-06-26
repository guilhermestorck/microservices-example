package orderservice.core.gateways

import orderservice.core.domains.exceptions.CatalogIntegrationException

interface CatalogGateway {

    @Throws(CatalogIntegrationException::class)
    fun getPriceById(id: String): Double
}