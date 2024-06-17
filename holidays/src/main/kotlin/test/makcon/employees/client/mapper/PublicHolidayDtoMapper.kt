package test.makcon.employees.client.mapper

import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.dto.PublicHolidayV1

fun PublicHoliday.toDto() = PublicHolidayV1(
    country = country,
    year = year,
    date = date,
    name = name,
)