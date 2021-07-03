package com.helloworld.display.controller

import com.helloworld.display.data.dto.response.DisplayProductResponseDto
import com.helloworld.display.service.ProductApplicationService
import com.helloworld.web.data.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "DisplayProductControllerV1")
@RequestMapping(
    value = ["/display"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class ProductController(
    val productApplicationService: ProductApplicationService
) {
    @GetMapping("/mall/{mallId}/products/{id}")
    fun get(@PathVariable mallId: Long, @PathVariable id: Long): Response<DisplayProductResponseDto> {
        return Response(productApplicationService.detail(id))
    }
}
