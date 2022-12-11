package com.example.anymind.posgateway.validator.paymentmethod

import com.example.anymind.posgateway.config.PaymentMethodsConfig
import com.example.anymind.posgateway.constants.Constants
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component

@Component
class BankTransferValidator(paymentMethodsConfig: PaymentMethodsConfig) : PaymentMethodValidator(paymentMethodsConfig) {
    override val paymentMethod = PaymentMethodsEnum.BANKTRANSER
    override fun validate(payRequest: PayRequest) {
        limitCheck(payRequest, paymentMethodInfo)
        validateMetadata(payRequest)
    }

    override fun validateMetadata(payRequest: PayRequest) {
        payRequest.additionalItem?.get(Constants.BANK)
            ?: throw ValidationException("Bank details not found")
        payRequest.additionalItem?.get(Constants.ACCOUNT_NUMBER)
            ?: throw ValidationException("Account number not found")
    }
}