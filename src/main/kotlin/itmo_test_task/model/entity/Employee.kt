package itmo_test_task.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "employees")
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    var department: Department? = null,

    @OneToMany(mappedBy = "employee", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var servers: MutableList<Server> = mutableListOf()
) {
    constructor() : this(UUID.randomUUID(), "")
}