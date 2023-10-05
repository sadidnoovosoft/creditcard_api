package com.rest.springboot.rest_api.controller

import com.rest.springboot.rest_api.model.CreditCard
import com.rest.springboot.rest_api.model.Response
import com.rest.springboot.rest_api.service.CreditCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/cards")
class CreditCardController(private val service: CreditCardService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<Response> {
        return ResponseEntity(Response("Invalid card number"), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleDuplicate(e: IllegalArgumentException): ResponseEntity<Response> {
        return ResponseEntity(
            Response(e.message ?: "Credit card already present"),
            HttpStatus.NOT_FOUND
        )
    }

    @GetMapping
    fun getCards(): List<CreditCard> {
        return service.getCards()
    }

    @GetMapping("/{cardNumber}")
    fun getCard(@PathVariable cardNumber: String): CreditCard {
        return service.getCard(cardNumber)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCard(@RequestBody creditCard: CreditCard): CreditCard {
        return service.addCard(creditCard)
    }
}