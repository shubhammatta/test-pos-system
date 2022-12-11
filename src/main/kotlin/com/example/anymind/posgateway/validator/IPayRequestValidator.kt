package com.example.anymind.posgateway.validator

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import com.example.anymind.posgateway.exception.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.paymentmethod.AmexValidator

abstract class IPayRequestValidator(paymentMethodsConfig: PaymentMethodsConfig): IValidator {
    val paymentMethodInfo: PaymentMethodInfo = paymentMethodsConfig.methods[paymentMethod]
        ?: throw PaymentMethodNotFoundException("Payment Config Missing for ${paymentMethod}")

    protected abstract val paymentMethod: PaymentMethodsEnum
    abstract fun validateMetadata(payRequest: PayRequest)

    fun limitCheck(payRequest: PayRequest, paymentMethodInfo: PaymentMethodInfo) {
        if (payRequest.priceModifier !in paymentMethodInfo.lowerLimitModifier..paymentMethodInfo.upperLimitModifier) {
            throw ValidationException("price modifier out of range ${paymentMethodInfo.lowerLimitModifier}..${paymentMethodInfo.upperLimitModifier}")
        }
    }
}