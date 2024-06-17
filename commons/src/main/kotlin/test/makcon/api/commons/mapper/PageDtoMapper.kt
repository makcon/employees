package test.makcon.api.commons.mapper

import test.makcon.api.commons.domain.model.PageRequest
import test.makcon.api.commons.domain.model.PageResponse
import test.makcon.api.commons.domain.model.Paging
import test.makcon.api.commons.domain.model.Sorting
import test.makcon.api.commons.dto.PagingV1
import test.makcon.api.commons.dto.SortingV1
import test.makcon.api.commons.dto.request.PageRequestV1
import test.makcon.api.commons.dto.response.PageResponseV1

fun PageRequestV1.toModel() = PageRequest(page.toModel(), sort.toModel())

fun PagingV1.toModel() = Paging(number, size)

fun SortingV1.toModel() = Sorting(field, order.uppercase())

fun <T, R> PageResponse<T>.toDto(transform: (T) -> R) = PageResponseV1(
    totalElements = totalElements,
    totalPages = totalPages,
    items = items.map(transform)
)