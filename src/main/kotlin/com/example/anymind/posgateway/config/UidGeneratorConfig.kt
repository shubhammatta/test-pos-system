package com.example.anymind.posgateway.config

import com.baidu.fsg.uid.impl.DefaultUidGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UidGeneratorConfig {

    @Bean
    fun uidGenerator(): DefaultUidGenerator {
        val uidGenerator = DefaultUidGenerator()
        uidGenerator.setTimeBits(30)
        uidGenerator.setWorkerBits(20)
        uidGenerator.setSeqBits(13)
        uidGenerator.setEpochStr("2000-01-01")
        return uidGenerator
    }
}