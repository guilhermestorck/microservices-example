package catalogservice.core.usecases

import catalogservice.core.domains.Product
import catalogservice.core.gateways.ProductsGateway
import spock.lang.Specification

class FindProductsByTermUseCaseTest extends Specification {

    FindProductsByTermUseCase useCase
    ProductsGateway productsGateway


    def setup() {
        productsGateway = Mock(ProductsGateway)
        useCase = new FindProductsByTermUseCase(productsGateway)
    }

    def "should call the gateway with the right params"() {
        given:
        def products = [
            new Product("id", "descr", 12.0),
            new Product("id", "descr", 12.0)
        ]

        when:
        def result = useCase.execute("the-term")

        then:
        1 * productsGateway.findByTerm("the-term") >> products

        and:
        assert result == products
    }
}
