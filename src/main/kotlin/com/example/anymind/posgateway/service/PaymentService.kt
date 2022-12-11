package com.example.anymind.posgateway.service

import com.baidu.fsg.uid.UidGenerator
import com.example.anymind.posgateway.enums.PaymentMethodsEnum
import com.example.anymind.posgateway.exception.DatabaseException
import com.example.anymind.posgateway.factory.PaymentMethodValidatorFactory
import com.example.anymind.posgateway.factory.PaymentMethodInfoFactory
import com.example.anymind.posgateway.model.PaymentDO
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.model.request.PaymentHistoryRequest
import com.example.anymind.posgateway.repository.PaymentRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.Clock
import java.util.logging.Logger
import javax.annotation.PostConstruct
import kotlin.math.roundToInt

@Service
class PaymentService(
    val clock: Clock,
    val paymentRepository: PaymentRepository,
    val uidGenerator: UidGenerator,
    val objectMapper: ObjectMapper,
    val paymentMethodInfoFactory: PaymentMethodInfoFactory,
    val paymentMethodValidatorFactory: PaymentMethodValidatorFactory
) {

    @PostConstruct
    fun postConstruct() {
        log.info(paymentMethodInfoFactory.paymentMethodsConfig.methods.toString())
    }

    fun pay(payRequest: PayRequest): PaymentDO {
        paymentMethodValidatorFactory.getValidator(payRequest.paymentMethod).validate(payRequest)
        val uid = uidGenerator.uid
        val paymentMethodInfo =
            paymentMethodInfoFactory.getPaymentMethodInfo(PaymentMethodsEnum.from(payRequest.paymentMethod))
        // 2 digit precision
        val finalPrice = (payRequest.priceModifier * payRequest.requestedPrice * 100).roundToInt() / 100.00
        val points = (paymentMethodInfo.pointsModifier * payRequest.requestedPrice).toInt()
        val metadata = PaymentMethodMetadata.from(payRequest.additionalItem)
        val paymentDO = PaymentDO(
            uid,
            finalPrice,
            points,
            payRequest.customerId,
            payRequest.requestedPrice,
            payRequest.priceModifier,
            payRequest.datetime,
            metadata
        )
        paymentRepository.insert(paymentDO)
        return paymentDO
    }


    fun getHistory(paymentHistoryRequest: PaymentHistoryRequest): List<PaymentDO> {
        return paymentRepository.getHistoryInRange(
            paymentHistoryRequest.startDateTime,
            paymentHistoryRequest.endDateTime
        ) ?: throw DatabaseException("Could not retrieve history for $paymentHistoryRequest")
    }

    companion object {
        val log = Logger.getLogger(PaymentService::class.simpleName)
    }
}