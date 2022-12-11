package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.IPayRequestValidator
import com.example.anymind.posgateway.validator.IValidator
import org.springframework.stereotype.Component

@Component
class PaypayValidator(paymentMethodsConfig: PaymentMethodsConfig) : IPayRequestValidator(paymentMethodsConfig) {
    override val paymentMethod = PaymentMethodsEnum.PAYPAY
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        TODO("Not yet implemented")
    }

    companion object {
        val paymentMethod = PaymentMethodsEnum.AMEX
    }
}