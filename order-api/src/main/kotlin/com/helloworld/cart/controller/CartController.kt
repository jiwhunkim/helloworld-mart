package com.helloworld.cart.controller

import com.helloworld.cart.controller.swagger.CartControllerSwaggerInterface
import com.helloworld.cart.data.CreateCartDto
import com.helloworld.cart.service.CartApplicationService
import com.helloworld.config.HelloworldUser
import com.helloworld.data.cart.CartDto
import com.helloworld.domain.common.data.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController(value = "CartControllerV1")
@RequestMapping(
    value = ["/order/carts"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartController(val cartApplicationService: CartApplicationService): CartControllerSwaggerInterface {
    @PostMapping
    override fun create(
        @HelloworldUser user: User,
        @RequestBody createCartDto: CreateCartDto,
    ): CartDto {
        return cartApplicationService.create(user, createCartDto)
    }
}