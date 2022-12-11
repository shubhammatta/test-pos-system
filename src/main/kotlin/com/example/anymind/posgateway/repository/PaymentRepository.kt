package com.example.anymind.posgateway.repository

import com.example.anymind.posgateway.exception.DatabaseException
import com.example.anymind.posgateway.mapper.PaymentMapper
import com.example.anymind.posgateway.model.PaymentDO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant

@Repository
class PaymentRepository(
    val paymentMapper: PaymentMapper
) {
    fun insert(payment: PaymentDO) {
        val insertedCount = try {
            paymentMapper.insert(
                payment.uid,
                payment.finalPrice,
                payment.points,
                payment.customerId,
                payment.requestedPrice,
                payment.priceModifier,
                payment.createdAt,
                payment.metadata
            )
        } catch (e: DuplicateKeyException) {
            log.error("Duplicate key found for ${payment.uid}")
            throw e
        } catch(e: Exception) {
            log.error("Unknown exception ", e)
            throw e
        }

        if(insertedCount != 1) {
            log.error("Insertion failed for uid: ${payment.uid}")
            throw DatabaseException("Insertion failed for uid: ${payment.uid}")
        }
    }

    fun getHistoryInRange(start: Instant, end: Instant): List<PaymentDO>? {
        val startTimestamp = Timestamp.from(start)
        val endTimestamp = Timestamp.from(end)
        return paymentMapper.selectInRange(startTimestamp, endTimestamp)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(PaymentRepository::class.java)
    }
}