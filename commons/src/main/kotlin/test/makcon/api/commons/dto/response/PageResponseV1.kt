package test.makcon.api.commons.dto.response

data class PageResponseV1<T>(
    val totalElements: Long,
    val totalPages: Int,
    val items: List<T> = listOf(),
)
