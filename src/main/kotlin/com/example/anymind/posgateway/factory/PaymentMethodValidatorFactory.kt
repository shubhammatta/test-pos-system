package com.example.anymind.posgateway.factory

import com.example.anymind.posgateway.validator.IValidator
import org.springframework.stereotype.Component

@Component
class PaymentMethodValidatorFactory(
    validators: List<IValidator>) {
}