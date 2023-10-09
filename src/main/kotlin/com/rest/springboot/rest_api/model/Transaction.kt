package com.rest.springboot.rest_api.model

import java.time.LocalDateTime

data class Transaction(
    val cardNumber: String,
    val amount: Double,
    val dateTime: LocalDateTime = LocalDateTime.now()
)