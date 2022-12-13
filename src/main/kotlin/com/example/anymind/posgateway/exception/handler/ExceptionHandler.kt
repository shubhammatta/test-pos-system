package com.example.anymind.posgateway.exception.handler

import com.example.anymind.posgateway.exception.DatabaseException
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import com.example.anymind.posgateway.model.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.function.Consumer
import java.util.logging.Logger
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException


@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(DatabaseException::class)
    fun databaseExceptionHandler(exception: DatabaseException):ResponseEntity<ErrorResponse> {
        return ResponseEntity.internalServerError().body(ErrorResponse(exception.message?: "Unknown Error"))
    }

    @ExceptionHandler(PaymentMethodNotFoundException::class)
    fun paymentMethodNotFoundExceptionHandler(exception: PaymentMethodNotFoundException):ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(exception.message?: "Unknown Error"))
    }

    @ExceptionHandler(ValidationException::class)
    fun validationExceptionHandler(exception: ValidationException):ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(exception.message?: "Unknown Error"))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationExceptionHandler(exception: ConstraintViolationException):ResponseEntity<ErrorResponse> {
        val violations: Set<ConstraintViolation<*>> = exception.constraintViolations
        var errorMessage = if (violations.isNotEmpty()) {
            val builder = StringBuilder()
            violations.forEach(Consumer { violation: ConstraintViolation<*> ->
                builder.append(
                    "\n" + violation.message
                )
            })
            builder.toString()
        } else {
            "ConstraintViolationException occurred."
        }
        return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        log.error("JSON error HttpMessageNotReadableException", exception)
        return ResponseEntity.badRequest().body(ErrorResponse(exception.message?: "Unknown Error"))
    }
    
    companion object {
        val log = LoggerFactory.getLogger("ExceptionHandler")
    }
}