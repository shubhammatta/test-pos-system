package com.example.anymind.posgateway.factory

import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.validator.PaymentMethodValidator
import org.springframework.stereotype.Component
import java.util.*

@Component
class PaymentMethodValidatorFactory(
    validators: List<PaymentMethodValidator>
) {
    var validatorMap: EnumMap<PaymentMethodsEnum, PaymentMethodValidator> = EnumMap(PaymentMethodsEnum::class.java)

    init {
        validators.forEach {
            validatorMap[it.getPaymentMethod()] = it
        }
    }

    fun getValidator(paymentMethod: String): PaymentMethodValidator {
        val payMethodEnum = PaymentMethodsEnum.from(paymentMethod)
        return validatorMap[payMethodEnum]
            ?: throw PaymentMethodNotFoundException("Validator for payment method enum not found $payMethodEnum")
    }
}