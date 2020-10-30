package com.devlhse.siso.controller

import com.devlhse.siso.model.request.CustomerRequest
import com.devlhse.siso.model.response.CustomerResponse
import com.devlhse.siso.model.response.GenericResponse
import com.devlhse.siso.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/customers")
class CustomerController(val customerService: CustomerService) {

    @PostMapping(produces = ["application/json"], consumes = ["application/json"])
    fun createCustomer(@RequestHeader("userId") userId: Long,
                       @RequestBody customerRequest: CustomerRequest): ResponseEntity<GenericResponse<CustomerResponse>> {
        val customerResponse = customerService.create(customerRequest, userId)
        return ResponseEntity.created(URI.create("/customers")).body(GenericResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.toString(),
                data = customerResponse)
        )
    }
}