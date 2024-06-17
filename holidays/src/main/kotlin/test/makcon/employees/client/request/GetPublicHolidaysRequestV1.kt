package test.makcon.employees.client.request

import test.makcon.employees.dto.request.GetPublicHolidaysRequestParamsV1
import kotlin.time.Duration

data class GetPublicHolidaysRequestV1(
    val params: GetPublicHolidaysRequestParamsV1,
    val timeout: Duration? = null,
)
