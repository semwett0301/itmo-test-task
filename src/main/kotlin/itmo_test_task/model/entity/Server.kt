package itmo_test_task.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "servers")
class Server(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    val name: String,
    val producer: String,
    val ip: String,
    val ram: Int,
    val ssd: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    var employee: Employee? = null,
) {
    constructor() : this(UUID.randomUUID(), "", "", "", 0, 0)
}