package test.makcon.employees.adapter.mapper

import test.makcon.api.commons.adapter.mapper.toDoc
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.doc.EmployeeDocData
import test.makcon.employees.domain.model.Employee

fun VersionedModel<Employee>.toDoc() = EmployeeDoc(
    id = id,
    version = version,
    createdAt = createdAt,
    updatedAt = updatedAt,
    data = data.toDoc(),
)

fun Employee.toDoc() = EmployeeDocData(
    name = name,
    position = position,
    email = email,
    salary = salary.toDoc(),
)