package test.makcon.app.acceptance

import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import test.makcon.app.mother.EmployeeDocMother
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.repository.EmployeeRepository

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class ATAbstractEmployeesTest : ATAbstractTest() {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @AfterEach
    internal fun tearDown() {
        employeeRepository.deleteAll()
    }

    fun createEmployee(): EmployeeDoc = employeeRepository.insert(EmployeeDocMother.of(version = 0))
}