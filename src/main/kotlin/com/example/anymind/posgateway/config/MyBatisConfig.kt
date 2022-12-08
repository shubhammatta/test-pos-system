package com.example.anymind.posgateway.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration

@Configuration
class MyBatisConfig {
    constructor(objectMapperBean: ObjectMapper) {
        objectMapper = objectMapperBean
    }


    companion object {
        lateinit var objectMapper: ObjectMapper
    }
}