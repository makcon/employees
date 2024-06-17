package test.makcon.employees.domain.command.handler

import org.springframework.stereotype.Component
import test.makcon.employees.domain.command.PublicHolidaysFetchCommand
import test.makcon.employees.domain.port.HolidaysFetcherPort
import test.makcon.employees.domain.port.PublicHolidayRepositoryPort

@Component
class PublicHolidaysCommandHandler(
    private val fetcher: HolidaysFetcherPort,
    private val repository: PublicHolidayRepositoryPort,
) {

    fun handle(command: PublicHolidaysFetchCommand) {
        val publicHolidays = fetcher.fetchPublic(command.country, command.year)
        repository.create(publicHolidays)
    }
}