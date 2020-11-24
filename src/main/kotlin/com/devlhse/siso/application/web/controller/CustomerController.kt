package com.devlhse.siso.application.web.controller

import com.devlhse.siso.domain.exception.NotFoundException
import com.devlhse.siso.domain.model.request.CustomerRequest
import com.devlhse.siso.domain.model.response.CustomerResponse
import com.devlhse.siso.domain.model.response.GenericResponse
import com.devlhse.siso.domain.service.AuthService
import com.devlhse.siso.domain.service.CustomerService
import com.devlhse.siso.domain.util.Constants.MAX_ITENS_PER_PAGE
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID
import javax.validation.Valid

@RestController
@Api(value = "Customers")
@RequestMapping("/customers")
class CustomerController(val authService: AuthService,
                         val customerService: CustomerService) {

    @ApiOperation(value = "Create new customer")
    @PostMapping(produces = ["application/json"], consumes = ["application/json"])
    fun createCustomer(@RequestHeader("Authorization") token: String,
                       @RequestBody @Valid customerRequest: CustomerRequest):
            ResponseEntity<GenericResponse<CustomerResponse>> {

        val userId = authService.authenticate(token)

        val customerResponse = customerService.create(customerRequest, userId.toLong())

        return ResponseEntity.created(URI.create("/customers")).body(GenericResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.toString(),
                data = customerResponse)
        )
    }


    @Throws(NotFoundException::class)
    @ApiOperation(value = "Get customer by id")
    @GetMapping("/{id}", produces = ["application/json"])
    fun getCustomerById(@RequestHeader("Authorization") token: String,
                       @PathVariable("id") id: UUID): ResponseEntity<GenericResponse<CustomerResponse>> {

        val userId = authService.authenticate(token)

        val customerResponse = customerService.getById(id, userId.toLong())

        return ResponseEntity.ok(GenericResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK.toString(),
                data = customerResponse)
        )
    }

    @ApiOperation(value = "Get paged customer by userId")
    @GetMapping(produces = ["application/json"])
    fun getCustomers(@RequestHeader("Authorization") token: String,
                     @RequestParam("page") page: Int): ResponseEntity<GenericResponse<Page<CustomerResponse>>> {

        val userId = authService.authenticate(token)

        val customerResponse = customerService.findAll(userId.toLong(), page, MAX_ITENS_PER_PAGE)

        return ResponseEntity.ok(GenericResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK.toString(),
                data = customerResponse)
        )
    }

}
