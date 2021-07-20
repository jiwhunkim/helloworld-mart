package com.helloworld.display.controller

import com.helloworld.web.data.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "DisplayLayoutlaControllerV1")
@RequestMapping(
    value = ["/display"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class LayoutController {
    @GetMapping("/mall/{mallId}/pages/{id}/layouts")
    fun index(@PathVariable mallId: Long, @PathVariable id: String): Response<String> {
        return Response("String")
    }
}