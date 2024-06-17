package test.makcon.employees.dto.request

import test.makcon.api.commons.dto.PagingV1
import test.makcon.api.commons.dto.SortingV1
import test.makcon.api.commons.dto.request.PageRequestV1

data class FindEmployeesRequestParamsV1(
    override val page: PagingV1 = PagingV1(),
    override val sort: SortingV1 = SortingV1(),
    // add here filters like email, country etc
) : PageRequestV1