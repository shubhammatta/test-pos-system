package com.example.anymind.posgateway.enums

import com.example.anymind.posgateway.exception.PaymentMethodNotFoundException

enum class PaymentMethodsEnum(value: String) {
    CASH("CASH"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY"),
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    AMEX("AMEX"),
    JCB("JCB"),
    LINEPAY("LINEPAY"),
    PAYPAY("PAYPAT=Y"),
    POINTS("POINTS"),
    GRABPAY("GRABPAY"),
    BANKTRANSER("BANKTRANSER"),
    CHEQUE("CHEQUE");


    companion object {
        final fun from(value: String) = PaymentMethodsEnum.values().find {
            it.name == value
        }?: throw PaymentMethodNotFoundException("Payment Method not found $value")
    }

}