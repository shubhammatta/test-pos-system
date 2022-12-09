package com.example.anymind.posgateway.config

import com.baidu.fsg.uid.UidGenerator
import com.baidu.fsg.uid.impl.DefaultUidGenerator
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner
import com.baidu.fsg.uid.worker.WorkerIdAssigner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class UidGeneratorConfig {

    @Bean
    fun disposableWorkerIdAssigner(): WorkerIdAssigner? {
        return DisposableWorkerIdAssigner()
    }

    @Bean
    fun uidGenerator(): UidGenerator {
        val uidGenerator = DefaultUidGenerator()
        uidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner())
        uidGenerator.setTimeBits(30)
        uidGenerator.setWorkerBits(20)
        uidGenerator.setSeqBits(13)
        uidGenerator.setEpochStr("2000-01-01")
        return uidGenerator
    }
}