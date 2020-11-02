package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.resources.client.AuthorizationClient
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class AuthService(val authorizationClient: AuthorizationClient,
                  @Value("\${auth-service.secret-key}") val authServiceSecretKey: String) {

    val log: Logger = LoggerFactory.getLogger(AuthService::class.java)

    fun authenticate(bearerToken: String) : String {
        try {
            authorizationClient.authenticate(bearerToken)

            val jwt = resolveToken(bearerToken)
            val claims: Claims = Jwts.parser()
                    .setSigningKey(authServiceSecretKey.toByteArray())
                    .parseClaimsJws(jwt)
                    .body

            return claims["uid"].toString()
        } catch (e: Exception) {
            log.error(e.message)
            throw UnauthorizedException()
        }
    }

    private fun resolveToken(bearerToken: String): String {
        return bearerToken.substring(7, bearerToken.length)
    }
}