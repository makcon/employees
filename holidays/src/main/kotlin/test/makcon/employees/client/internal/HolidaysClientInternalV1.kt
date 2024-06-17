package test.makcon.employees.client.internal

import org.springframework.stereotype.Component
import test.makcon.employees.client.HolidaysClientV1
import test.makcon.employees.client.mapper.toDto
import test.makcon.employees.client.request.GetPublicHolidaysRequestV1
import test.makcon.employees.domain.query.GetPublicHolidaysQuery
import test.makcon.employees.domain.query.handler.GetPublicHolidaysQueryHandler
import test.makcon.employees.dto.PublicHolidayV1

@Component
class HolidaysClientInternalV1(
    private val getQueryHandler: GetPublicHolidaysQueryHandler,
) : HolidaysClientV1 {

    override fun getPublic(request: GetPublicHolidaysRequestV1): List<PublicHolidayV1> = getQueryHandler
        .handler(
            GetPublicHolidaysQuery(
                country = request.country,
                year = request.year,
            )
        )
        .map { it.toDto() }
}