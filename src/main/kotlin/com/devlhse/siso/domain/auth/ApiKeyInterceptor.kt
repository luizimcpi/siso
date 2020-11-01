package com.devlhse.siso.domain.auth

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.resources.client.AuthorizationClient
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor

@Component
class ApiKeyInterceptor(val authClient: AuthorizationClient) : WebRequestInterceptor {

    override fun preHandle(request: WebRequest) {
        val token = request.getHeader("Authorization") ?: throw UnauthorizedException()

        try {
            authClient.authenticate(token)
        } catch (e: Exception) {
            throw UnauthorizedException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {

    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {

    }
}