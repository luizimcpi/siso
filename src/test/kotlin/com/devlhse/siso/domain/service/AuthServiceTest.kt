package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.domain.util.Constants.AUTH_SECRET_KEY
import com.devlhse.siso.resources.client.AuthorizationClient
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @BeforeAll
    fun setUp(){
        System.setProperty(AUTH_SECRET_KEY, "GmpGT97dooIJGBsknf30uhYLxveSBCFM");
    }

    @MockBean
    private lateinit var authorizationClient: AuthorizationClient

    @Autowired
    private lateinit var authService: AuthService

    val expiredBearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2LCJpYXQiOjE2MDQyODY2NDgsImV4cCI6MTYwNDI5MDI0OH0.tCP6WsqpY-xG1D2M5Kpxe6zkReGRIen9wAbNanwZZ9E"
    val invalidBearerToken = "Bearer teste"

    @Test
    fun givenInvalidExpiredToken_whenCallAuthenticate_shouldThrowUnauthorizedException() {
        doNothing().`when`(authorizationClient).authenticate(expiredBearerToken)
        assertThrows<UnauthorizedException> {
            authService.authenticate(bearerToken = invalidBearerToken)
        }
    }

    @Test()
    fun givenInvalidToken_whenCallAuthenticate_shouldThrowUnauthorizedException() {
        doNothing().`when`(authorizationClient).authenticate(invalidBearerToken)
        assertThrows<UnauthorizedException> {
            authService.authenticate(bearerToken = invalidBearerToken)
        }
    }
}