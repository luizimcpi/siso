package com.devlhse.siso.resources.repository

import com.devlhse.siso.domain.model.entity.Customer
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID

interface CustomerRepository : PagingAndSortingRepository<Customer, String> {
    fun findByIdAndUserId(id: UUID, userId: Long): Customer
}
