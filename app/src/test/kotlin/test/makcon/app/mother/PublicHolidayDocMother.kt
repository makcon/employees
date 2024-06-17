package test.makcon.app.mother

import test.makcon.api.commons.utils.uuidOf
import test.makcon.api.commons.utils.uuidStrOf
import test.makcon.employees.adapter.doc.PublicHolidayDoc
import java.time.LocalDate
import java.time.Year
import java.util.*

object PublicHolidayDocMother {

    fun of(
        id: UUID = uuidOf(),
        year: Int = Year.now().value,
        country: String = Locale.getISOCountries().random(),
        date: LocalDate = LocalDate.now(),
        name: String = uuidStrOf(),
    ) = PublicHolidayDoc(
        id = id,
        year = year,
        country = country,
        date = date,
        name = name,
    )
}