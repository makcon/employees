package test.makcon.api.commons.utils

import java.util.*

fun uuidStrOf(vararg params: String): String = uuidOf(*params).toString()

fun uuidOf(vararg params: Any): UUID =
    if (params.isEmpty()) UUID.randomUUID()
    else UUID.nameUUIDFromBytes(params.joinToString().toByteArray())