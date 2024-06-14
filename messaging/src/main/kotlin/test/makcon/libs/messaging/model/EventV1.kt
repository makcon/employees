package test.makcon.libs.messaging.model

import java.time.Instant
import java.util.*

data class EventV1(
    val id: UUID = UUID.randomUUID(),
    val createdAt: Instant = Instant.now(),
    val action: String,
    val body: Any,
    val createdBy: EventAuthorV1,
)

data class EventAuthorV1(
    val type: String,
    val value: String,
) {

    companion object {

        fun ofUser(id: UUID): EventAuthorV1 = EventAuthorV1("USER", id.toString())
        fun ofSystem(id: String): EventAuthorV1 = EventAuthorV1("SYSTEM", id)
        fun ofEvent(action: String, id: UUID): EventAuthorV1 = EventAuthorV1(action, id.toString())
    }
}