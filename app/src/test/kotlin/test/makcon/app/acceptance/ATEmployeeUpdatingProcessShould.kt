package test.makcon.app.acceptance

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import test.makcon.api.commons.dto.ErrorCode
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.app.mother.EmployeeV1Mother
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.doc.EmployeeDocData
import test.makcon.employees.dto.EmployeeV1
import java.util.*

class ATEmployeeUpdatingProcessShould : ATAbstractEmployeesTest() {

    @Test
    internal fun `successfully update an employee`() {
        // given
        val storedEmployee = createEmployee()
        val givenData = EmployeeV1Mother.of()

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, storedEmployee.version, givenData))

        // then
        verifyAndGetResponse(actions, HttpStatus.NO_CONTENT)
        verifyDocUpdated(storedEmployee, givenData)
    }

    @Test
    internal fun `return error when email is invalid`() {
        // given
        val storedEmployee = createEmployee()
        val givenData = EmployeeV1Mother.of(email = uuidStrOf())

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, storedEmployee.version, givenData))

        // then
        val errors = verifyAndGetValidationErrors(actions, HttpStatus.UNPROCESSABLE_ENTITY)
        errors.size shouldBe 1
        errors[0].code shouldBe "Email"
    }

    @Test
    internal fun `return error when version is outdated`() {
        // given
        val storedEmployee = createEmployee()
        val givenData = EmployeeV1Mother.of()

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id, storedEmployee.version + 1, givenData))

        // then
        val errors = verifyAndGetError(actions, HttpStatus.CONFLICT)
        errors.code shouldBe ErrorCode.MODEL_OUTDATED
    }

    private fun buildRequest(
        employeeId: UUID,
        version: Long,
        data: EmployeeV1
    ): MockHttpServletRequestBuilder = MockMvcRequestBuilders
        .put("/v1/employees/$employeeId/versions/$version")
        .content(objectMapper.writeValueAsString(data))
        .contentType(MediaType.APPLICATION_JSON)

    private fun verifyDocUpdated(storedEmployee: EmployeeDoc, givenData: EmployeeV1) {
        val doc = employeeRepository.findById(storedEmployee.id).get()

        doc.createdAt shouldBe storedEmployee.createdAt
        doc.updatedAt shouldNotBe storedEmployee.updatedAt
        doc.data shouldBe EmployeeDocData(
            name = givenData.name,
            position = givenData.position,
            email = givenData.email,
            salary = MoneyDoc(givenData.salary.amount, givenData.salary.currency),
            countryOfResidence = givenData.countryOfResidence,
        )
    }
}