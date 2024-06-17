package test.makcon.employees.domain.model

import java.time.LocalDate
import java.time.Year

data class PublicHoliday(
    val year: Year,
    val country: String,
    val date: LocalDate,
    val name: String,
)
