package test.makcon.api.commons.domain.model

import test.makcon.api.commons.utils.uuidOf
import java.time.Instant
import java.util.*

data class VersionedModel<T>(
    val id: UUID = uuidOf(),
    val version: Long = 0,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
    val data: T,
)
