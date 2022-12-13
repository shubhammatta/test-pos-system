package com.example.anymind.posgateway.model.request

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PayRequest(
    @field:NotEmpty(message = "Customer Id should not be empty/null")
    val customerId: String?,
    @field:NotNull(message = "Price must not be null")
    @field:Positive(message = "Price must be > 0")
    val price: String?,
    @field:NotNull(message = "price modifier should be positive")
    var priceModifier: Double?,
    @field:NotNull(message = "method should be present")
    val paymentMethod: String?,
    @field:NotNull(message = "Datetime should be present")
    val datetime: Instant?,
    val additionalItem: JsonNode?
) {
    val requestedPrice: Double by lazy {
        price!!.toDouble()
    }
}
