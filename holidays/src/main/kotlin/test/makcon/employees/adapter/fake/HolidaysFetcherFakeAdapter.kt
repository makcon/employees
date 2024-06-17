package test.makcon.employees.adapter.fake

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.domain.port.HolidaysFetcherPort
import java.time.LocalDate
import java.time.Year
import kotlin.random.Random

@Component
@ConditionalOnProperty(
    value = ["adapter.type.holidays"],
    havingValue = "fake",
)
class HolidaysFetcherFakeAdapter : HolidaysFetcherPort {

    override fun fetchPublic(country: String, year: Year): List<PublicHoliday> {
        return listOf(
            createFakePublicHoliday(country, year),
            createFakePublicHoliday(country, year),
            createFakePublicHoliday(country, year),
            createFakePublicHoliday(country, year),
            createFakePublicHoliday(country, year),
            createFakePublicHoliday(country, year),
        )
    }

    private fun createFakePublicHoliday(country: String, year: Year) = PublicHoliday(
        year = year,
        country = country,
        date = LocalDate.of(year.value, Random.nextInt(1, 12), Random.nextInt(1, 28)),
        name = uuidStrOf(),
    )
}