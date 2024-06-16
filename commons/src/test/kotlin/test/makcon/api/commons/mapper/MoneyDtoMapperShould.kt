package test.makcon.api.commons.mapper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.javamoney.moneta.FastMoney
import org.junit.jupiter.api.Test

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import test.makcon.api.commons.domain.exception.ValidationCode.INVALID_AMOUNT_SCALE
import test.makcon.api.commons.domain.exception.ValidationCode.UNKNOWN_CURRENCY
import test.makcon.api.commons.domain.exception.ValidationConstraint
import test.makcon.api.commons.domain.exception.ValidationException
import test.makcon.api.commons.mother.MoneyV1Mother
import kotlin.random.Random

class MoneyDtoMapperShould {

    @ParameterizedTest
    @CsvSource(value = ["1.00", "1.12", "1.123", "1.1234", "1.12345"])
    fun `sucsessfuly convert to model when amount is valid`(givenAmount: String) {
        // given
        val givenMoney = MoneyV1Mother.of(amount = givenAmount.toBigDecimal())
        // when
        val model = givenMoney.toModel(givenMoney::amount)
        // then
        model shouldBe FastMoney.of(givenAmount.toBigDecimal(), givenMoney.currency)
    }

    @ParameterizedTest
    @CsvSource(value = ["AED", "EUR", "USD"])
    fun `sucsessfuly convert to model when currency is valid`(givenCurrency: String) {
        // given
        val givenMoney = MoneyV1Mother.of(currency = givenCurrency)
        // when
        val model = givenMoney.toModel(givenMoney::amount)
        // then
        model shouldBe FastMoney.of(givenMoney.amount, givenCurrency)
    }

    @ParameterizedTest
    @CsvSource(value = ["123", "unknown_string"])
    fun `thrown an exception when currency is unknown`(givenCurrency: String) {
        // given
        val givenMoney = MoneyV1Mother.of(currency = givenCurrency)
        // when
        val exception = shouldThrow<ValidationException> {
            givenMoney.toModel(givenMoney::amount)
        }
        // then
        exception.constraints.size shouldBe 1
        exception.constraints[0] shouldBe ValidationConstraint(
            code = UNKNOWN_CURRENCY,
            field = "amount.currency",
            value = givenMoney.currency,
            message = "The provided currency is invalid"
        )
    }

    @Test
    fun `thrown an exception when amount is invalid`() {
        // given
        val givenMoney = MoneyV1Mother.of(amount = Random.nextDouble().toBigDecimal())
        // when
        val exception = shouldThrow<ValidationException> {
            givenMoney.toModel(givenMoney::amount)
        }
        // then
        exception.constraints.size shouldBe 1
        exception.constraints[0] shouldBe ValidationConstraint(
            code = INVALID_AMOUNT_SCALE,
            field = "amount.amount",
            value = givenMoney.amount,
            message = "Scale must be lees that 5"
        )
    }
}