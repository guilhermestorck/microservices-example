package orderservice.db.domains

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class OrderEntity(
    val id: Long,

    val total: Double
) : GormEntity<OrderEntity> {

}