package com.example.anymind.posgateway.validator

import com.example.anymind.posgateway.config.PaymentMethodInfo
import javax.validation.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest

interface IValidator {
    fun validate(payRequest: PayRequest)


}