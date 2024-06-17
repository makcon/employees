package test.makcon.employees.domain.query.handler

import org.springframework.stereotype.Component
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.domain.port.PublicHolidayRepositoryPort
import test.makcon.employees.domain.query.GetPublicHolidaysQuery
import java.time.Year

@Component
class GetPublicHolidaysQueryHandler(private val repository: PublicHolidayRepositoryPort) {

    fun handle(query: GetPublicHolidaysQuery): PageResponse<PublicHoliday> = repository
        .getForYear(
            year = query.year ?: Year.now(),
            country = query.country,
            page = query.page,
        )
}