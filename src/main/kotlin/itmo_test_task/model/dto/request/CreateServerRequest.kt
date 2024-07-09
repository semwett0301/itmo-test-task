package itmo_test_task.model.dto.request

import itmo_test_task.model.dto.base.BaseServer
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import java.util.UUID

@Validated
data class CreateServerRequest(
    override val name: String,
    override val producer: String,
    override val ip: String,

    @field:Min(value = 1, message = "Ram must be at least 1")
    override val ram: Int,

    @field:Min(value = 1, message = "SSD must be at least 1")
    override val ssd: Int,
    override val employee_id: UUID,
) : BaseServer;