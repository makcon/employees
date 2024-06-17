package test.makcon.employees.adapter

import org.springframework.stereotype.Repository
import test.makcon.api.commons.adapter.mapper.toDoc
import test.makcon.api.commons.adapter.mapper.toModel
import test.makcon.api.commons.domain.model.PageRequest
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.employees.adapter.mapper.toDoc
import test.makcon.employees.adapter.mapper.toModel
import test.makcon.employees.adapter.repository.PublicHolidayRepository
import test.makcon.employees.domain.model.PublicHoliday
import test.makcon.employees.domain.port.PublicHolidayRepositoryPort
import java.time.Year

@Repository
class PublicHolidayRepositoryAdapter(
    private val repository: PublicHolidayRepository,
) : PublicHolidayRepositoryPort {

    override fun getForYear(year: Year, country: String, page: PageRequest): PageResponse<PublicHoliday> = repository
        .findByYearAndCountry(
            year = year.value,
            country = country,
            page = page.toDoc()
        )
        .toModel { it.toModel() }

    override fun create(holidays: List<PublicHoliday>) {
        repository.saveAll(holidays.map { it.toDoc() })
    }
}