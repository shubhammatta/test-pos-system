package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.factory.PaymentMethodInfoFactory
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component

@Component
class GrabpayValidator(paymentMethodInfoFactory: PaymentMethodInfoFactory) : PaymentMethodValidator(paymentMethodInfoFactory) {
    override val paymentMethod
        get() = PaymentMethodsEnum.GRABPAY
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {}
}