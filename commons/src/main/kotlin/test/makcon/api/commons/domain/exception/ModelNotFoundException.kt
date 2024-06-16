package test.makcon.api.commons.domain.exception

import kotlin.reflect.KClass

class ModelNotFoundException(klass: KClass<*>) : RuntimeException("Model '${klass.simpleName} not found")
