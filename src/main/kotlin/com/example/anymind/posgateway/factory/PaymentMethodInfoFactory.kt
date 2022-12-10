package com.example.anymind.posgateway.factory

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import org.springframework.stereotype.Component

@Component
class PaymentMethodInfoFactory(
    val paymentMethodsConfig: PaymentMethodsConfig
) {

    fun getPaymentMethodInfo(paymentMethodEnum: PaymentMethodsEnum): PaymentMethodInfo {
        if (paymentMethodsConfig.methods.containsKey(paymentMethodEnum)) return paymentMethodsConfig.methods[paymentMethodEnum]!!
        throw PaymentMethodNotFoundException("Payment method $paymentMethodEnum not found in config")
    }
}