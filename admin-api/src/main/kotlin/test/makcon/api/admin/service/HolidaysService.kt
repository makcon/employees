package test.makcon.api.admin.service

import org.springframework.stereotype.Service
import test.makcon.api.admin.dto.request.ApiGetPublicHolidaysRequestParamsV1
import test.makcon.api.commons.dto.response.PageResponseV1
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.HolidaysClientV1
import test.makcon.employees.client.request.GetEmployeeRequestV1
import test.makcon.employees.client.request.GetPublicHolidaysRequestV1
import test.makcon.employees.dto.PublicHolidayV1
import test.makcon.employees.dto.request.GetPublicHolidaysRequestParamsV1
import java.util.*

@Service
class HolidaysService(
    private val employeeClient: EmployeesClientV1,
    private val holidaysClient: HolidaysClientV1,
) {

    fun getPublicHolidays(
        employeeId: UUID,
        params: ApiGetPublicHolidaysRequestParamsV1
    ): PageResponseV1<PublicHolidayV1> {
        val countryOfResidence = employeeClient
            .get(GetEmployeeRequestV1(employeeId))
            .data
            .countryOfResidence

        return holidaysClient.getPublic(
            GetPublicHolidaysRequestV1(
                params = GetPublicHolidaysRequestParamsV1(
                    country = countryOfResidence,
                    year = params.year,
                    page = params.page,
                    sort = params.sort,
                )
            )
        )
    }
}