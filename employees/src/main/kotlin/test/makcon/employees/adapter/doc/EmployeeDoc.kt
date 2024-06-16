package test.makcon.employees.adapter.doc

import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import java.time.Instant
import java.util.*

@Document("employees")
data class EmployeeDoc(
    val id: UUID,
    @field:Version
    val version: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
    val data: EmployeeDocData,
)

data class EmployeeDocData(
    val name: String,
    val position: String,
    val email: String,
    val salary: MoneyDoc,
)