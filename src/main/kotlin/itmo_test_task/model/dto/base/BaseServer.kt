package itmo_test_task.model.dto.base

interface BaseServer {
    val name: String?
    val producer: String?
    val ip: String?
    val ram: Int?
    val ssd: Int?
}