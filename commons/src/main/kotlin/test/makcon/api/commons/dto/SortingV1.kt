package test.makcon.api.commons.dto

data class SortingV1(
    val field: String = "updatedAt",
    val order: String = "DESC",
)