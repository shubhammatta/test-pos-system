package com.example.anymind.posgateway.model

import com.example.anymind.posgateway.model.response.PayResponse
import java.time.Instant

data class PaymentDO(
    val uid: Long,
    val finalPrice: Double,
    val points: Int,
    val customerId: String,
    val requestedPrice: Int,
    val priceModifier: Double,
    val createdAt: Instant,
    val metadata: PaymentMethodMetadata,
) {

    fun convertToResponse() = PayResponse(finalPrice.toString(), points)


}
