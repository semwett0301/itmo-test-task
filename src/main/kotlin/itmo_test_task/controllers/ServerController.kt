package itmo_test_task.controllers

import itmo_test_task.model.dto.request.CreateServerRequest
import itmo_test_task.model.dto.request.UpdateServerRequest
import itmo_test_task.services.ServerManagingService.ServerManagingService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@CrossOrigin("*")
@RestController
@RequestMapping("/servers")
class ServerController(val serverManagingService: ServerManagingService) {
    @PostMapping
    fun createServer(@RequestBody serverRequest: CreateServerRequest) {
        serverManagingService.createServer(serverRequest);
    }

    @PatchMapping("/{id}")
    fun updateServer(@RequestBody serverRequest: UpdateServerRequest, @PathVariable id: UUID) {
        serverManagingService.updateServer(serverRequest, id)
    }
}