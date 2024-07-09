package itmo_test_task.model.dto.response

import itmo_test_task.model.dto.base.BaseServer

data class ServerResponse(
    override val name: String,
    override val producer: String,
    override val ip: String,
    override val ram: Int,
    override val ssd: Int
): BaseServer;
