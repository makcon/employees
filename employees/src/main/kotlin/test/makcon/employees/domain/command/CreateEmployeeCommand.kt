package test.makcon.employees.domain.command

import test.makcon.api.commons.domain.model.Author
import test.makcon.employees.domain.model.Employee

data class CreateEmployeeCommand(
    val author: Author,
    val data: Employee
)
