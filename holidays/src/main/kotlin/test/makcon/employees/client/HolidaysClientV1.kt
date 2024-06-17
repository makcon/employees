package test.makcon.employees.client

import test.makcon.api.commons.dto.response.PageResponseV1
import test.makcon.employees.client.request.GetPublicHolidaysRequestV1
import test.makcon.employees.dto.PublicHolidayV1

interface HolidaysClientV1 {

    fun getPublic(request: GetPublicHolidaysRequestV1): PageResponseV1<PublicHolidayV1>
}