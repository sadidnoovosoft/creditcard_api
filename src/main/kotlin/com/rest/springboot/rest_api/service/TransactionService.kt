package com.rest.springboot.rest_api.service

import com.rest.springboot.rest_api.datasource.CreditCardDataSource
import com.rest.springboot.rest_api.datasource.TransactionDataSource
import com.rest.springboot.rest_api.exception.InsufficientCreditException
import com.rest.springboot.rest_api.model.Transaction
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionDataSource: TransactionDataSource,
    val cardDataSource: CreditCardDataSource
) {

    fun getAllTransactions(): List<Transaction> {
        return transactionDataSource.getAllTransactions()
    }

    fun getTransactionsByCardNumber(cardNumber: String): List<Transaction> {
        cardDataSource.retrieveCard(cardNumber)
        return transactionDataSource.getTransactionsByCardNumber(cardNumber)
    }

    fun processTransaction(transaction: Transaction): Transaction {
        val creditLimit = cardDataSource.retrieveCard(transaction.cardNumber).creditLimit
        val amountSpent = transactionDataSource.amountSpent(transaction.cardNumber)

        if (creditLimit - amountSpent < transaction.amount) {
            throw InsufficientCreditException("Insufficient available credit for the transaction.")
        }
        return transactionDataSource.addTransaction(transaction)
    }
}