package itmo_test_task.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import java.util.*

@Entity
@Table(name = "servers")
class Server(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    var name: String,
    var producer: String,
    var ip: String,

    @Min(1)
    var ram: Int,

    @Min(1)
    var ssd: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    var employee: Employee? = null,
) {
    constructor() : this(UUID.randomUUID(), "", "", "", 0, 0)
}