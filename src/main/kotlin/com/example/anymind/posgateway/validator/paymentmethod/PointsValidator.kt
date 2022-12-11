package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component

@Component
class PointsValidator(paymentMethodsConfig: PaymentMethodsConfig) : PaymentMethodValidator(paymentMethodsConfig) {
    override val paymentMethod = PaymentMethodsEnum.POINTS
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
    }

    override fun validateMetadata(payRequest: PayRequest) {}
}