package test.makcon.employees.adapter.mapper

import test.makcon.api.commons.domain.model.Author
import test.makcon.api.commons.domain.model.VersionedModel
import test.makcon.employees.domain.model.Employee
import test.makcon.employees.dto.event.EmployeeCreatedEventV1
import test.makcon.libs.messaging.model.EventAuthorV1
import test.makcon.libs.messaging.model.EventV1
import test.makcon.libs.messaging.repository.doc.EventDoc

fun VersionedModel<Employee>.toCreatedEvent(author: Author) = EventDoc(
    entityId = id,
    topic = "EMPLOYEES_V1",
    event = EventV1(
        action = "EMPLOYEE_CREATED",
        createdBy = EventAuthorV1(author.type, author.id),
        body = EmployeeCreatedEventV1(
            employeeId = id,
        )
    )
)