package com.devlhse.siso.application.web.controller

import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import com.devlhse.siso.domain.model.response.GenericResponse
import com.devlhse.siso.domain.service.AuthService
import com.devlhse.siso.domain.service.CustomerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@Api(value = "Customers")
@RequestMapping("/customers")
class CustomerController(val authService: AuthService,
                         val customerService: CustomerService) {

    @ApiOperation(value = "Create new customer")
    @PostMapping(produces = ["application/json"], consumes = ["application/json"])
    fun createCustomer(@RequestHeader("Authorization") token: String,
                       @RequestBody @Valid customerRequest: CustomerRequest): ResponseEntity<GenericResponse<CustomerResponse>> {

        val userId = authService.authenticate(token)

        val customerResponse = customerService.create(customerRequest, userId.toLong())

        return ResponseEntity.created(URI.create("/customers")).body(GenericResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.toString(),
                data = customerResponse)
        )
    }
}