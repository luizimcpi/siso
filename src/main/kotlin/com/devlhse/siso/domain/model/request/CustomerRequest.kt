package com.devlhse.siso.domain.model.request

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest (
        @NotEmpty(message = "Name cant be blank")
        val name: String,

        @NotEmpty(message = "email cant be blank")
        @Email(message = "Invalid email address" )
        val email: String,

        @NotEmpty(message = "Mobile phone cant be blank")
        val mobilePhone: String,

        val phone: String?,

        @NotEmpty(message = "Birth date cant be blank")
        val birthDate: LocalDate,

        val document: String?
)
