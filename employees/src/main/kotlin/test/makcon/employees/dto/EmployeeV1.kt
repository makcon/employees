package test.makcon.employees.dto

import test.makcon.api.commons.dto.MoneyV1

data class EmployeeV1(
    val name: String,
    val position: String,
    val email: String,
    val salary: MoneyV1,
    val countryOfResidence: String,
)
