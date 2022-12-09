package com.example.anymind.posgateway.controller

import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.model.response.PayResponse
import com.example.anymind.posgateway.service.PaymentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PosGatewayController(
    val paymentService: PaymentService
) {

    @PostMapping("/v1/pay")
    fun pay(paymentRequest: PayRequest): PayResponse {
        val paymentDO = paymentService.pay(paymentRequest)
        return paymentDO.convertToResponse()
    }
}