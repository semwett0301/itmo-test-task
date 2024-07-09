package itmo_test_task.model.mappers

import itmo_test_task.model.dto.request.CreateServerRequest
import itmo_test_task.model.dto.response.ServerResponse
import itmo_test_task.model.entity.Server
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ServerMapper {
    fun toServerDto(server: Server): ServerResponse

    fun toServer(createServerRequest: CreateServerRequest): Server

    companion object {
        val INSTANCE: ServerMapper = Mappers.getMapper(ServerMapper::class.java)
    }
}