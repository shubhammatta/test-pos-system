package com.example.anymind.posgateway.validator

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import com.example.anymind.posgateway.factory.PaymentMethodInfoFactory
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest

abstract class PaymentMethodValidator(
    paymentMethodInfoFactory: PaymentMethodInfoFactory
): IValidator {
    val paymentMethodInfo: PaymentMethodInfo = paymentMethodInfoFactory.getPaymentMethodInfo(paymentMethod)

    abstract val paymentMethod: PaymentMethodsEnum

    abstract fun validateMetadata(payRequest: PayRequest)

    fun limitCheck(payRequest: PayRequest, paymentMethodInfo: PaymentMethodInfo) {
        if (payRequest.priceModifier !in paymentMethodInfo.lowerLimitModifier..paymentMethodInfo.upperLimitModifier) {
            throw ValidationException("price modifier out of range ${paymentMethodInfo.lowerLimitModifier}..${paymentMethodInfo.upperLimitModifier}")
        }
    }
}