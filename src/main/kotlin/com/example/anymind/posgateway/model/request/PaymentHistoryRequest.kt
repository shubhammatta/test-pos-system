package com.example.anymind.posgateway.model.request

import java.time.Instant

data class PaymentHistoryRequest(
    val startDateTime: Instant,
    val endDateTime: Instant
)