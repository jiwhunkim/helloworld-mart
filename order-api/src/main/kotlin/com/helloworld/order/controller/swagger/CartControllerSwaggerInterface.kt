package com.helloworld.order.controller.swagger

import com.helloworld.common.domain.data.User
import com.helloworld.config.HelloworldUser
import com.helloworld.order.data.cart.CreateCartDto
import com.helloworld.order.domain.dto.CartDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.RequestBody

interface CartControllerSwaggerInterface {
    @Parameters(
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Authenticated",
            required = true,
            content = [Content(schema = Schema(type = "integer"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Authorization",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Accept-Language",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Channel-Type",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(`in` = ParameterIn.HEADER, name = "Os-Code", required = true, content = [Content(schema = Schema(type = "string"))]),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Os-Version",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(`in` = ParameterIn.HEADER, name = "Device-Id", required = true, content = [Content(schema = Schema(type = "string"))]),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "Accept-Language",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
        Parameter(
            `in` = ParameterIn.HEADER,
            name = "App-Version",
            required = true,
            content = [Content(schema = Schema(type = "string"))]
        ),
    )
    fun create(
        @Parameter(hidden = true) @HelloworldUser user: User,
        @RequestBody createCartDto: CreateCartDto,
    ): CartDto
}
