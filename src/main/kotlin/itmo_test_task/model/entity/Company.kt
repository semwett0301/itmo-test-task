package itmo_test_task.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "companies")
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    var name: String,

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var departments: MutableList<Department> = mutableListOf()
) {
    constructor() : this(UUID.randomUUID(), "")
}