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
        return creditCards.first { it.cardNumber == cardNumber }
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
}