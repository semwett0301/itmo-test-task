package itmo_test_task.services.ServerManagingService

import itmo_test_task.model.dto.request.CreateServerRequest
import itmo_test_task.model.dto.request.UpdateServerRequest
import itmo_test_task.model.dto.response.ServerResponse
import java.util.UUID

interface ServerManagingService {
    fun createServer(serverRequest: CreateServerRequest)

    fun updateServer(serverRequest: UpdateServerRequest, serverId: UUID)

    fun getServersByDepartmentId(departmentId: UUID): MutableList<ServerResponse>
}