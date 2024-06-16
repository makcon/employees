package test.makcon.api.commons.adapter.mapper

import org.javamoney.moneta.FastMoney
import test.makcon.api.commons.adapter.repository.doc.MoneyDoc
import javax.money.MonetaryAmount

fun MoneyDoc.toModel(): MonetaryAmount = FastMoney.of(amount, currency)

fun MonetaryAmount.toDoc() = MoneyDoc(
    amount = number.toDouble().toBigDecimal(),
    currency = currency.currencyCode
)