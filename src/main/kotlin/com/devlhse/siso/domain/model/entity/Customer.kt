package com.devlhse.siso.domain.model.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
data class Customer(
        @Id
        val id: UUID,
        @Column(name = "user_id")
        val userId: Long,
        val name: String,
        val email: String,
        @Column(name = "mobile_phone")
        val mobilePhone: String,
        val phone: String,
        @Column(name = "birth_date")
        val birthDate: LocalDate,
        val document: String,
        @Column(name = "created_at")
        val createdAt: LocalDateTime,
        @Column(name = "updated_at")
        val updatedAt: LocalDateTime
)