package com.example.anymind.posgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PosGatewayApplication

fun main(args: Array<String>) {
	runApplication<PosGatewayApplication>(*args)
}
