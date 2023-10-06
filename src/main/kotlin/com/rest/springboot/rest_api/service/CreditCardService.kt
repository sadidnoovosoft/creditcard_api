package com.rest.springboot.rest_api.service

import com.rest.springboot.rest_api.datasource.CreditCardDataSource
import com.rest.springboot.rest_api.model.CreditCard
import org.springframework.stereotype.Service

@Service
class CreditCardService(private val dataSource: CreditCardDataSource) {

    fun getCards(): List<CreditCard> {
        return dataSource.retrieveCards()
    }

    fun getCard(cardNumber: String): CreditCard {
        return dataSource.retrieveCard(cardNumber)
    }

    fun addCard(creditCard: CreditCard): CreditCard {
        return dataSource.createCard(creditCard)
    }

    fun updateCard(creditCard: CreditCard): CreditCard {
        return dataSource.updateCard(creditCard)
    }

    fun deleteCard(cardNumber: String) {
        dataSource.deleteCard(cardNumber)
    }
}