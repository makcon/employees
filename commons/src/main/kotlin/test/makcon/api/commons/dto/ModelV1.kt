package test.makcon.api.commons.dto

import java.time.Instant

data class ModelV1<T>(
    val id: String,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val data: T,
)
