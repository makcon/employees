package test.makcon.employees.client.mapper

import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.api.commons.dto.VersionedModelV1
import test.makcon.api.commons.mapper.toDto
import test.makcon.api.commons.mapper.toModel
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.dto.EmployeeV1

fun EmployeeV1.toModel() = Employee(
    name = name,
    position = position,
    email = email,
    salary = salary.toModel(::salary),
)

fun VersionedModel<Employee>.toDto() = VersionedModelV1(
    id = id,
    version = version,
    createdAt = createdAt,
    updatedAt = updatedAt,
    data = data.toDto(),
)

fun Employee.toDto() = EmployeeV1(
    name = name,
    position = position,
    email = email,
    salary = salary.toDto(),
)