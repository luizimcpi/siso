package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.resources.client.AuthorizationClient
import org.springframework.stereotype.Service

@Service
class AuthService(val authorizationClient: AuthorizationClient) {

    fun authenticate(token: String){
        try {
            authorizationClient.authenticate(token)
        } catch (e: Exception) {
            throw UnauthorizedException()
        }
    }
}