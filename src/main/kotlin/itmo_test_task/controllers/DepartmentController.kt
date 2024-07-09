package itmo_test_task.controllers

import itmo_test_task.model.dto.response.ServerResponse
import itmo_test_task.services.ServerManagingService.ServerManagingService
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin("*")
@RestController
@RequestMapping("/departments")
class DepartmentController(val serverManagingService: ServerManagingService) {
    @PostMapping("/{id}/servers")
    fun getDepartmentServers(@PathVariable id: UUID): MutableList<ServerResponse>? {
        return serverManagingService.getServersByDepartmentId(id);
    }
}