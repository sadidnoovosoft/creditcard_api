package com.rest.springboot.rest_api.exception

import com.rest.springboot.rest_api.exception.InsufficientCreditException
import com.rest.springboot.rest_api.model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<Response> {
        return ResponseEntity(
            Response(e.message ?: "Invalid card number"), HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleDuplicate(e: IllegalArgumentException): ResponseEntity<Response> {
        return ResponseEntity(
            Response(e.message ?: "Credit card already present"), HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(InsufficientCreditException::class)
    fun handleCreditException(e: InsufficientCreditException): ResponseEntity<Response> {
        return ResponseEntity(
            Response(e.message ?: "Insufficient available credit for the transaction."),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<Response> {
        return ResponseEntity(
            Response(e.message ?: "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}