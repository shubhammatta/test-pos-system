package com.example.anymind.posgateway.service

import com.example.anymind.posgateway.model.PaymentDO
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class PaymentService(
    val clock: Clock,

) {

    fun insert(payment: PaymentDO) {

    }
}