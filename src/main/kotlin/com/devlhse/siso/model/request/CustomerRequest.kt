package com.devlhse.siso.model.request

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CustomerRequest (
        @NotBlank(message = "Name cant be blank")
        val name: String,

        @NotBlank(message = "email cant be blank")
        @Email(message = "Invalid email address" )
        val email: String,

        @NotBlank(message = "Mobile phone cant be blank")
        val mobilePhone: String,

        val phone: String?,

        @NotBlank(message = "Birth date cant be blank")
        val birthDate: LocalDate,

        val document: String?
)
