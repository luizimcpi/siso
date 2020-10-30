package com.devlhse.siso.domain.model.response

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class CustomerResponse (
        val id: UUID,
        val userId: Long,
        val name: String,
        val email: String,
        val mobilePhone: String,
        val phone: String,
        val birthDate: LocalDate,
        val document: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
