package com.example.anymind.posgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CommonsRequestLoggingFilter
import java.time.Clock


@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class PosGatewayApplication {


	@Bean
	fun clock() : Clock = Clock.systemDefaultZone()

	@Bean
	fun requestLoggingFilter(): CommonsRequestLoggingFilter? {
		val loggingFilter = CommonsRequestLoggingFilter()
		loggingFilter.setIncludeClientInfo(true)
		loggingFilter.setIncludeQueryString(true)
		loggingFilter.setIncludePayload(true)
		loggingFilter.setMaxPayloadLength(64000)
		return loggingFilter
	}
}

fun main(args: Array<String>) {
	runApplication<PosGatewayApplication>(*args)
}
