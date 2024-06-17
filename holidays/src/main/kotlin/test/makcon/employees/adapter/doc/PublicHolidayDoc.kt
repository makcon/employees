package test.makcon.employees.adapter.doc

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document("public-holidays")
@CompoundIndexes(
    CompoundIndex(def = "{'year': 1, 'country': 1}"),
)
data class PublicHolidayDoc(
    val id: UUID,
    val year: Int,
    val country: String,
    val date: LocalDate,
    val name: String,
)
