package test.makcon.employees.domain.model

import javax.money.MonetaryAmount

data class Employee(
    val name: String,
    val position: String,
    val email: String,
    val salary: MonetaryAmount,
    val countryOfResidence: String,
)
