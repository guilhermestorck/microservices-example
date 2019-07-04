package com.ranefare.plancatalogservice.db.repositories

import com.ranefare.plancatalogservice.core.repositories.CrudRepository
import com.ranefare.plancatalogservice.db.assemblers.EntityAssembler
import com.ranefare.plancatalogservice.db.converters.EntityConverter
import io.micronaut.spring.tx.annotation.Transactional
import org.hibernate.Session
import javax.persistence.EntityManager


open class CrudRepositoryImpl<D, E, ID>(
    private val entityManager: EntityManager,
    private val entityClass: Class<E>,
    private val entityConverter: EntityConverter<D, E>,
    private val entityAssembler: EntityAssembler<E, D>
) : CrudRepository<D, ID> {

    @Transactional
    override fun save(resource: D): D {
        val entity = entityConverter.convert(resource)
        (entityManager as Session).saveOrUpdate(entity)
        return entityAssembler.assemble(entity)
    }

    @Transactional
    override fun get(id: ID): D {
        return entityAssembler.assemble(entityManager.find(entityClass, id))
    }

    @Transactional
    override fun getAll(): List<D> {
        val criteriaQuery = entityManager.criteriaBuilder.createQuery(entityClass)
        val selection = criteriaQuery.from(entityClass)
        val query = criteriaQuery.select(selection)

        return entityManager.createQuery(query).resultList.map(entityAssembler::assemble)
    }
}