package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.factory.PaymentMethodInfoFactory
import com.example.anymind.posgateway.constants.Constants
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component

@Component
class JcbValidator(paymentMethodInfoFactory: PaymentMethodInfoFactory) : PaymentMethodValidator(paymentMethodInfoFactory) {
    override val paymentMethod
        get() = PaymentMethodsEnum.JCB
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        val node = payRequest.additionalItem?.get(Constants.LAST_4_DIGITS)?.asText()
            ?: throw ValidationException("Last 4 digits not found")
        val last4Digits = node.toIntOrNull() ?: throw ValidationException("Last 4 digits are not valid")
        if(last4Digits < 0  || last4Digits.toString().length  > 4) throw ValidationException("Last 4 digits are not valid")
    }
}