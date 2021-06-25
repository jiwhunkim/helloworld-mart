package com.helloworld.display.controller

import com.helloworld.display.data.DisplayDto
import com.helloworld.display.data.Response
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "DisplayControllerV1")
@RequestMapping(
    value = ["/display"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class DisplayController {
    @GetMapping("/mall/{mallId}/index")
    fun index(@PathVariable mallId: Long): Response<DisplayDto> {
        return Response(DisplayDto(id = mallId))
    }
}