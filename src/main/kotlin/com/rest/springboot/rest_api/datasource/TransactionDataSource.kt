package com.rest.springboot.rest_api.datasource

import com.rest.springboot.rest_api.model.Transaction
import org.springframework.stereotype.Repository

@Repository
class TransactionDataSource {
    val transactions = mutableListOf<Transaction>()

    fun getAllTransactions() = transactions

    fun getTransactionsByCardNumber(cardNumber: String): List<Transaction> {
        return transactions
            .filter { it.cardNumber == cardNumber }
            .sortedByDescending { it.dateTime }
    }

    fun amountSpent(cardNumber: String): Double {
        return transactions
            .asSequence()
            .filter { it.cardNumber == cardNumber }
            .sumOf { it.amount }
    }

    fun addTransaction(transaction: Transaction): Transaction {
        transactions.add(transaction)
        return transaction
    }
}