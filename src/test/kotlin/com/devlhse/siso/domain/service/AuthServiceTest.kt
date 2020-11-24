package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.domain.util.Constants.AUTH_SECRET_KEY
import com.devlhse.siso.resources.client.AuthorizationClient
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.core.env.Environment


class AuthServiceTest {

    private lateinit var authorizationClient: AuthorizationClient
    private lateinit var env: Environment
    private lateinit var authService: AuthService


    @BeforeEach
    fun beforeEach(){
        authorizationClient = mockk(relaxed = true)
        env = mockk(relaxed = true)
        authService = AuthService(authorizationClient, env)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    val expiredBearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2LCJpYXQiOjE2MDQyODY2NDgsImV4cCI6MTYwNDI5MDI0OH0.tCP6WsqpY-xG1D2M5Kpxe6zkReGRIen9wAbNanwZZ9E"
    val invalidBearerToken = "Bearer teste"

    @Test
    fun `givenInvalidExpiredToken_whenCallAuthenticate_shouldThrowUnauthorizedException`() {
        every { env.getProperty(AUTH_SECRET_KEY) } returns "GmpGT97dooIJGBsknf30uhYLxveSBCFM"
        justRun { authorizationClient.authenticate(expiredBearerToken) }
        assertThrows<UnauthorizedException> {
            authService.authenticate(bearerToken = expiredBearerToken)
        }
    }

    @Test
    fun `givenInvalidToken_whenCallAuthenticate_shouldThrowUnauthorizedException`() {
        assertThrows<UnauthorizedException> {
            authService.authenticate(bearerToken = invalidBearerToken)
        }
    }
}