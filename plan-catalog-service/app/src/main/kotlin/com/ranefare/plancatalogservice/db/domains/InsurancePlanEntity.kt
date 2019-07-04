package com.ranefare.plancatalogservice.db.domains


import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "INSURANCE_PLANS")
data class InsurancePlanEntity(
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: String?,

    @Column(name = "NAME", nullable = false, length = 100)
    @NotNull
    val name: String,

    @Column(name = "COST_RATE")
    @NotNull
    val costRate: Double,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "INSURANCE_PLANS_INSURANCE_COVERAGE_ITEMS",
        joinColumns = [JoinColumn(name = "INSURANCE_PLAN_ID", referencedColumnName = "ID")],
        inverseJoinColumns = [JoinColumn(name = "INSURANCE_COVERAGE_ITEM_ID", referencedColumnName = "ID")]
    )
    val coverageItems: List<InsuranceCoverageItemEntity>
)