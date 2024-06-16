package test.makcon.employees.client.mapper

import test.makcon.api.commons.mapper.toModel
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.dto.EmployeeV1

fun EmployeeV1.toModel() = Employee(
    name = name,
    position = position,
    email = email,
    salary = salary.toModel(::salary),
)