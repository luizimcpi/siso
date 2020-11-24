package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.exception.NotFoundException
import com.devlhse.siso.domain.extensions.toEntity
import com.devlhse.siso.domain.extensions.toResponse
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import com.devlhse.siso.domain.repository.UserCustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(val repository: UserCustomerRepository) {

    val log: Logger = LoggerFactory.getLogger(CustomerService::class.java)

    fun create(request: CustomerRequest, userId: Long): CustomerResponse {
        log.trace("Creating customer with data: $request")

        val customer = repository.save(request.toEntity(userId))

        log.trace("Customer has been created")
        return customer.toResponse()
    }

    fun getById(id: UUID, userId: Long): CustomerResponse {
        log.trace("Searching customer with id: $id and userId: $userId")
        try {
            repository.findByIdAndUserId(id, userId).let {
                log.trace("Customer with id: $id and userId: $userId has been found")
                return it.toResponse()
            }
        } catch (e: EmptyResultDataAccessException){
            log.debug("Customer with id: $id and userId: $userId not found")
            throw NotFoundException("Customer not found!")
        }
    }

    fun findAll(userId: Long, evalPage: Int, maxItensPerPage: Int): Page<CustomerResponse> {
        return repository.findAllByUserId(userId, PageRequest.of(evalPage, maxItensPerPage))
                .map { customer -> customer.toResponse() }
    }

}
