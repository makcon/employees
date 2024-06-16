package test.makcon.employees.client.request

import org.valiktor.functions.isEmail
import org.valiktor.functions.isPositive
import org.valiktor.validate
import test.makcon.api.commons.dto.AuthorV1
import test.makcon.employees.dto.EmployeeV1
import java.util.*
import kotlin.time.Duration

data class UpdateEmployeeRequestV1(
    val employeeId: UUID,
    val version: Long,
    val author: AuthorV1,
    val data: EmployeeV1,
    val timeout: Duration? = null,
) {

    init {
        validate(this) {
            validate(UpdateEmployeeRequestV1::version).isPositive()
        }
        validate(data) {
            validate(EmployeeV1::email).isEmail()
        }
    }
}
