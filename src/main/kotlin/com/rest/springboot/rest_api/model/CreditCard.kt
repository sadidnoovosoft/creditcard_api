package com.rest.springboot.rest_api.model

data class CreditCard(
    val cardNumber: String,
    val cardholderName: String,
    val creditLimit: Double
)