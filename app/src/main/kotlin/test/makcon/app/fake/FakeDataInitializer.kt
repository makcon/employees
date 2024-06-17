package test.makcon.app.fake

import io.github.serpro69.kfaker.faker
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import test.makcon.api.commons.dto.AuthorType.USER
import test.makcon.api.commons.dto.AuthorV1
import test.makcon.api.commons.dto.MoneyV1
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.client.EmployeesClientV1
import test.makcon.employees.client.request.CreateEmployeeRequestV1
import test.makcon.employees.domain.command.PublicHolidaysFetchCommand
import test.makcon.employees.domain.command.handler.PublicHolidaysCommandHandler
import test.makcon.employees.dto.EmployeeV1
import java.math.RoundingMode.HALF_DOWN
import java.time.Year
import kotlin.random.Random

@Component
class FakeDataInitializer(
    private val employeesClient: EmployeesClientV1,
    private val holidaysCommandHandler: PublicHolidaysCommandHandler
) {

    val faker = faker { }

    @PostConstruct
    fun init() {
        setOf("ES", "FR", "UA").forEach { init(it) }
    }

    private fun init(country: String) {
        holidaysCommandHandler.handle(PublicHolidaysFetchCommand(country, Year.now()))
        repeat((0..Random.nextInt(10)).count()) { createFakeEmployee(country) }
    }

    private fun createFakeEmployee(country: String) {
        val employee = EmployeeV1(
            name = faker.name.nameWithMiddle(),
            position = uuidStrOf(),
            email = faker.internet.safeEmail(),
            salary = MoneyV1(
                amount = Random.nextDouble().toBigDecimal().setScale(2, HALF_DOWN),
                currency = faker.currency.code()
            ),
            countryOfResidence = country,
        )
        employeesClient.create(
            CreateEmployeeRequestV1(
                author = AuthorV1(USER, uuidStrOf()),
                data = employee
            )
        )
    }
}