package test.makcon.employees.client

import test.makcon.employees.client.request.CreateEmployeeRequestV1
import java.util.*

interface EmployeesClientV1 {

    fun create(request: CreateEmployeeRequestV1): UUID
}