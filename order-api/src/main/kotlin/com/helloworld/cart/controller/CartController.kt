package com.helloworld.cart.controller

import com.helloworld.cart.controller.swagger.CartControllerSwaggerInterface
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
class CartController(val cartApplicationService: CartApplicationService): CartControllerSwaggerInterface {
    @PostMapping
    override fun create(
        @Parameter(hidden = true) @HelloworldUser user: User,
        @RequestBody createCartDto: CreateCartDto,
    ): CartDto {
        return cartApplicationService.create(user, createCartDto)
    }
}