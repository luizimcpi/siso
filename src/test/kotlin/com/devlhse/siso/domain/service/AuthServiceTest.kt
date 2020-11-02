package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.resources.client.AuthorizationClient
import org.junit.jupiter.api.Assertions
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

    @MockBean
    private lateinit var authorizationClient: AuthorizationClient

    @Autowired
    private lateinit var authService: AuthService

    val validBearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE2LCJpYXQiOjE2MDQyODY2NDgsImV4cCI6MTYwNDI5MDI0OH0.tCP6WsqpY-xG1D2M5Kpxe6zkReGRIen9wAbNanwZZ9E"
    val invalidBearerToken = "Bearer teste"

    @Test
    fun givenValidToken_whenCallAuthenticate_shouldGetUserIdInfoFromTokenSuccess() {
        doNothing().`when`(authorizationClient).authenticate(validBearerToken)
        val result = authService.authenticate(bearerToken = validBearerToken)
        val validUserId = "16"
        Assertions.assertEquals(validUserId, result)
    }

    @Test()
    fun givenInvalidToken_whenCallAuthenticate_shouldThrowUnauthorizedException() {
        doNothing().`when`(authorizationClient).authenticate(invalidBearerToken)
        assertThrows<UnauthorizedException> {
            authService.authenticate(bearerToken = invalidBearerToken)
        }
    }
}