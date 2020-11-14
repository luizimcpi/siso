package com.devlhse.siso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages=[
	"com.devlhse.siso.application",
	"com.devlhse.siso.application.config",
	"com.devlhse.siso.application.web",
	"com.devlhse.siso.application.web.controller",
	"com.devlhse.siso.domain",
	"com.devlhse.siso.domain.auth",
	"com.devlhse.siso.domain.service",
	"com.devlhse.siso.domain.validation",
	"com.devlhse.siso.resources",
	"com.devlhse.siso.resources.client",
	"com.devlhse.siso.resources.repository"
])
class SisoCustomerApiApplication

fun main(args: Array<String>) {
	@Suppress("SpreadOperator")
	runApplication<SisoCustomerApiApplication>(*args)
}
