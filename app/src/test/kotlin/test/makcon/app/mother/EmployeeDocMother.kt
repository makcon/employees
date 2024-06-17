package test.makcon.app.mother

import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import test.makcon.api.commons.utils.uuidOf
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.adapter.doc.EmployeeDoc
import test.makcon.employees.adapter.doc.EmployeeDocData
import java.time.Instant
import java.time.temporal.ChronoUnit.MILLIS
import java.util.*
import kotlin.random.Random

object EmployeeDocMother {
    
    fun of(
        id: UUID = uuidOf(),
        version: Long = Random.nextLong(),
        createdAt: Instant = Instant.now().truncatedTo(MILLIS),
        updatedAt: Instant = Instant.now().truncatedTo(MILLIS),
        data: EmployeeDocData = EmployeeDocDataMother.of()
    ) = EmployeeDoc(
        id = id,
        version = version,
        createdAt = createdAt,
        updatedAt = updatedAt,
        data = data,
    )
}

object EmployeeDocDataMother {

    fun of(
        name: String = uuidStrOf(),
        position: String = uuidStrOf(),
        email: String = "john@mail.com", // use a random value, e.q. kotlin faker lib
        salary: MoneyDoc = MoneyDocMother.of(),
        countryOfResidence: String = Locale.getISOCountries().random(),
    ) = EmployeeDocData(
        name = name,
        position = position,
        email = email,
        salary = salary,
        countryOfResidence = countryOfResidence,
    )
}