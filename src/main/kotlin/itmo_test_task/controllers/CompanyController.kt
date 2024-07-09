package itmo_test_task.controllers

import itmo_test_task.model.dto.response.EmployeeResponse
import itmo_test_task.services.EmployeeManagingService.EmployeeManagingService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@CrossOrigin("*")
@RestController
@RequestMapping("/companies")
class CompanyController(val employeeManagingService: EmployeeManagingService) {
    @PostMapping("/{id}/employees")
    fun getCompanyEmployees(@PathVariable id: UUID): MutableList<EmployeeResponse>? {
        return employeeManagingService.getEmployeesByCompanyId(id)
    }
}