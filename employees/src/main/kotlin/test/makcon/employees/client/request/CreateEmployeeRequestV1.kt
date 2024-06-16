package test.makcon.employees.client.request

import org.valiktor.functions.isEmail
import org.valiktor.validate
import test.makcon.api.commons.dto.AuthorV1
import test.makcon.employees.dto.EmployeeV1
import kotlin.time.Duration

data class CreateEmployeeRequestV1(
    val author: AuthorV1,
    val data: EmployeeV1,
    val timeout: Duration? = null,
) {

    init {
        validate(data) {
            validate(EmployeeV1::email).isEmail()
        }
    }
}
