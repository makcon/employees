package test.makcon.libs.messaging.model

import java.time.Instant
import java.util.*

data class EventV1(
    val id: UUID = UUID.randomUUID(),
    val createdAt: Instant = Instant.now(),
    val createdBy: EventAuthorV1,
    val action: String,
    val body: Any,
)

data class EventAuthorV1(
    val type: String,
    val id: String,
)