package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.NotFoundException
import com.devlhse.siso.domain.model.entity.Customer
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.repository.UserCustomerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class CustomerServiceTest {

    private lateinit var customerRepository: UserCustomerRepository
    private lateinit var customerService: CustomerService

    @BeforeEach
    fun beforeEach(){
        customerRepository = mockk(relaxed = true)
        customerService = CustomerService(repository = customerRepository)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @Test
    fun `givenValidCustomerRequest_whenCallSave_shouldSuccess`() {
        val validUUID = UUID.randomUUID()
        val validUserId = 1L
        val customer = Customer(
                id = validUUID,
                userId = validUserId,
                name = "Customer",
                email = "customer@gmail.com",
                mobilePhone = "13999999999",
                phone = "1333333333",
                birthDate = LocalDate.of(1990, 3,3),
                document = "947.267.420-86",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )

        val customerRequest = CustomerRequest(
                name = "Customer",
                email = "customer@gmail.com",
                mobilePhone = "13999999999",
                phone = "1333333333",
                birthDate = LocalDate.of(1990, 3,3),
                document = "947.267.420-86"
        )

        every { customerRepository.save(any()) } returns customer

        customerService.create(customerRequest, validUserId).let { customerResponse ->
            assertNotNull(customerResponse)
            assertEquals(validUUID, customerResponse.id)
            assertEquals(1L, customerResponse.userId)
            assertEquals("Customer", customerResponse.name)
        }
    }

    @Test
    fun `givenInvalidUserId_whenCallGetById_shouldThrowNotFoundException`() {
        val validCustomerUUID = UUID.randomUUID()
        val invalidUserId = 2L

        every {
            customerRepository.findByIdAndUserId(validCustomerUUID, invalidUserId)
        } throws NotFoundException("Customer not found")

        assertThrows<NotFoundException> {
            customerService.getById(validCustomerUUID, invalidUserId)
        }
    }

    @Test
    fun `givenValidCustomerIdAndUserId_whenCallGetById_shouldSuccess`() {
        val validUUID = UUID.randomUUID()
        val validUserId = 1L
        val customer = Customer(
                id = validUUID,
                userId = validUserId,
                name = "Customer",
                email = "customer@gmail.com",
                mobilePhone = "13999999999",
                phone = "1333333333",
                birthDate = LocalDate.of(1990, 3,3),
                document = "947.267.420-86",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )

        every { customerRepository.findByIdAndUserId(validUUID, validUserId) } returns customer

        customerService.getById(validUUID, validUserId).let { customerResponse ->
            assertNotNull(customerResponse)
            assertEquals(validUUID, customerResponse.id)
            assertEquals(1L, customerResponse.userId)
            assertEquals("Customer", customerResponse.name)
        }
    }
}
