package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.extensions.toEntity
import com.devlhse.siso.domain.extensions.toResponse
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import com.devlhse.siso.resources.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerService(val repository: CustomerRepository) {

    val log: Logger = LoggerFactory.getLogger(CustomerService::class.java)

    fun create(request: CustomerRequest, userId: Long): CustomerResponse {
        log.info("Creating customer with data: $request")

        val customer = repository.save(request.toEntity(userId))

        log.info("Customer has been created")
        return customer.toResponse()
    }
}