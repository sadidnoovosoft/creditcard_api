package com.rest.springboot.rest_api.controller

import com.rest.springboot.rest_api.exception.InsufficientCreditException
import com.rest.springboot.rest_api.model.Response
import com.rest.springboot.rest_api.model.Transaction
import com.rest.springboot.rest_api.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping
    fun getAllTransactions(): ResponseEntity<List<Transaction>> {
        return ResponseEntity(transactionService.getAllTransactions(), HttpStatus.OK)
    }

    @GetMapping("/{cardNumber}")
    fun getTransactionsByCardNumber(@PathVariable cardNumber: String): ResponseEntity<List<Transaction>> {
        return ResponseEntity(transactionService.getTransactionsByCardNumber(cardNumber), HttpStatus.OK)
    }

    @PostMapping
    fun postTransactions(@RequestBody transaction: Transaction): ResponseEntity<Transaction> {
        return ResponseEntity(transactionService.processTransaction(transaction), HttpStatus.OK)
    }
}