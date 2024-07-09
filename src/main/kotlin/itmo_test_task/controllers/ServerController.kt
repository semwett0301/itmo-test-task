package itmo_test_task.controllers

import itmo_test_task.model.dto.request.ServerRequest
import itmo_test_task.services.ServerManagingService.ServerManagingService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin("*")
@RestController
@RequestMapping("/servers")
class ServerController(val serverManagingService: ServerManagingService) {
    @PostMapping
    fun createServer(@Valid @RequestBody serverRequest: ServerRequest) {
        serverManagingService.createServer(serverRequest);
    }

    @PatchMapping("/{id}")
    fun updateServer(@Valid @RequestBody serverRequest: ServerRequest, @PathVariable id: UUID) {
        serverManagingService.updateServer(serverRequest, id)
    }
}