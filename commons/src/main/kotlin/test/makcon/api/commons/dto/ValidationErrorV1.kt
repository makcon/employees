package test.makcon.api.commons.dto

data class ValidationErrorV1(
    val code: String,
    val field: String,
    val value: Any?,
    val message: String,
)
