package com.example.anymind.posgateway.validator

import com.example.anymind.posgateway.config.PaymentMethodInfo
import com.example.anymind.posgateway.exception.ValidationException
import com.example.anymind.posgateway.model.request.PayRequest

interface IValidator {
    fun validate(payRequest: PayRequest)


}