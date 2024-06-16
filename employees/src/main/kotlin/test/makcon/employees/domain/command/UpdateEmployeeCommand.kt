package test.makcon.employees.domain.command

import test.makcon.api.commons.domain.model.Author
import test.makcon.employees.domain.model.Employee
import java.util.*

data class UpdateEmployeeCommand(
    val employeeId: UUID,
    val version: Long,
    val author: Author,
    val data: Employee
)
