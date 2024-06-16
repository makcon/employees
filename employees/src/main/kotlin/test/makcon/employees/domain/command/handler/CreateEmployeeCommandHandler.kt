package test.makcon.employees.domain.command.handler

import org.springframework.stereotype.Component
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.domain.command.CreateEmployeeCommand
import test.makcon.employees.domain.port.EmployeeRepositoryPort
import java.util.*

@Component
class CreateEmployeeCommandHandler(private val repository: EmployeeRepositoryPort) {

    fun handle(command: CreateEmployeeCommand): UUID {
        val employee = VersionedModel(
            data = command.data
        )

        repository.create(employee, command.author)

        return employee.id
    }
}