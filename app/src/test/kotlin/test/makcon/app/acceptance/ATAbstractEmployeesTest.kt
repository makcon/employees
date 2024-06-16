package test.makcon.app.acceptance

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import test.makcon.api.commons.dto.ApiErrorV1
import test.makcon.api.commons.dto.ValidationErrorV1
import test.makcon.app.mother.EmployeeDocMother
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.repository.EmployeeRepository

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class ATAbstractEmployeesTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @AfterEach
    internal fun tearDown() {
        employeeRepository.deleteAll()
    }

    fun createEmployee(): EmployeeDoc = employeeRepository.insert(EmployeeDocMother.of(version = 0))

    fun verifyAndGetResponse(actions: ResultActions, status: HttpStatus): String = actions
        .andExpect(MockMvcResultMatchers.status().`is`(status.value()))
        .andReturn()
        .response
        .contentAsString

    fun <T> verifyAndGetObject(actions: ResultActions, status: HttpStatus, type: TypeReference<T>): T {
        val json = verifyAndGetResponse(actions, status)

        return objectMapper.readValue(json, type)
    }

    fun verifyAndGetValidationErrors(actions: ResultActions, status: HttpStatus): List<ValidationErrorV1> =
        verifyAndGetObject(actions, status, object : TypeReference<List<ValidationErrorV1>>() {})

    fun verifyAndGetError(actions: ResultActions, status: HttpStatus): ApiErrorV1 =
        verifyAndGetObject(actions, status, object : TypeReference<ApiErrorV1>() {})
}