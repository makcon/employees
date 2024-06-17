package test.makcon.employees.adapter

import org.springframework.stereotype.Repository
import test.makcon.employees.adapter.mapper.toModel
import test.makcon.employees.adapter.repository.PublicHolidayRepository
import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.domain.port.PublicHolidayRepositoryPort
import java.time.Year

@Repository
class PublicHolidayRepositoryAdapter(
    private val repository: PublicHolidayRepository,
) : PublicHolidayRepositoryPort {

    override fun getForYear(year: Year, country: String): List<PublicHoliday> = repository
        .findByYearAndCountry(year.value, country)
        .map { it.toModel() }
}