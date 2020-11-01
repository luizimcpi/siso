package com.devlhse.siso.domain.model.request

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CustomerRequest (

        @field:NotNull(message = "{customer_name.required}")
        @field:NotEmpty(message = "{customer_name.empty}")
        val name: String?,

        @field:NotNull(message = "{customer_email.required}")
        @field:NotEmpty(message = "{customer_email.empty}")
        @field:Email(message = "{customer_email.invalid}" )
        val email: String?,

        @field:NotNull(message = "{customer_mobilePhone.required}")
        @field:NotEmpty(message = "{customer_mobilePhone.empty}")
        val mobilePhone: String?,

        val phone: String?,

        val birthDate: LocalDate,

        val document: String?
)
