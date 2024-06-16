package test.makcon.api.admin.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import test.makcon.api.commons.dto.AuthorType
import test.makcon.api.commons.dto.AuthorV1
import test.makcon.api.commons.dto.VersionedModelV1
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.request.CreateEmployeeRequestV1
import test.makcon.employees.client.request.GetEmployeeRequestV1
import test.makcon.employees.client.request.UpdateEmployeeRequestV1
import test.makcon.employees.dto.EmployeeV1
import java.util.*

@RestController
@RequestMapping("/v1/employees")
class EmployeesControllerV1(private val client: EmployeesClientV1) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody data: EmployeeV1): UUID = client.create(
        CreateEmployeeRequestV1(
            // in real app we can get userId from auth or another service
            author = AuthorV1(AuthorType.USER, uuidStrOf()),
            data = data
        )
    )

    @PutMapping("/{id}/versions/{version}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(
        @RequestBody data: EmployeeV1,
        @PathVariable id: UUID,
        @PathVariable version: Long,
    ) = client.update(
        UpdateEmployeeRequestV1(
            employeeId = id,
            version = version,
            author = AuthorV1(AuthorType.USER, uuidStrOf()),
            data = data
        )
    )

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): VersionedModelV1<EmployeeV1> = client.get(
        GetEmployeeRequestV1(employeeId = id)
    )
}