package com.helloworld.cart.controller.swagger

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.config.HelloworldUser
import com.helloworld.data.cart.CartDto
import com.helloworld.domain.common.data.User
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.web.bind.annotation.PostMapping
import springfox.documentation.annotations.ApiIgnore

interface CartControllerSwaggerInterface {
    @ApiImplicitParams(
        ApiImplicitParam(
            name = "Authenticated",
            dataType = "Long",
            required = true,
            paramType = "header",
            readOnly = true,
            value = "accountId (Swagger Test용)"
        ),
        ApiImplicitParam(
            name = "Authorization",
            required = true,
            dataType = "string",
            dataTypeClass = String::class,
            paramType = "header",
            defaultValue = "bearer order-_-7584e07d-9d2a-11e9-a0ad-0a1c53f94230",
            value = "클라이언트 Bearer 토큰"
        ),
        ApiImplicitParam(
            name = "Accept-Language",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "ja-JP",
            value = "ja-JP, ko-KR, en-US"
        ),
        ApiImplicitParam(
            name = "Channel-Type",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "FOODNEKO_IOS",
            value = "Available : FOODNEKO_IOS, FOODNEKO_AND, FOODNEKO_WEB"
        ),
        ApiImplicitParam(
            name = "Os-Code",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "ios",
            value = "Available : ios, android, web"
        ),
        ApiImplicitParam(
            name = "Os-Version",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "1.0.0",
            value = "OS Version"
        ),
        ApiImplicitParam(
            name = "Device-Id",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "testdeviceId",
            value = "디바이스 고유 ID"
        ),
        ApiImplicitParam(
            name = "App-Version",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "1.0.0",
            value = "앱버전"
        ),
        ApiImplicitParam(
            name = "deviceId",
            dataType = "string",
            dataTypeClass = String::class,
            paramType = "path",
            defaultValue = "testdeviceId",
            value = "전달되지 않는 경우 Header값을 사용, 디바이스 고유 ID"
        ),
        ApiImplicitParam(
            name = "osCode",
            dataType = "string",
            dataTypeClass = String::class,
            paramType = "body",
            defaultValue = "ios",
            value = "전달되지 않는 경우 Header값을 사용, Available : ios, android, web"
        )
    )
    @PostMapping
    fun create(
        @ApiIgnore @HelloworldUser user: User,
        createCartDto: CreateCartDto,
    ): CartDto
}