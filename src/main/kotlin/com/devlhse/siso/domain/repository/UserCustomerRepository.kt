package com.devlhse.siso.domain.repository

import com.devlhse.siso.domain.model.entity.Customer
import java.util.UUID

interface UserCustomerRepository {
    fun save(customer: Customer) : Customer
    fun findByIdAndUserId(id: UUID, userId: Long): Customer
}