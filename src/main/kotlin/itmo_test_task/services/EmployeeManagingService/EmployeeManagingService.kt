package itmo_test_task.services.EmployeeManagingService

import itmo_test_task.model.dto.response.EmployeeResponse
import java.util.UUID

interface EmployeeManagingService {
    fun deleteEmployeeById(id: UUID)

    fun getEmployeesByCompanyId(companyId: UUID): MutableList<EmployeeResponse>
}