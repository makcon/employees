package test.makcon.employees.domain.query

import java.time.Year

data class GetPublicHolidaysQuery(
    val country: String,
    val year: Year?,
)
