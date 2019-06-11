package catalogservice.gateways

import spock.lang.Specification

class ProductsGatewayImplSpec extends Specification {

    ProductsGatewayImpl gateway

    def setup() {
        gateway = new ProductsGatewayImpl()
    }

    def "should return a product by id"() {
        when:
        def product = gateway.getById("ID")

        then:
        product.id == "id-ID"
        product.description == "ID"
    }

    def "should return a list of products by term"() {
        when:
        def products = gateway.findByTerm("TERM")

        then:
        products.size() == 2
    }
}
