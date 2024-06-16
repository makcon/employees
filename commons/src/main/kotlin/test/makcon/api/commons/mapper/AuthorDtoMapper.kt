package test.makcon.api.commons.mapper

import test.makcon.api.commons.domain.model.Author
import test.makcon.api.commons.dto.AuthorV1

fun AuthorV1.toModel() = Author(type, id)