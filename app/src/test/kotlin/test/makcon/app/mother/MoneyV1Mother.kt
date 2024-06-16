package test.makcon.app.mother

import test.makcon.api.commons.dto.MoneyV1
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.random.Random

object MoneyV1Mother {

    fun of(
        amount: BigDecimal = randomAmount(),
        currency: String = Currency.getAvailableCurrencies().random().currencyCode
    ) = MoneyV1(
        amount = amount,
        currency = currency
    )

    private fun randomAmount(): BigDecimal = Random.nextDouble()
        .toBigDecimal()
        .setScale(5, RoundingMode.HALF_DOWN)
}