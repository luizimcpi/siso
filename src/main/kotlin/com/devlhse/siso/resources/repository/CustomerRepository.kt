package com.devlhse.siso.resources.repository

import com.devlhse.siso.domain.model.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String> {
}