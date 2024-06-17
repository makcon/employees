package test.makcon.employees.client.internal

import org.springframework.stereotype.Component
import test.makcon.api.commons.dto.response.PageResponseV1
import test.makcon.api.commons.mapper.toDto
import test.makcon.api.commons.mapper.toModel
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

    override fun getPublic(request: GetPublicHolidaysRequestV1): PageResponseV1<PublicHolidayV1> = getQueryHandler
        .handle(
            GetPublicHolidaysQuery(
                country = request.params.country,
                year = request.params.year,
                page = request.params.toModel(),
            )
        )
        .toDto { it.toDto() }
}