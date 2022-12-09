package com.example.anymind.posgateway.config

import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix ="payment")
class PaymentMethodConfig(
    val methods: Map<PaymentMethodsEnum, PaymentMethodInfo> = HashMap()
)


data class PaymentMethodInfo(
    val lowerLimitModifier: Double,
    val upperLimitModifier: Double,
    val pointsModifier: Double
)