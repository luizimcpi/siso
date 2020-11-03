package com.devlhse.siso.resources.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "authorization", url = "\${auth-service.authentication.url}")
interface AuthorizationClient {

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/authenticate"],
            consumes = ["application/json"])
    fun authenticate(@RequestHeader("Authorization") token: String)
}
