package com.devlhse.siso.domain.service

import com.devlhse.siso.domain.extensions.toEntity
import com.devlhse.siso.domain.extensions.toResponse
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import com.devlhse.siso.domain.validation.ValidationUtil
import com.devlhse.siso.resources.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val repository: CustomerRepository,
                      val validationUtil: ValidationUtil) {

    fun create(request: CustomerRequest, userId: Long): CustomerResponse {
        validationUtil.validate(request)
        //criar service especifico com feign para chamada ao auth service via feign
        val customer = repository.save(request.toEntity(userId))
        return customer.toResponse()
    }
}