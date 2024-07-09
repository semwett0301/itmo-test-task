package itmo_test_task.model.dto.base

import java.util.*

interface BaseServer {
    val name: String?
    val producer: String?
    val ip: String?
    val ram: Int?
    val ssd: Int?
    val employee_id: UUID?
}