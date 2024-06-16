package test.makcon.employees.client.request

import java.util.*
import kotlin.time.Duration

data class GetEmployeeRequestV1(
    val employeeId: UUID,
    val timeout: Duration? = null,
)
