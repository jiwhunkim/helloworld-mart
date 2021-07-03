package com.helloworld.order.controller

import com.helloworld.common.domain.data.User
import com.helloworld.config.HelloworldUser
import com.helloworld.order.controller.swagger.CartControllerSwaggerInterface
import com.helloworld.order.data.cart.CreateCartDto
import com.helloworld.order.domain.dto.CartDto
import com.helloworld.order.service.CartApplicationService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "CartControllerV1")
@RequestMapping(
    value = ["/order/carts"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartController(val cartApplicationService: CartApplicationService) : CartControllerSwaggerInterface {
    @PostMapping
    override fun create(
        @Parameter(hidden = true) @HelloworldUser user: User,
        @RequestBody createCartDto: CreateCartDto,
    ): CartDto {
        return cartApplicationService.create(user, createCartDto)
    }
}
