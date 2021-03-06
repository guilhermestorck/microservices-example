package orderservice.core.domains.exceptions

class CatalogIntegrationException(message: String?, cause: Throwable?) : Exception(message, cause) {

    constructor() : this(null, null)

    constructor(message: String) : this(message, null)

}
