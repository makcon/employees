package test.makcon.api.commons.dto.request

import test.makcon.api.commons.dto.PagingV1
import test.makcon.api.commons.dto.SortingV1

interface PageRequestV1 {
    val page: PagingV1
    val sort: SortingV1
}