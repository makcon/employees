package test.makcon.employees.domain.port

import test.makcon.api.commons.domain.model.PageRequest
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.employees.domain.model.PublicHoliday
import java.time.Year

interface PublicHolidayRepositoryPort {

    fun getForYear(year: Year, country: String, page: PageRequest): PageResponse<PublicHoliday>
}