package test.makcon.api.commons.adapter.repository.doc

import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal

data class MoneyDoc(
    @Field(targetType = FieldType.DECIMAL128)
    val amount: BigDecimal,
    val currency: String,
)