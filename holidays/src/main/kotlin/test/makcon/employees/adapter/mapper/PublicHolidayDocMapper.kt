package test.makcon.employees.adapter.mapper

import test.makcon.api.commons.utils.uuidOf
import test.makcon.employees.adapter.doc.PublicHolidayDoc
import test.makcon.employees.domain.model.PublicHoliday
import java.time.Year

fun PublicHolidayDoc.toModel() = PublicHoliday(
    country = country,
    year = Year.of(year),
    date = date,
    name = name,
)

fun PublicHoliday.toDoc() = PublicHolidayDoc(
    id = uuidOf(country, date),
    country = country,
    year = year.value,
    date = date,
    name = name,
)