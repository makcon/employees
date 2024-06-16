package test.makcon.api.commons.mapper

import org.javamoney.moneta.FastMoney
import test.makcon.api.commons.domain.exception.ValidationCode.INVALID_AMOUNT_SCALE
import test.makcon.api.commons.domain.exception.ValidationCode.UNKNOWN_CURRENCY
import test.makcon.api.commons.domain.exception.ValidationConstraint
import test.makcon.api.commons.domain.exception.ValidationException
import test.makcon.api.commons.dto.MoneyV1
import javax.money.MonetaryAmount
import javax.money.UnknownCurrencyException
import kotlin.reflect.KProperty

fun MoneyV1.toModel(prop: KProperty<Any>): MonetaryAmount = try {
    FastMoney.of(amount, currency)
} catch (e: UnknownCurrencyException) {
    throw ValidationException(
        listOf(
            ValidationConstraint(
                code = UNKNOWN_CURRENCY,
                field = "${prop.name}.${MoneyV1::currency.name}",
                value = currency,
                message = "The provided currency is invalid"
            )
        )
    )
} catch (e: ArithmeticException) {
    throw ValidationException(
        listOf(
            ValidationConstraint(
                code = INVALID_AMOUNT_SCALE,
                field = "${prop.name}.${MoneyV1::amount.name}",
                value = amount,
                message = "Scale must be lees that 5"
            )
        )
    )
}

fun MonetaryAmount.toDto() = MoneyV1(
    amount = number.toDouble().toBigDecimal(),
    currency = currency.currencyCode,
)