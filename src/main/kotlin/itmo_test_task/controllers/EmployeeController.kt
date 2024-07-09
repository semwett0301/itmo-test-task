package itmo_test_task.controllers

import itmo_test_task.services.EmployeeManagingService.EmployeeManagingService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@CrossOrigin("*")
@RestController
@RequestMapping("/employees")
class EmployeeController(val employeeManagingService: EmployeeManagingService) {
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: UUID) {
        employeeManagingService.deleteEmployeeById(id)
    }
}