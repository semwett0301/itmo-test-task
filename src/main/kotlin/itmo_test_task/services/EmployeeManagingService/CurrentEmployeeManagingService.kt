package itmo_test_task.services.EmployeeManagingService

import itmo_test_task.model.dto.response.EmployeeResponse
import itmo_test_task.model.entity.Employee
import itmo_test_task.model.mappers.EmployeeMapper
import itmo_test_task.repositories.CompanyRepository
import itmo_test_task.repositories.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CurrentEmployeeManagingService(
    val companyRepository: CompanyRepository,
    val employeeRepository: EmployeeRepository
) : EmployeeManagingService {
    override fun deleteEmployeeById(id: UUID) {
        val employee =
            employeeRepository.findById(id).orElseThrow { EntityNotFoundException("employee with Id:$id not found ") }

        val departmentEmployeesAmount = employee.department?.employees?.size ?: 0

        if (departmentEmployeesAmount > 1) {
            employeeRepository.deleteById(employee.id)
        } else {
            throw IllegalArgumentException("employee with Id:$id can't be deleted ")
        }
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