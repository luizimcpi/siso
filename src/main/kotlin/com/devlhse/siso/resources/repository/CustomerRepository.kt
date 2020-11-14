package com.devlhse.siso.resources.repository

import com.devlhse.siso.domain.model.entity.Customer
import com.devlhse.siso.domain.repository.UserCustomerRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID

interface CustomerRepository : UserCustomerRepository, PagingAndSortingRepository<Customer, String> {
    override fun findByIdAndUserId(id: UUID, userId: Long): Customer
}
