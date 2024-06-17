package test.makcon.employees.dto

import java.time.LocalDate
import java.time.Year

data class PublicHolidayV1(
    val country: String,
    val year: Year,
    val date: LocalDate,
    val name: String,
)
