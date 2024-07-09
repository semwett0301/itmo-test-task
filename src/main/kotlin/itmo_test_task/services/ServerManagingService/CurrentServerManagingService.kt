package itmo_test_task.services.ServerManagingService

import itmo_test_task.model.dto.request.CreateServerRequest
import itmo_test_task.model.dto.request.UpdateServerRequest
import itmo_test_task.model.dto.response.ServerResponse
import itmo_test_task.model.entity.Server
import itmo_test_task.model.mappers.ServerMapper
import itmo_test_task.repositories.DepartmentRepository
import itmo_test_task.repositories.ServerRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CurrentServerManagingService(val serverRepository: ServerRepository, val departmentRepository: DepartmentRepository): ServerManagingService {
    override fun createServer(serverRequest: CreateServerRequest) {
        TODO("Not yet implemented")
    }

    override fun updateServer(serverRequest: UpdateServerRequest, serverId: UUID) {
        TODO("Not yet implemented")
    }

    override fun getServersByDepartmentId(departmentId: UUID): MutableList<ServerResponse> {
        val department = departmentRepository.findById(departmentId)
            .orElseThrow { EntityNotFoundException("department with Id:$departmentId not found ") }

        return department.employees
            .fold(mutableListOf<Server>()) { acc, employee ->
                acc.addAll(employee.servers)
                acc
            }
            .map { ServerMapper.INSTANCE.toServerDto(it) }
            .toMutableList()
    }
}