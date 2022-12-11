package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.constants.Constants
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component

@Component
class VisaValidator(paymentMethodsConfig: PaymentMethodsConfig) : PaymentMethodValidator(paymentMethodsConfig) {
    @NonNull
    override val paymentMethod = PaymentMethodsEnum.VISA

    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        val value = payRequest.additionalItem?.get(Constants.LAST_4_DIGITS)
            ?: throw ValidationException("Last 4 digits not found")
        if (value.size() != 4 || !value.canConvertToExactIntegral() || value.intValue() < 0) throw ValidationException("Last 4 digits are not valid")
    }
}