package com.example.anymind.posgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
@ConfigurationPropertiesScan
class PosGatewayApplication {


	@Bean
	fun clock() : Clock = Clock.systemDefaultZone()
}

fun main(args: Array<String>) {
	runApplication<PosGatewayApplication>(*args)
}
