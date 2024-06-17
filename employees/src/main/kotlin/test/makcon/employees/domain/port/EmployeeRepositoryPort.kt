package test.makcon.employees.domain.port

import test.makcon.api.commons.domain.model.Author
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.domain.query.FindEmployeeQuery
import java.util.*

interface EmployeeRepositoryPort {

    fun create(employee: VersionedModel<Employee>, author: Author)

    fun update(employee: VersionedModel<Employee>, author: Author)

    fun getById(employeeId: UUID): VersionedModel<Employee>

    fun find(query: FindEmployeeQuery): PageResponse<VersionedModel<Employee>>
}