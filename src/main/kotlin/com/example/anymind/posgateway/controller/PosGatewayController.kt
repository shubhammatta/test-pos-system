package com.example.anymind.posgateway.controller

import com.example.anymind.posgateway.model.request.PayRequest
import com.example.anymind.posgateway.model.request.PaymentHistoryRequest
import com.example.anymind.posgateway.model.response.PayResponse
import com.example.anymind.posgateway.model.response.PaymentHistoryResponse
import com.example.anymind.posgateway.model.response.Sales
import com.example.anymind.posgateway.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
class PosGatewayController(
    val paymentService: PaymentService
) {

    @PostMapping("/v1/pay")
    fun pay(@RequestBody paymentRequest: PayRequest): ResponseEntity<PayResponse> {
        log.info("/v1/pay Payment Request $paymentRequest")
        val paymentDO = paymentService.pay(paymentRequest)
        return ResponseEntity.accepted().body(paymentDO.convertToResponse())
    }

    @GetMapping("/v1/payment-history")
    fun getHistory(@RequestBody paymentHistoryRequest: PaymentHistoryRequest): ResponseEntity<PaymentHistoryResponse> {
        log.info("/v1/payment-history Payment History Request $paymentHistoryRequest")

        val sales = paymentService.getHistory(paymentHistoryRequest).map { Sales.from(it) }
        return ResponseEntity.ok().body(PaymentHistoryResponse(sales))
    }


    companion object {
        val log = Logger.getLogger("PosGatewayController")
    }
}