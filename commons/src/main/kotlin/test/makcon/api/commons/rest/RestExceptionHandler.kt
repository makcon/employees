package test.makcon.api.commons.rest

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.toMessage
import test.makcon.api.commons.domain.exception.ModelNotFoundException
import test.makcon.api.commons.domain.exception.OutdatedVersionException
import test.makcon.api.commons.domain.exception.ValidationException
import test.makcon.api.commons.dto.ApiErrorV1
import test.makcon.api.commons.dto.ErrorCode.INTERNAL_ERROR
import test.makcon.api.commons.dto.ErrorCode.MODEL_OUTDATED
import test.makcon.api.commons.dto.ErrorCode.NOT_FOUND
import test.makcon.api.commons.dto.ValidationErrorV1

@RestControllerAdvice
class RestExceptionHandler {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(ModelNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle(exception: ModelNotFoundException) = createError(NOT_FOUND, exception)

    @ExceptionHandler(OutdatedVersionException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handle(exception: OutdatedVersionException) = createError(MODEL_OUTDATED, exception)

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handle(exception: ConstraintViolationException) = exception.constraintViolations.map {
        ValidationErrorV1(
            code = it.constraint.name,
            field = it.property,
            value = it.value,
            message = it.toMessage().message
        )
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handle(exception: ValidationException) = exception.constraints.map {
        ValidationErrorV1(
            code = it.code,
            field = it.field,
            value = it.value,
            message = it.message
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handle(exception: Exception): ApiErrorV1 {
        log.error(exception) { "An unexpected error occurred" }
        return ApiErrorV1(
            code = INTERNAL_ERROR,
            message = "An internal error occurred, please contact support"
        )
    }

    private fun createError(code: String, exception: Exception): ApiErrorV1 {
        log.warn { exception.message }
        return ApiErrorV1(
            code = code,
            message = exception.message ?: "An error occurred, please contact support"
        )
    }
}
