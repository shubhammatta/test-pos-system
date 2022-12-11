package com.example.anymind.posgateway.persistence

import com.example.anymind.posgateway.config.MyBatisConfig
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.logging.Logger


@MappedJdbcTypes(JdbcType.CHAR)
class PaymentMethodMetadataTypeHandler : BaseTypeHandler<PaymentMethodMetadata>() {

    private val objectMapper: ObjectMapper
        get() = MyBatisConfig.objectMapper


    override fun setNonNullParameter(preparedStatement: PreparedStatement, i: Int, o: PaymentMethodMetadata, jdbcType: JdbcType?) {
        log.info("Writing metadata ${objectMapper.writeValueAsString(o)}")
        preparedStatement.setString(i, objectMapper.writeValueAsString(o))
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): PaymentMethodMetadata? {
        log.info("Metadata read ${rs?.getString(columnName)}")
        return load(rs?.getString(columnName))
    }
    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): PaymentMethodMetadata? {
        log.info("Metadata read ${rs?.getString(columnIndex)}")
        return load(rs?.getString(columnIndex))
    }
    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): PaymentMethodMetadata?{
        log.info("Metadata read ${cs?.getString(columnIndex)}")
        return load(cs?.getString(columnIndex))
    }

    private fun convertToObject(databaseValue: String): PaymentMethodMetadata {
        log.info("")
        return objectMapper.readValue(databaseValue, PaymentMethodMetadata::class.java)
    }

    private fun load(databaseValue: String?): PaymentMethodMetadata? {
        log.info("Reading metadata $databaseValue")
        return databaseValue?.let { convertToObject(it) }
    }

    companion object {
        val log = Logger.getLogger("PaymentMethodMetadataTypeHandler")
    }
}