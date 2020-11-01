package com.devlhse.siso.resources.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("authorization")
interface AuthorizationClient {

    @RequestMapping(method = [RequestMethod.POST], value = ["\${auth-service.authentication.url}"], consumes = ["application/json"])
    fun authenticate(@RequestHeader("Authorization") token: String)
}
