package com.devlhse.siso.repository

import com.devlhse.siso.model.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String> {
}