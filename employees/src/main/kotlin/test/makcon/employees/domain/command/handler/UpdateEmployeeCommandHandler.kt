package test.makcon.employees.domain.command.handler

import org.springframework.stereotype.Component
import test.makcon.employees.domain.command.UpdateEmployeeCommand
import test.makcon.employees.domain.port.EmployeeRepositoryPort
import java.time.Instant

@Component
class UpdateEmployeeCommandHandler(private val repository: EmployeeRepositoryPort) {

    fun handle(command: UpdateEmployeeCommand) {
        val storedEmployee = repository.getById(command.employeeId)

        repository.update(
            employee = storedEmployee.copy(
                version = command.version,
                updatedAt = Instant.now(),
                data = command.data,
            ),
            author = command.author
        )
    }
}