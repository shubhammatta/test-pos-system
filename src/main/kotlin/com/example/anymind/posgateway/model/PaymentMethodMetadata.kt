package com.example.anymind.posgateway.model

import com.example.anymind.posgateway.CouriersEnum

data class PaymentMethodMetadata(
    val last4: String?,
    val courierService: CouriersEnum?,
    val storeBank: String?,
    val accountNumber: String?,
    val chequeNumber: String?,
)
