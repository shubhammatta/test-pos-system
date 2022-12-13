package com.example.anymind.posgateway.controller

import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.model.request.PaymentHistoryRequest
import com.example.anymind.posgateway.model.response.PayResponse
import com.example.anymind.posgateway.model.response.PaymentHistoryResponse
import com.example.anymind.posgateway.model.response.Sales
import com.example.anymind.posgateway.service.PaymentService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


@RestController
class PosGatewayController(
    val paymentService: PaymentService,
    val validator: Validator
) {

    @PostMapping("/v1/pay")
    fun pay(@RequestBody paymentRequest: PayRequest): ResponseEntity<PayResponse> {
        val violations = validator.validate(paymentRequest)
        if(violations.isNotEmpty()) {
            log.error(violations.toString())
            throw ConstraintViolationException(violations)
        }

        log.info("/v1/pay Payment Request $paymentRequest")
        val paymentDO = paymentService.pay(paymentRequest)
        return ResponseEntity.accepted().body(paymentDO.convertToResponse())
    }

    @GetMapping("/v1/payment-history")
    fun getHistory(@RequestBody @Valid paymentHistoryRequest: PaymentHistoryRequest): ResponseEntity<PaymentHistoryResponse> {
        log.info("/v1/payment-history Payment History Request $paymentHistoryRequest")
        val violations = validator.validate(paymentHistoryRequest)
        if(violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
        val sales = paymentService.getHistory(paymentHistoryRequest).map { Sales.from(it) }
        return ResponseEntity.ok().body(PaymentHistoryResponse(sales))
    }


    companion object {
        val log = LoggerFactory.getLogger("PosGatewayController")
    }
}