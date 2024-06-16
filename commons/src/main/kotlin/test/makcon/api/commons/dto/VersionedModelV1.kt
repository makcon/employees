package test.makcon.api.commons.dto

import java.time.Instant
import java.util.*

data class VersionedModelV1<T>(
    val id: UUID,
    val version: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
    val data: T,
)
