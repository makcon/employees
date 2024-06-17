package test.makcon.employees.client.request

import java.time.Year
import kotlin.time.Duration

data class GetPublicHolidaysRequestV1(
    val country: String,
    val year: Year? = null,
    val timeout: Duration? = null,
)
