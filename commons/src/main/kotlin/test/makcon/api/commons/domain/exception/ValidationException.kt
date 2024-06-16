package test.makcon.api.commons.domain.exception

class ValidationException(val constraints: List<ValidationConstraint>) : Exception()

data class ValidationConstraint(
    val code: String,
    val field: String,
    val value: Any?,
    val message: String,
)

object ValidationCode {

    const val INVALID_AMOUNT_SCALE = "InvalidAmountScale"
    const val UNKNOWN_CURRENCY = "UnknownCurrency"
}