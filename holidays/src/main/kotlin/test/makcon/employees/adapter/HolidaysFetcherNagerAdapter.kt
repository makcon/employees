package test.makcon.employees.adapter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.domain.port.HolidaysFetcherPort
import java.time.LocalDate
import java.time.Year

@Component
@ConditionalOnProperty(
    value = ["adapter.type.holidays"],
    havingValue = "nager",
)
class HolidaysFetcherNagerAdapter(
    // TODO use more moder client like Ktor
    private val restTemplate: RestTemplate,
    @Value("\${nager.url:https://date.nager.at/api/v3/publicholidays}")
    private val baseUrl: String
) : HolidaysFetcherPort {

    override fun fetchPublic(country: String, year: Year): List<PublicHoliday> = restTemplate
        .getForObject(
            "$baseUrl/$year/$country",
            Array<NagerHoliday>::class.java
        )
        ?.map {
            PublicHoliday(
                year = year,
                country = country,
                date = it.date,
                name = it.name,
            )
        }
        ?: listOf()
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class NagerHoliday(
    val date: LocalDate,
    val name: String,
)