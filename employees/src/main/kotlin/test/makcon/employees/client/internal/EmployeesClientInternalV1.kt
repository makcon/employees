package test.makcon.employees.client.internal

import org.springframework.stereotype.Component
import test.makcon.api.commons.dto.VersionedModelV1
import test.makcon.api.commons.dto.response.PageResponseV1
import test.makcon.api.commons.mapper.toDto
import test.makcon.api.commons.mapper.toModel
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.mapper.toDto
import test.makcon.employees.client.mapper.toModel
import test.makcon.employees.client.request.CreateEmployeeRequestV1
import test.makcon.employees.client.request.FindEmployeesRequestV1
import test.makcon.employees.client.request.GetEmployeeRequestV1
import test.makcon.employees.client.request.UpdateEmployeeRequestV1
import test.makcon.employees.domain.command.CreateEmployeeCommand
import test.makcon.employees.domain.command.UpdateEmployeeCommand
import test.makcon.employees.domain.command.handler.CreateEmployeeCommandHandler
import test.makcon.employees.domain.command.handler.UpdateEmployeeCommandHandler
import test.makcon.employees.domain.query.FindEmployeeQuery
import test.makcon.employees.domain.query.GetEmployeeQuery
import test.makcon.employees.domain.query.handler.GetEmployeeQueryHandler
import test.makcon.employees.dto.EmployeeV1
import java.util.*

@Component
class EmployeesClientInternalV1(
    private val createCommandHandler: CreateEmployeeCommandHandler,
    private val updateCommandHandler: UpdateEmployeeCommandHandler,
    private val getQueryHandler: GetEmployeeQueryHandler,
) : EmployeesClientV1 {

    override fun create(request: CreateEmployeeRequestV1): UUID = createCommandHandler.handle(
        CreateEmployeeCommand(
            author = request.author.toModel(),
            data = request.data.toModel(),
        )
    )

    override fun update(request: UpdateEmployeeRequestV1) = updateCommandHandler.handle(
        UpdateEmployeeCommand(
            employeeId = request.employeeId,
            version = request.version,
            author = request.author.toModel(),
            data = request.data.toModel(),
        )
    )

    override fun get(request: GetEmployeeRequestV1): VersionedModelV1<EmployeeV1> = getQueryHandler
        .handle(GetEmployeeQuery(request.employeeId))
        .toDto()

    override fun find(request: FindEmployeesRequestV1): PageResponseV1<VersionedModelV1<EmployeeV1>> = getQueryHandler
        .handle(FindEmployeeQuery(request.params.toModel()))
        .toDto { it.toDto() }
}