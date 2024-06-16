package test.makcon.api.commons.dto

data class AuthorV1(
    val type: String,
    val id: String,
)

object AuthorType {

    const val USER = "USER"
}
