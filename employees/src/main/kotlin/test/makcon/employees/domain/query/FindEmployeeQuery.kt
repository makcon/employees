package test.makcon.employees.domain.query

import test.makcon.api.commons.domain.model.PageRequest

data class FindEmployeeQuery(
    val page: PageRequest,
)
