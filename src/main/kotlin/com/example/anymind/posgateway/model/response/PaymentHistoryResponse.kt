package com.example.anymind.posgateway.model.response

import com.example.anymind.posgateway.model.PaymentDO
import java.time.Instant

data class PaymentHistoryResponse(
    val sales: List<Sales>
)

data class Sales(
    val datetime: Instant,
    val sales: String,
    val points: Int
) {
    companion object {
        fun from(paymentDO: PaymentDO): Sales {
            return Sales(paymentDO.createdAt, paymentDO.finalPrice.toString(), paymentDO.points)
        }
    }
}