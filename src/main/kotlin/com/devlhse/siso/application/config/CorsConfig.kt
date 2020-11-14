package com.devlhse.siso.application.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CorsConfig : Filter {
    val log: Logger = LoggerFactory.getLogger(CorsConfig::class.java)

    override fun init(filterChain: FilterConfig) {
        log.trace("Init cors config")
    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpServletResponse: HttpServletResponse = servletResponse as HttpServletResponse
        val httpServletRequest: HttpServletRequest = servletRequest as HttpServletRequest

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*")
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600")
        httpServletResponse.setHeader(
                "Access-Control-Allow-Headers",
                "accept, authorization, x-requested-with, content-type")

        if (httpServletRequest.method == "OPTIONS")
            try {
                httpServletResponse.status = HttpStatus.OK.value()
                httpServletResponse.writer.print("OK")
                httpServletResponse.writer.flush()
            } catch(e: IOException) {
                log.error("exception on method options")
            }
        else
            filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
        log.trace("destroy cors config")
    }
}
