package com.example.anymind.posgateway.model.request

import java.time.Instant
import javax.validation.constraints.NotNull

data class PaymentHistoryRequest(
    @NotNull
    val startDateTime: Instant,
    @NotNull
    val endDateTime: Instant
)