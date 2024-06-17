package test.makcon.api.admin.service

import org.springframework.stereotype.Service
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.HolidaysClientV1
import test.makcon.employees.client.request.GetEmployeeRequestV1
import test.makcon.employees.client.request.GetPublicHolidaysRequestV1
import test.makcon.employees.dto.PublicHolidayV1
import java.time.Year
import java.util.*

@Service
class HolidaysService(
    private val employeeClient: EmployeesClientV1,
    private val holidaysClient: HolidaysClientV1,
) {

    fun getPublicHolidays(employeeId: UUID, year: Year?): List<PublicHolidayV1> {
        val countryOfResidence = employeeClient
            .get(GetEmployeeRequestV1(employeeId))
            .data
            .countryOfResidence

        return holidaysClient.getPublic(
            GetPublicHolidaysRequestV1(
                country = countryOfResidence,
                year = year
            )
        )
    }
}