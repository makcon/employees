package test.makcon.api.commons.domain.model

data class PageResponse<T>(
    val totalElements: Long,
    val totalPages: Int,
    val items: List<T> = listOf(),
)
