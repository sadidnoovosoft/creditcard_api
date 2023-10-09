package com.rest.springboot.rest_api.controller

import com.rest.springboot.rest_api.model.CreditCard
import com.rest.springboot.rest_api.service.CreditCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cards")
class CreditCardController(private val service: CreditCardService) {

    @GetMapping
    fun getCards(): ResponseEntity<List<CreditCard>> {
        return ResponseEntity(service.getCards(), HttpStatus.OK)
    }

    @GetMapping("/{cardNumber}")
    fun getCard(@PathVariable cardNumber: String): ResponseEntity<CreditCard> {
        return ResponseEntity(service.getCard(cardNumber), HttpStatus.OK)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCard(@RequestBody creditCard: CreditCard): ResponseEntity<CreditCard> {
        return ResponseEntity(service.addCard(creditCard), HttpStatus.OK)
    }

    @PutMapping
    fun updateCard(@RequestBody creditCard: CreditCard): ResponseEntity<CreditCard> {
        return ResponseEntity(service.updateCard(creditCard), HttpStatus.OK)
    }

    @DeleteMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCard(@PathVariable cardNumber: String) {
        service.deleteCard(cardNumber)
    }
}