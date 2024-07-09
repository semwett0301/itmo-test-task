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
        val server = ServerMapper.INSTANCE.toServer(serverRequest)
        serverRepository.save(server)
    }

    override fun updateServer(serverRequest: UpdateServerRequest, serverId: UUID) {
        val server = serverRepository.findById(serverId)
            .orElseThrow { EntityNotFoundException("server with Id:$serverId not found ") }

        server.apply {
            name = serverRequest.name ?: name
            producer = serverRequest.producer ?: producer
            ip = serverRequest.ip ?: ip
            ram = serverRequest.ram ?: ram
            ssd = serverRequest.ssd ?: ssd
        }
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