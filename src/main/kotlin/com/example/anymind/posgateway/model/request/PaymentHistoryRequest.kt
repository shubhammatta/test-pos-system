package com.example.anymind.posgateway.model.request

import java.time.Instant
import javax.validation.constraints.NotNull

data class PaymentHistoryRequest(
    @field:NotNull(message = "Start data must not be null")
    val startDateTime: Instant?,
    @field:NotNull(message = "End data must not be null")
    val endDateTime: Instant?
)