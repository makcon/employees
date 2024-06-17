package test.makcon.app.mother

import test.makcon.api.commons.dto.MoneyV1
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.dto.EmployeeV1
import java.util.*

object EmployeeV1Mother {

    fun of(
        name: String = uuidStrOf(),
        position: String = uuidStrOf(),
        email: String = "john@mail.com", // use a random value, e.q. kotlin faker lib
        salary: MoneyV1 = MoneyV1Mother.of(),
        countryOfResidence: String = Locale.getISOCountries().random(),
    ) = EmployeeV1(
        name = name,
        position = position,
        email = email,
        salary = salary,
        countryOfResidence = countryOfResidence
    )
}