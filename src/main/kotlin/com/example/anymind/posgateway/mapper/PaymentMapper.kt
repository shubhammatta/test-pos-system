package com.example.anymind.posgateway.mapper

import com.example.anymind.posgateway.model.PaymentDO
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.example.anymind.posgateway.persistence.PaymentMethodMetadataTypeHandler
import org.apache.ibatis.annotations.ConstructorArgs
import org.apache.ibatis.annotations.*
import org.apache.ibatis.type.JdbcType
import java.time.Instant

@Mapper
interface PaymentMapper {

    @ConstructorArgs(
        Arg(column = "uid", jdbcType = JdbcType.BIGINT, javaType = Long::class, id = true),
        Arg(column = "final_price", jdbcType = JdbcType.VARCHAR, javaType = Double::class, id = true),
        Arg(column = "customer_id", jdbcType = JdbcType.VARCHAR, javaType = String::class, id = true),
        Arg(column = "requested_price", jdbcType = JdbcType.VARCHAR, javaType = Double::class, id = true),
        Arg(column = "price_modifier", jdbcType = JdbcType.DOUBLE, javaType = Double::class, id = true),
        Arg(column = "created_at", jdbcType = JdbcType.TIMESTAMP, javaType = Instant::class, id = true),
        Arg(
            column = "metadata",
            jdbcType = JdbcType.CHAR,
            typeHandler = PaymentMethodMetadataTypeHandler::class,
            javaType = PaymentMethodMetadata::class
        ),
    )
    @Select(
        """
            select uid, final_price, customer_id, requested_price, price_modifier, created_at, metadata
            from payments
            where uid = #{paymentId, jdbcType=BIGINT}
        """
    )
    fun selectByPrimaryKey(paymentId: Long): PaymentDO?

    @Insert(
        """
        insert into payments (
            uid,
            final_price,
            points,
            customer_id,
            requested_price,
            price_modifier,
            created_at,
            metadata
        )
        values (
            #{uid, jdbcType=BIGINT},
            #{finalPrice, jdbcType=DOUBLE},
            #{points, jdbcType=INTEGER},
            #{customerId, jdbcType=VARCHAR},
            #{requestedPrice, jdbcType=DOUBLE},
            #{priceModifier, jdbcType=DOUBLE},
            #{createdAt, jdbcType=TIMESTAMP},
            #{metadata, jdbcType=CHAR},
        )
        """
    )
    fun insert(
        uid: Long,
        finalPrice: Double,
        points: Int,
        customerId: String,
        requestedPrice: Double,
        priceModifier: Double,
        createdAt: Instant,
        metadata: PaymentMethodMetadata
    ): Int


    @ConstructorArgs(
        Arg(column = "uid", jdbcType = JdbcType.BIGINT, javaType = Long::class, id = true),
        Arg(column = "final_price", jdbcType = JdbcType.VARCHAR, javaType = Double::class, id = true),
        Arg(column = "customer_id", jdbcType = JdbcType.VARCHAR, javaType = String::class, id = true),
        Arg(column = "requested_price", jdbcType = JdbcType.VARCHAR, javaType = Double::class, id = true),
        Arg(column = "price_modifier", jdbcType = JdbcType.DOUBLE, javaType = Double::class, id = true),
        Arg(column = "created_at", jdbcType = JdbcType.TIMESTAMP, javaType = Instant::class, id = true),
        Arg(
            column = "metadata",
            jdbcType = JdbcType.CHAR,
            typeHandler = PaymentMethodMetadataTypeHandler::class,
            javaType = PaymentMethodMetadata::class
        ),
    )
    @Select(
        """
            select uid, final_price, customer_id, requested_price, price_modifier, created_at, metadata
            from payments
            where uid = #{paymentId, jdbcType=BIGINT}
        """
    )
    fun selectInRange(start: Instant, end: Instant): List<PaymentDO>?

}