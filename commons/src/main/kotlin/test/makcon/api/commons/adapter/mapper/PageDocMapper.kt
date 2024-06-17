package test.makcon.api.commons.adapter.mapper

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import test.makcon.api.commons.domain.model.PageRequest
import test.makcon.api.commons.domain.model.PageResponse

fun PageRequest.toDoc(): Pageable =
    if (page.size == 0) Pageable.unpaged()
    else org.springframework.data.domain.PageRequest.of(
        page.number,
        page.size,
        Sort.Direction.valueOf(sort.order.uppercase()),
        sort.field
    )

fun <T, R> Page<T>.toModel(transform: (T) -> R) = PageResponse(
    totalElements = totalElements,
    totalPages = totalPages,
    items = content.map(transform)
)