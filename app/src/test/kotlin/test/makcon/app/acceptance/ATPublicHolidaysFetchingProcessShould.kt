package test.makcon.app.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import test.makcon.api.commons.dto.ErrorCode
import test.makcon.api.commons.dto.PagingV1
import test.makcon.api.commons.dto.SortingV1
import test.makcon.api.commons.dto.response.PageResponseV1
import test.makcon.api.commons.utils.uuidOf
import test.makcon.app.mother.EmployeeDocMother
import test.makcon.app.mother.PublicHolidayDocMother
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.doc.PublicHolidayDoc
import test.makcon.employees.adapter.repository.EmployeeRepository
import test.makcon.employees.adapter.repository.PublicHolidayRepository
import test.makcon.employees.dto.PublicHolidayV1
import java.time.LocalDate
import java.time.Year
import java.util.*

class ATPublicHolidaysFetchingProcessShould : ATAbstractTest() {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var publicHolidayRepository: PublicHolidayRepository

    @AfterEach
    internal fun tearDown() {
        employeeRepository.deleteAll()
        publicHolidayRepository.deleteAll()
    }

    @Test
    internal fun `successfully get holidays for an employee for current year`() {
        // given
        val storedEmployee = createEmployee()
        val holidayName1 = createHoliday(storedEmployee).name
        val holidayName2 = createHoliday(storedEmployee).name
        createHoliday(storedEmployee, Year.now().minusYears(1))
        createHoliday(createEmployee())

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id))

        // then
        val foundHolidays = verifyAndGetResponse(actions)
        foundHolidays.items.map { it.name }.toSet() shouldBe setOf(holidayName1, holidayName2)
    }

    @Test
    internal fun `successfully get holidays for an employee for requested year`() {
        // given
        val storedEmployee = createEmployee()
        val givenYear = Year.now().minusYears(1)
        val holidayName1 = createHoliday(storedEmployee, givenYear).name
        createHoliday(storedEmployee, Year.now())
        createHoliday(createEmployee())

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, year = givenYear))

        // then
        val foundHolidays = verifyAndGetResponse(actions)
        foundHolidays.items.map { it.name }.toSet() shouldBe setOf(holidayName1)
    }

    @Test
    internal fun `get sorted asc holidays`() {
        // given
        val storedEmployee = createEmployee()
        val holidayName1 = createHoliday(storedEmployee, date = LocalDate.now().plusDays(1)).name
        val holidayName2 = createHoliday(storedEmployee, date = LocalDate.now()).name
        val givenSorting = SortingV1("date", "asc")

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, sorting = givenSorting))

        // then
        val foundHolidays = verifyAndGetResponse(actions)
        foundHolidays.items.map { it.name } shouldBe listOf(holidayName2, holidayName1)
    }

    @Test
    internal fun `get sorted desc holidays`() {
        // given
        val storedEmployee = createEmployee()
        val holidayName1 = createHoliday(storedEmployee, date = LocalDate.now().plusDays(1)).name
        val holidayName2 = createHoliday(storedEmployee, date = LocalDate.now()).name
        val givenSorting = SortingV1("date", "desc")

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, sorting = givenSorting))

        // then
        val foundHolidays = verifyAndGetResponse(actions)
        foundHolidays.items.map { it.name } shouldBe listOf(holidayName1, holidayName2)
    }

    @Test
    internal fun `get limited page`() {
        // given
        val storedEmployee = createEmployee()
        val holidayName1 = createHoliday(storedEmployee, date = LocalDate.now()).name
        createHoliday(storedEmployee)
        createHoliday(storedEmployee)
        val givenSorting = PagingV1(0, 1)

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, paging = givenSorting))

        // then
        val foundHolidays = verifyAndGetResponse(actions)
        foundHolidays.items.map { it.name } shouldBe listOf(holidayName1)
    }

    @Test
    internal fun `return not found when employee does not exists`() {
        // when
        val actions = mvc.perform(buildRequest(uuidOf()))

        // then
        val errors = verifyAndGetError(actions, HttpStatus.NOT_FOUND)
        errors.code shouldBe ErrorCode.NOT_FOUND
    }

    private fun buildRequest(
        id: UUID,
        sorting: SortingV1 = SortingV1(field = "date"),
        paging: PagingV1 = PagingV1(),
        year: Year? = null
    ): MockHttpServletRequestBuilder {
        var url = "/v1/employees/$id/holidays/public"
        url = "$url?sort.field=${sorting.field}&sort.order=${sorting.order}"
        url = "$url&page.number=${paging.number}&page.size=${paging.size}"
        year?.let { url = "$url&year=$year" }

        return MockMvcRequestBuilders.get(url)
    }

    fun createEmployee(): EmployeeDoc = employeeRepository.insert(EmployeeDocMother.of(version = 0))

    fun createHoliday(
        employee: EmployeeDoc,
        year: Year = Year.now(),
        date: LocalDate = LocalDate.now()
    ): PublicHolidayDoc =
        publicHolidayRepository.insert(
            PublicHolidayDocMother.of(
                country = employee.data.countryOfResidence,
                year = year.value,
                date = date,
            )
        )

    private fun verifyAndGetResponse(actions: ResultActions) =
        verifyAndGetObject(actions, OK, object : TypeReference<PageResponseV1<PublicHolidayV1>>() {})
}
