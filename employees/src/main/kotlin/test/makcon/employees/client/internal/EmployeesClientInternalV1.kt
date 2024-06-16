package test.makcon.employees.client.internal

import org.springframework.stereotype.Component
import test.makcon.api.commons.mapper.toModel
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.mapper.toModel
import test.makcon.employees.client.request.CreateEmployeeRequestV1
import test.makcon.employees.domain.command.CreateEmployeeCommand
import test.makcon.employees.domain.command.handler.CreateEmployeeCommandHandler
import java.util.*

@Component
class EmployeesClientInternalV1(
    private val createCommandHandler: CreateEmployeeCommandHandler,
) : EmployeesClientV1 {

    override fun create(request: CreateEmployeeRequestV1): UUID = createCommandHandler.handle(
        CreateEmployeeCommand(
            author = request.author.toModel(),
            data = request.data.toModel()
        )
    )
}