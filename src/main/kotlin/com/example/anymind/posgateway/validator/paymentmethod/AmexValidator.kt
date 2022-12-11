package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.IPayRequestValidator
import com.example.anymind.posgateway.validator.IValidator
import org.springframework.stereotype.Component

@Component
class AmexValidator(paymentMethodsConfig: PaymentMethodsConfig) : IPayRequestValidator(paymentMethodsConfig) {
    override val paymentMethod = PaymentMethodsEnum.AMEX

    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }
}