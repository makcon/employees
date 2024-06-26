package test.makcon.employees.adapter

import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import test.makcon.api.commons.adapter.mapper.toDoc
import test.makcon.api.commons.adapter.mapper.toModel
import test.makcon.api.commons.domain.exception.ModelNotFoundException
import test.makcon.api.commons.domain.exception.OutdatedVersionException
import test.makcon.api.commons.domain.model.Author
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.adapter.mapper.toCreatedEvent
import test.makcon.employees.adapter.mapper.toDoc
import test.makcon.employees.adapter.mapper.toModel
import test.makcon.employees.adapter.mapper.toUpdatedEvent
import test.makcon.employees.adapter.repository.EmployeeRepository
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.domain.port.EmployeeRepositoryPort
import test.makcon.employees.domain.query.FindEmployeeQuery
import test.makcon.libs.messaging.repository.EventRepository
import java.util.*

@Repository
class EmployeeRepositoryAdapter(
    private val repository: EmployeeRepository,
    private val eventRepository: EventRepository,
) : EmployeeRepositoryPort {

    @Transactional
    override fun create(employee: VersionedModel<Employee>, author: Author) {
        repository.save(employee.toDoc())
        eventRepository.save(employee.toCreatedEvent(author))
    }

    @Transactional
    override fun update(employee: VersionedModel<Employee>, author: Author) {
        try {
            repository.save(employee.toDoc())
            eventRepository.save(employee.toUpdatedEvent(author))
        } catch (e: OptimisticLockingFailureException) {
            throw OutdatedVersionException(Employee::class)
        }
    }

    override fun getById(employeeId: UUID): VersionedModel<Employee> = repository
        .findById(employeeId)
        .map { it.toModel() }
        .orElseThrow { ModelNotFoundException(Employee::class) }

    override fun find(query: FindEmployeeQuery): PageResponse<VersionedModel<Employee>> = repository
        .findAll(query.page.toDoc())
        .toModel { it.toModel() }
}