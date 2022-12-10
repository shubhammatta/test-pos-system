package com.example.anymind.posgateway.config

import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.mapper.PaymentMapper
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties(prefix ="payment")
@ConstructorBinding
data class PaymentMethodsConfig(
    val methods: Map<PaymentMethodsEnum, PaymentMethodInfo>
)


data class PaymentMethodInfo(
    val lowerLimitModifier: Double,
    val upperLimitModifier: Double,
    val pointsModifier: Double
)