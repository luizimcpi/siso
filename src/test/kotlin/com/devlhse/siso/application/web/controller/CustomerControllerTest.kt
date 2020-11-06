package com.devlhse.siso.application.web.controller

import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.service.AuthService
import com.devlhse.siso.domain.service.CustomerService
import com.devlhse.siso.resources.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@WebMvcTest(controllers = [CustomerController::class])
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var authService: AuthService

    @MockBean
    private lateinit var customerService: CustomerService

    @MockBean
    private lateinit var customerRepository: CustomerRepository


    @Test
    fun `givenCustomerURIWithPostAndValidCustomerRequest_whenMockMVC_thenResponseCreated`() {

        val customerRequest = CustomerRequest(
                "Customer",
                "customer@customer.com.br",
                "5513999999999",
                "5513999999999",
                LocalDate.of(1990, 1, 1),
                "333.333.333-33"
        )

        `when`(authService.authenticate(anyString())).thenReturn("1")

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .header("Authorization", "Bearer token")
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isCreated)
    }

    @Test
    fun `givenCustomerURIWithPostAndValidCustomerRequestWithoutAuthorization_whenMockMVC_thenResponseBadRequest`() {

        val customerRequest = CustomerRequest(
                "Customer",
                "customer@customer.com.br",
                "5513999999999",
                "5513999999999",
                LocalDate.of(1990, 1, 1),
                "333.333.333-33"
        )

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isBadRequest)
    }


    @Test
    fun `givenCustomerURIWithPostAndInvalidCustomerRequestWhereNameIsEmpty_whenMockMVC_thenResponseBadRequest`() {

        val customerRequest = CustomerRequest(
                "",
                "customer@customer.com.br",
                "5513999999999",
                "5513999999999",
                LocalDate.of(1990, 1, 1),
                "333.333.333-33"
        )

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .header("Authorization", "Bearer token")
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.data").isArray)
                .andExpect(jsonPath("$.data[0]").value("name: Name can´t be empty."))
    }

    @Test
    fun `givenCustomerURIWithPostAndInvalidCustomerRequestWhereNameIsNull_whenMockMVC_thenResponseBadRequest`() {

        val customerRequest = CustomerRequest(
                email = "customer@customer.com.br",
                mobilePhone = "5513999999999",
                phone = "5513999999999",
                birthDate = LocalDate.of(1990, 1, 1),
                document = "333.333.333-33"
        )

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .header("Authorization", "Bearer token")
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.data").isArray)
                .andExpect(jsonPath("$.data", hasItem("name: Name can´t be empty.")))
                .andExpect(jsonPath("$.data", hasItem("name: Name is required.")))
    }
}