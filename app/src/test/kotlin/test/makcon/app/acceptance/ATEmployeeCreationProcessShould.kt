package test.makcon.app.acceptance

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import test.makcon.api.commons.exception.ValidationCode
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.app.mother.EmployeeV1Mother
import test.makcon.app.mother.MoneyV1Mother
import test.makcon.employees.adapter.doc.EmployeeDocData
import test.makcon.employees.dto.EmployeeV1
import java.util.*

class ATEmployeeCreationProcessShould : ATAbstractEmployeesTest() {

    @Test
    internal fun `successfully create an employee`() {
        // given
        val givenData = EmployeeV1Mother.of()

        // when
        val actions = mvc.perform(buildRequest(givenData))

        // then
        val id = verifyAndGetResponse(actions, HttpStatus.CREATED)
        verifyDocCreated(id, givenData)
    }

    @Test
    internal fun `return error when email is invalid`() {
        // given
        val givenData = EmployeeV1Mother.of(email = uuidStrOf())

        // when
        val actions = mvc.perform(buildRequest(givenData))

        // then
        val errors = verifyAndGetValidationErrors(actions, HttpStatus.UNPROCESSABLE_ENTITY)
        errors.size shouldBe 1
        errors[0].code shouldBe "Email"
    }

    @Test
    internal fun `return error when salary is invalid`() {
        // given
        val givenData = EmployeeV1Mother.of(salary = MoneyV1Mother.of(currency = uuidStrOf()))

        // when
        val actions = mvc.perform(buildRequest(givenData))

        // then
        val errors = verifyAndGetValidationErrors(actions, HttpStatus.UNPROCESSABLE_ENTITY)
        errors.size shouldBe 1
        errors[0].code shouldBe ValidationCode.UNKNOWN_CURRENCY
    }

    private fun buildRequest(params: EmployeeV1): MockHttpServletRequestBuilder =
        MockMvcRequestBuilders
            .post("/v1/employees")
            .content(objectMapper.writeValueAsString(params))
            .contentType(MediaType.APPLICATION_JSON)

    private fun verifyDocCreated(id: String, givenData: EmployeeV1) {
        val uuid = UUID.fromString(id.replace("\"", ""))
        val doc = employeeRepository.findById(uuid).get()

        doc.createdAt shouldBe doc.updatedAt
        doc.data shouldBe EmployeeDocData(
            name = givenData.name,
            position = givenData.position,
            email = givenData.email,
            salary = MoneyDoc(givenData.salary.amount, givenData.salary.currency)
        )
    }
}