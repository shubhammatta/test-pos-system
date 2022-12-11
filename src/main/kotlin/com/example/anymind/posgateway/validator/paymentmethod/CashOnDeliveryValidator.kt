package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.factory.PaymentMethodInfoFactory
import com.example.anymind.posgateway.constants.Constants
import com.example.anymind.posgateway.enums.CouriersEnum
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component

@Component
class CashOnDeliveryValidator(paymentMethodInfoFactory: PaymentMethodInfoFactory) : PaymentMethodValidator(paymentMethodInfoFactory) {
    override val paymentMethod
        get() = PaymentMethodsEnum.CASH_ON_DELIVERY
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        val courier = payRequest.additionalItem?.get(Constants.COURIER_SERVICES)
            ?: throw ValidationException("Courier service empty for Cash on delivery")
        CouriersEnum.values().find { it.name == courier.toString() }
            ?: throw ValidationException("Courier service not in YAMATO or SAGAWA")
    }
}