package test.makcon.employees.domain.port

import test.makcon.employees.domain.model.PublicHoliday
import java.time.Year

interface HolidaysFetcherPort {

    fun fetchPublic(country: String, year: Year): List<PublicHoliday>
}