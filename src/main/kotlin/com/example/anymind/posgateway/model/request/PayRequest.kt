package com.example.anymind.posgateway.model.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.databind.JsonNode
import java.time.Instant

data class PayRequest(
    @JsonAlias("customer_id")
    val customerId: String,
    val price: String,
    @JsonAlias("payment_method")
    val paymentMethod: String,
    @JsonAlias("price_modifier")
    val priceModifier: Double,
    val datetime: Instant,
    @JsonAlias("addition_item")
    val additionalItem: JsonNode?
) {
    val requestedPrice: Double by lazy {
        price.toDouble()
    }
}
