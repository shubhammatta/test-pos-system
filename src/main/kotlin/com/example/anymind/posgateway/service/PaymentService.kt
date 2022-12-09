package com.example.anymind.posgateway.service

import com.baidu.fsg.uid.impl.DefaultUidGenerator
import com.example.anymind.posgateway.model.PaymentDO
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.repository.PaymentRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class PaymentService(
    val clock: Clock,
    val paymentRepository: PaymentRepository,
    val uidGenerator: DefaultUidGenerator,
    val objectMapper: ObjectMapper
) {

    fun pay(payRequest: PayRequest): PaymentDO {
        val uid = uidGenerator.uid
        // TODO: Get final price
        val metadata = objectMapper.convertValue(payRequest.additionalItem, PaymentMethodMetadata::class.java)
        val paymentDO = PaymentDO(
            uid,
            payRequest.price.toDouble(),
            0,
            payRequest.customerId,
            payRequest.price.toInt(),
            payRequest.priceModifier,
            payRequest.datetime,
            metadata
        )
        paymentRepository.insert(paymentDO)
        return paymentDO
    }
}