package com.example.anymind.posgateway.model

import com.fasterxml.jackson.databind.JsonNode
import java.time.Instant

data class PaymentDO(
    val uid: Long,
    val finalPrice: Int,
    val customerId: String,
    val requestedPrice: Int,
    val priceModifier: Double,
    val createdAt: Instant,
    val metadata: PaymentMethodMetadata,
) {
}
