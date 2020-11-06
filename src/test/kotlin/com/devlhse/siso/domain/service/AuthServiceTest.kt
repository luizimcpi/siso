package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.domain.util.Constants.AUTH_SECRET_KEY
import com.devlhse.siso.resources.client.AuthorizationClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.core.env.Environment


@ExtendWith(MockitoExtension::class)
class AuthServiceTest {

    @Mock
    private lateinit var authorizationClient: AuthorizationClient

    @Mock
    private lateinit var env: Environment

    @InjectMocks
    private lateinit var authService: AuthService


    val expiredBearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2LCJpYXQiOjE2MDQyODY2NDgsImV4cCI6MTYwNDI5MDI0OH0.tCP6WsqpY-xG1D2M5Kpxe6zkReGRIen9wAbNanwZZ9E"
    val invalidBearerToken = "Bearer teste"

    @Test
    fun `givenInvalidExpiredToken_whenCallAuthenticate_shouldThrowUnauthorizedException`() {
        `when`(env.getProperty(AUTH_SECRET_KEY)).thenReturn("GmpGT97dooIJGBsknf30uhYLxveSBCFM")
        doNothing().`when`(authorizationClient).authenticate(expiredBearerToken)
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