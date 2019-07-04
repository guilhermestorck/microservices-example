package catalogservice.db.domains


import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: String,

    @Column
    @NotNull
    val description: String,

    @Column
    @NotNull
    val price: Double
)