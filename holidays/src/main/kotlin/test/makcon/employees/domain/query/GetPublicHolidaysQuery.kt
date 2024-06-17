package test.makcon.employees.domain.query

import test.makcon.api.commons.domain.model.PageRequest
import java.time.Year

data class GetPublicHolidaysQuery(
    val country: String,
    val year: Year?,
    val page: PageRequest,
)
