package test.makcon.employees.client.request

import test.makcon.employees.dto.request.FindEmployeesRequestParamsV1
import kotlin.time.Duration

data class FindEmployeesRequestV1(
    val params: FindEmployeesRequestParamsV1,
    val timeout: Duration? = null,
)
