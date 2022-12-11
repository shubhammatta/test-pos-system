package com.example.anymind.posgateway.enums

import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException

enum class PaymentMethodsEnum {
    CASH,
    CASH_ON_DELIVERY,
    VISA,
    MASTERCARD,
    AMEX,
    JCB,
    LINEPAY,
    PAYPAY,
    POINTS,
    GRABPAY,
    BANKTRANSER,
    CHEQUE;


    companion object {
        final fun from(value: String) = PaymentMethodsEnum.values().find {
            it.name == value
        }?: throw PaymentMethodNotFoundException("Payment Method not found $value")
    }

}