package com.ranefare.plancatalogservice.db.domains


import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "INSURANCE_COVERAGE_ITEMS")
data class InsuranceCoverageItemEntity(
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: String?,

    @Column(name = "NAME", nullable = false, length = 100)
    @NotNull
    val name: String,

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    @NotNull
    val description: String,

    @ManyToMany(mappedBy = "coverageItems")
    val insurancePlans: List<InsurancePlanEntity>
)