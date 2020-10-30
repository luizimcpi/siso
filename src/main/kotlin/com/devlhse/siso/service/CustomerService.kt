package com.devlhse.siso.service

import com.devlhse.siso.extensions.toEntity
import com.devlhse.siso.extensions.toResponse
import com.devlhse.siso.model.request.CustomerRequest
import com.devlhse.siso.model.response.CustomerResponse
import com.devlhse.siso.repository.CustomerRepository
import com.devlhse.siso.validation.ValidationUtil
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