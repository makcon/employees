package test.makcon.app.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import test.makcon.api.commons.adapter.mapper.toModel
import test.makcon.api.commons.dto.ErrorCode
import test.makcon.api.commons.dto.VersionedModelV1
import test.makcon.api.commons.mapper.toDto
import test.makcon.api.commons.utils.uuidOf
import test.makcon.employees.dto.EmployeeV1
import java.util.*

class ATEmployeeFetchingProcessShould : ATAbstractEmployeesTest() {

    @Test
    internal fun `successfully get an employee by id`() {
        // given
        val storedEmployee = createEmployee()

        // when
        val actions = mvc.perform(buildRequest(storedEmployee.id))

        // then
        val foundEmployee =
            verifyAndGetObject(actions, HttpStatus.OK, object : TypeReference<VersionedModelV1<EmployeeV1>>() {})
        foundEmployee shouldBe VersionedModelV1(
            id = storedEmployee.id,
            version = storedEmployee.version,
            createdAt = storedEmployee.createdAt,
            updatedAt = storedEmployee.updatedAt,
            data = EmployeeV1(
                name = storedEmployee.data.name,
                position = storedEmployee.data.position,
                email = storedEmployee.data.email,
                salary = storedEmployee.data.salary.toModel().toDto(),
                countryOfResidence = storedEmployee.data.countryOfResidence,
            )
        )
    }

    @Test
    internal fun `return not found when employee does not exists`() {
        // when
        val actions = mvc.perform(buildRequest(uuidOf()))

        // then
        val errors = verifyAndGetError(actions, HttpStatus.NOT_FOUND)
        errors.code shouldBe ErrorCode.NOT_FOUND
    }

    private fun buildRequest(id: UUID): MockHttpServletRequestBuilder =
        MockMvcRequestBuilders.get("/v1/employees/$id")
}