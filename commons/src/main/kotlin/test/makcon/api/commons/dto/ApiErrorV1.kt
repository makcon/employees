package test.makcon.api.commons.dto

data class ApiErrorV1(
    val code: String,
    val message: String,
    val attributes: Map<String, Any?> = mapOf(),
)

object ErrorCode {

    const val NOT_FOUND = "NotFound"
    const val MODEL_OUTDATED = "ModelOutdated"
    const val INTERNAL_ERROR = "InternalError"
}
