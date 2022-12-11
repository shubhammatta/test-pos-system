package com.example.anymind.posgateway.model

import com.example.anymind.posgateway.enums.CouriersEnum
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PaymentMethodMetadata(
    @JsonProperty("last_4")
    val last4Digits: String? = null,
    @JsonProperty("courier_service")
    val courierService: CouriersEnum? = null,
    @JsonProperty("store_bank")
    val storeBank: String? = null,
    @JsonProperty("account_number")
    val accountNumber: String? = null,
    @JsonProperty("cheque_number")
    val chequeNumber: String? = null,
) {


    companion object {
        val objectMapper = ObjectMapper()

        fun from(jsonNode: JsonNode?) = jsonNode?.let {
            objectMapper.convertValue(jsonNode, PaymentMethodMetadata::class.java)
        }?: PaymentMethodMetadata()
    }
}
