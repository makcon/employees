package test.makcon.employees.adapter.mapper

import test.makcon.employees.adapter.doc.PublicHolidayDoc
import test.makcon.employees.domain.model.PublicHoliday
import java.time.Year

fun PublicHolidayDoc.toModel() = PublicHoliday(
    country = country,
    year = Year.of(year),
    date = date,
    name = name,
)