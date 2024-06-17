package test.makcon.employees.domain.port

import test.makcon.employees.domain.model.PublicHoliday
import java.time.Year

interface PublicHolidayRepositoryPort {

    fun getForYear(year: Year, country: String): List<PublicHoliday>
}