package test.makcon.employees.client

import test.makcon.employees.client.request.GetPublicHolidaysRequestV1
import test.makcon.employees.dto.PublicHolidayV1

interface HolidaysClientV1 {

    fun getPublic(request: GetPublicHolidaysRequestV1): List<PublicHolidayV1>
}