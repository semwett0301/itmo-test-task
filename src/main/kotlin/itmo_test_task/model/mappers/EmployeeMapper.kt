package itmo_test_task.model.mappers

import itmo_test_task.model.dto.response.EmployeeResponse
import itmo_test_task.model.entity.Employee
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface EmployeeMapper {
    fun toEmployeeDto(employee: Employee): EmployeeResponse

    companion object {
        val INSTANCE: EmployeeMapper = Mappers.getMapper(EmployeeMapper::class.java)
    }
}