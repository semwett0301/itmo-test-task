package itmo_test_task.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "departments")
class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company? = null,

    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var employees: MutableList<Employee> = mutableListOf()
) {
    constructor() : this(UUID.randomUUID(), "")
}