package test.makcon.app.mother

import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.random.Random

object MoneyDocMother {

    fun of(
        amount: BigDecimal = randomAmount(),
        currency: String = Currency.getAvailableCurrencies().random().currencyCode
    ) = MoneyDoc(
        amount = amount,
        currency = currency
    )

    private fun randomAmount(): BigDecimal = Random.nextDouble()
        .toBigDecimal()
        .setScale(5, RoundingMode.HALF_DOWN)
}