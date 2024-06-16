package test.makcon.employees.client

import test.makcon.api.commons.dto.VersionedModelV1
import test.makcon.employees.client.request.CreateEmployeeRequestV1
import test.makcon.employees.client.request.GetEmployeeRequestV1
import test.makcon.employees.client.request.UpdateEmployeeRequestV1
import test.makcon.employees.dto.EmployeeV1
import java.util.*

interface EmployeesClientV1 {

    fun create(request: CreateEmployeeRequestV1): UUID

    fun update(request: UpdateEmployeeRequestV1)

    fun get(request: GetEmployeeRequestV1): VersionedModelV1<EmployeeV1>
}