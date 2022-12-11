package com.example.anymind.posgateway.model

import com.example.anymind.posgateway.model.response.PayResponse
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PaymentDO(
    val uid: Long,
    val finalPrice: Double,
    val points: Int,
    val customerId: String,
    val requestedPrice: Double,
    val priceModifier: Double,
    val createdAt: Instant,
    val metadata: PaymentMethodMetadata,
) {

    fun convertToResponse() = PayResponse(finalPrice.toString(), points)


}
