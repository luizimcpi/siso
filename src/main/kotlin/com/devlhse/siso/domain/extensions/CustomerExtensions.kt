package com.devlhse.siso.domain.extensions

import com.devlhse.siso.domain.model.entity.Customer
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import java.time.LocalDateTime
import java.util.UUID

fun CustomerRequest.toEntity(userId: Long): Customer =
        Customer(
                id = UUID.randomUUID(),
                userId = userId,
                name = this.name,
                email = this.email,
                mobilePhone = this.mobilePhone,
                phone = this.phone ?: "",
                birthDate = this.birthDate,
                document = this.document ?: "",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )

fun Customer.toResponse(): CustomerResponse =
        CustomerResponse(
                id = this.id,
                userId = userId,
                name = this.name,
                email = this.email,
                mobilePhone = this.mobilePhone,
                phone = this.phone,
                birthDate = this.birthDate,
                document = this.document,
                createdAt = this.createdAt,
                updatedAt = this.updatedAt
        )