package catalogservice.db.repositories

import catalogservice.db.domains.ProductEntity
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
open class ProductRepository(@param:CurrentSession @field:PersistenceContext val entityManager: EntityManager) {

    @Transactional
    open fun getById(id: String): ProductEntity {
        return entityManager.find(ProductEntity::class.java, id)
    }
}