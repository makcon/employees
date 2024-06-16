package test.makcon.employees.domain.port

import test.makcon.api.commons.domain.model.Author
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.domain.model.Employee

interface EmployeeRepositoryPort {

    fun create(employee: VersionedModel<Employee>, author: Author)
}