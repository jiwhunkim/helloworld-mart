package com.helloworld.cart.controller

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.cart.service.CartApplicationService
import com.helloworld.config.HelloworldUser
import com.helloworld.data.cart.CartDto
import com.helloworld.domain.common.data.User
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "CartControllerV1")
@RequestMapping(
    value = ["/order/carts"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartController(val cartApplicationService: CartApplicationService) {
    @PostMapping
    @Parameters(
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Authorization",
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Accept-Language",
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Channel-Type",
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(`in` = ParameterIn.HEADER, name = "Os-Code", content = [Content(schema = Schema(type = "string"))]),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Os-Version",
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(`in` = ParameterIn.HEADER, name = "Device-Id", content = [Content(schema = Schema(type = "string"))]),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Accept-Language",
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "App-Version",
            content = [Content(schema = Schema(type = "string"))]
        ),
    )
    fun create(
        @Parameter(hidden = true) @HelloworldUser user: User,
        @RequestBody createCartDto: CreateCartDto,
    ): CartDto {
        return cartApplicationService.create(user, createCartDto)
    }
}