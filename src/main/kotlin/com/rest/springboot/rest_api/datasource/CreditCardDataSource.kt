package com.rest.springboot.rest_api.datasource

import com.rest.springboot.rest_api.model.CreditCard
import org.springframework.stereotype.Repository

@Repository
class CreditCardDataSource {

    val creditCards = mutableListOf(
        CreditCard("1534", "Sadid Shaikh", 100000.0),
        CreditCard("4987", "Pulkit Dadheech", 50000.0),
        CreditCard("4238", "Tejas Ekhande", 75000.0),
    )

    fun retrieveCards(): List<CreditCard> {
        return creditCards
    }

    fun retrieveCard(cardNumber: String): CreditCard {
        return creditCards.find { it.cardNumber == cardNumber }
            ?: throw NoSuchElementException(
                "Could not find credit card with card number: $cardNumber"
            )
    }

    fun createCard(creditCard: CreditCard): CreditCard {
        if (creditCards.any { it.cardNumber == creditCard.cardNumber }) {
            throw IllegalArgumentException(
                "Credit card with card-number ${creditCard.cardNumber} already present"
            )
        }
        creditCards.add(creditCard)
        return creditCard
    }

    fun updateCard(creditCard: CreditCard): CreditCard {
        val index = creditCards.indexOfFirst { it.cardNumber == creditCard.cardNumber }
        if (index == -1) {
            throw NoSuchElementException(
                "Could not find credit card with card number: ${creditCard.cardNumber}"
            )
        }
        creditCards[index] = creditCard
        return creditCards[index]
    }

    fun deleteCard(cardNumber: String) {
        val creditCard = creditCards.find { it.cardNumber == cardNumber }
            ?: throw NoSuchElementException(
                "Could not find credit card with card number: $cardNumber"
            )
        creditCards.remove(creditCard)
    }
}