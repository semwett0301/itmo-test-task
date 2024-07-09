package itmo_test_task.repositories

import itmo_test_task.model.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface EmployeeRepository : JpaRepository<Employee, UUID> {
    @Modifying
    @Query(value = "delete from Employee e where e.id = :id")
    override fun deleteById(id: UUID)
}