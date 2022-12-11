package com.example.anymind.posgateway.model.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PayRequest(
    val customerId: String,
    val price: String,
    var priceModifier: Double,
    val paymentMethod: String,
    val datetime: Instant,
    val additionalItem: JsonNode?
) {
    val requestedPrice: Double by lazy {
        price.toDouble()
    }
}
