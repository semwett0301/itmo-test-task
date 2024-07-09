package itmo_test_task.services.EmployeeManagingService

import itmo_test_task.model.dto.response.EmployeeResponse
import itmo_test_task.model.entity.Employee
import itmo_test_task.model.mappers.EmployeeMapper
import itmo_test_task.repositories.CompanyRepository
import itmo_test_task.repositories.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CurrentEmployeeManagingService(
    val companyRepository: CompanyRepository,
    val employeeRepository: EmployeeRepository
) : EmployeeManagingService {
    override fun deleteEmployeeById(id: UUID) {
        employeeRepository.deleteById(id)
    }

    override fun getEmployeesByCompanyId(companyId: UUID): MutableList<EmployeeResponse> {
        val company = companyRepository.findById(companyId)
            .orElseThrow { EntityNotFoundException("company with Id:$companyId not found ") }
        return company.departments
            .fold(mutableListOf<Employee>()) { acc, department ->
                acc.addAll(department.employees)
                acc
            }
            .map { EmployeeMapper.INSTANCE.toEmployeeDto(it) }
            .toMutableList()
    }

}