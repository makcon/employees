package test.makcon.api.commons.exception

import kotlin.reflect.KClass

class OutdatedVersionException(klass: KClass<*>) : RuntimeException("Model '${klass.simpleName} is outdated")