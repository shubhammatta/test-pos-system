package com.example.anymind.posgateway.model

import com.example.anymind.posgateway.enums.CouriersEnum
import com.fasterxml.jackson.databind.JsonNode

data class PaymentMethodMetadata(
    val last4Digits: String?,
    val courierService: CouriersEnum?,
    val storeBank: String?,
    val accountNumber: String?,
    val chequeNumber: String?,
) {
    fun from(jsonNode: JsonNode?) = jsonNode?.let {

    }
}
