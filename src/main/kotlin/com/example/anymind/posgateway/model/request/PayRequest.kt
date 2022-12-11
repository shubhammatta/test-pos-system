package com.example.anymind.posgateway.model.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PayRequest(
    @NotNull
    val customerId: String,
    @NotNull
    @Positive
    val price: String,
    @NotNull
    var priceModifier: Double,
    @NotNull
    val paymentMethod: String,
    @NotNull
    val datetime: Instant,
    val additionalItem: JsonNode?
) {
    val requestedPrice: Double by lazy {
        price.toDouble()
    }
}
