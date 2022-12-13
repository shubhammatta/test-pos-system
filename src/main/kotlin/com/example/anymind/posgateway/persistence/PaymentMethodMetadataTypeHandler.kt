package com.example.anymind.posgateway.persistence

import com.example.anymind.posgateway.config.MyBatisConfig
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.slf4j.LoggerFactory
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.logging.Logger


@MappedJdbcTypes(JdbcType.CHAR)
class PaymentMethodMetadataTypeHandler : BaseTypeHandler<PaymentMethodMetadata>() {

    private val objectMapper: ObjectMapper
        get() = MyBatisConfig.objectMapper


    override fun setNonNullParameter(preparedStatement: PreparedStatement, i: Int, o: PaymentMethodMetadata, jdbcType: JdbcType?) {
        preparedStatement.setString(i, objectMapper.writeValueAsString(o))
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): PaymentMethodMetadata? {
        return load(rs?.getString(columnName))
    }
    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): PaymentMethodMetadata? {
        return load(rs?.getString(columnIndex))
    }
    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): PaymentMethodMetadata?{
        return load(cs?.getString(columnIndex))
    }

    private fun convertToObject(databaseValue: String): PaymentMethodMetadata {
        return objectMapper.readValue(databaseValue, PaymentMethodMetadata::class.java)
    }

    private fun load(databaseValue: String?): PaymentMethodMetadata? {
        return databaseValue?.let { convertToObject(it) }
    }

    companion object {
        val log = LoggerFactory.getLogger("PaymentMethodMetadataTypeHandler")
    }
}