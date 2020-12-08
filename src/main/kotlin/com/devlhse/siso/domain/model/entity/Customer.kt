package com.devlhse.siso.domain.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
data class Customer(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: UUID? = null,

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

        @Column(name = "created_at", updatable = false)
        @CreationTimestamp
        val createdAt: LocalDateTime? = null,

        @Column(name = "updated_at")
        @UpdateTimestamp
        val updatedAt: LocalDateTime? = null
)
