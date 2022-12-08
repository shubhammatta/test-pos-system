package com.example.anymind.posgateway.persistence

import com.example.anymind.posgateway.config.MyBatisConfig
import com.example.anymind.posgateway.model.PaymentMethodMetadata
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.apache.ibatis.type.MappedTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


@MappedJdbcTypes(JdbcType.CHAR)
class PaymentMethodMetadataTypeHandler : BaseTypeHandler<PaymentMethodMetadata>() {

    private val objectMapper: ObjectMapper
        get() = MyBatisConfig.objectMapper


    override fun setNonNullParameter(preparedStatement: PreparedStatement, i: Int, o: PaymentMethodMetadata, jdbcType: JdbcType?) {
        preparedStatement.setString(i, objectMapper.writeValueAsString(o))
    }

    final override fun getNullableResult(rs: ResultSet?, columnName: String?): PaymentMethodMetadata? = load(rs?.getString(columnName))
    final override fun getNullableResult(rs: ResultSet?, columnIndex: Int): PaymentMethodMetadata? = load(rs?.getString(columnIndex))
    final override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): PaymentMethodMetadata? = load(cs?.getString(columnIndex))

    fun convertToObject(databaseValue: String): PaymentMethodMetadata {
        return objectMapper.convertValue(databaseValue, PaymentMethodMetadata::class.java)
    }

    private fun load(databaseValue: String?): PaymentMethodMetadata? {
        return databaseValue?.let { convertToObject(it) }
    }
}