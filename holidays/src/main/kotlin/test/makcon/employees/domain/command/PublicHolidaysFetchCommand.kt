package test.makcon.employees.domain.command

import java.time.Year

data class PublicHolidaysFetchCommand(
    val country: String,
    val year: Year,
)