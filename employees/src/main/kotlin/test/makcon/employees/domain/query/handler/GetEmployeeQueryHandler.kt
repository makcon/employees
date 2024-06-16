package test.makcon.employees.domain.query.handler

import org.springframework.stereotype.Component
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.domain.port.EmployeeRepositoryPort
import test.makcon.employees.domain.query.GetEmployeeQuery

@Component
class GetEmployeeQueryHandler(private val repository: EmployeeRepositoryPort) {

    fun handle(query: GetEmployeeQuery): VersionedModel<Employee> = repository.getById(query.employeeId)
}