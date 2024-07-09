package itmo_test_task.services.ServerManagingService

import itmo_test_task.model.dto.request.ServerRequest
import itmo_test_task.model.dto.response.ServerResponse
import java.util.UUID

interface ServerManagingService {
    fun createServer(serverRequest: ServerRequest)

    fun updateServer(serverRequest: ServerRequest, serverId: UUID)

    fun getServersByDepartmentId(departmentId: UUID): MutableList<ServerResponse>
}