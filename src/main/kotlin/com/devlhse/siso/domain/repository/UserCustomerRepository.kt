package com.devlhse.siso.domain.repository

import com.devlhse.siso.domain.model.entity.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface UserCustomerRepository {
    fun save(customer: Customer) : Customer
    fun findByIdAndUserId(id: UUID, userId: Long): Customer
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Customer>
}
