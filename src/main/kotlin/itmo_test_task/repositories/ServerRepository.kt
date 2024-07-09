package itmo_test_task.repositories

import itmo_test_task.model.entity.Server
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ServerRepository : JpaRepository<Server, UUID> {
}