package com.example.anymind.posgateway.model.response

import java.time.Instant

data class PaymentHistoryResponse(
    val sales: List<Sales>
)

data class Sales(
    val datetime: Instant,
    val sales: String,
    val points: Int
)