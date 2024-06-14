package test.makcon.api.commons.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape
import java.math.BigDecimal

data class MoneyV1(
    @JsonFormat(shape = Shape.STRING)
    val amount: BigDecimal,
    val currency: String,
)
