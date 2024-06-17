package test.makcon.employees.dto.request

import test.makcon.api.commons.dto.PagingV1
import test.makcon.api.commons.dto.SortingV1
import test.makcon.api.commons.dto.request.PageRequestV1
import java.time.Year

data class GetPublicHolidaysRequestParamsV1(
    override val page: PagingV1 = PagingV1(),
    override val sort: SortingV1 = SortingV1(), // TODO check allowed sorting fields
    val country: String,
    val year: Year?,
) : PageRequestV1