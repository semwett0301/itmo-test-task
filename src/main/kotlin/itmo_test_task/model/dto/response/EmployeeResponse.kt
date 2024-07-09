package itmo_test_task.model.dto.response

import java.util.UUID

data class EmployeeResponse(
    val id: UUID,
    val name: String
)