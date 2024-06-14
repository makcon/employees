package test.makcon.api.commons.rest

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.valiktor.ConstraintViolationException
import test.makcon.api.commons.dto.ApiErrorV1
import test.makcon.api.commons.dto.ErrorCode.BAD_REQUEST
import test.makcon.api.commons.dto.ErrorCode.INTERNAL_ERROR
import test.makcon.api.commons.dto.ErrorCode.NOT_FOUND
import test.makcon.api.commons.exception.BadRequestException
import test.makcon.api.commons.exception.ModelNotFoundException

@RestControllerAdvice
class RestExceptionHandler {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(ModelNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle(exception: ModelNotFoundException) = createError(NOT_FOUND, exception)

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(exception: BadRequestException) = createError(BAD_REQUEST, exception)

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handle(exception: ConstraintViolationException) = exception.constraintViolations.map {
        ApiErrorV1(
            code = it.constraint.name,
            message = it.constraint.name,
            attributes = mapOf(it.property to it.value)
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
