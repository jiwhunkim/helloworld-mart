package com.helloworld.display.controller

import com.helloworld.display.data.dto.display.condition.DisplayPageSearchCondition
import com.helloworld.display.domain.dto.page.DisplayPageDto
import com.helloworld.display.service.DisplayPageApplicationService
import com.helloworld.web.data.Response
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "DisplayPageControllerV1")
@RequestMapping(
    value = ["/display"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class PageController(
    private val displayPageApplicationService: DisplayPageApplicationService
) {
    @GetMapping("/mall/{mallId}/pages")
    fun index(
        @PathVariable mallId: Long,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC) @ParameterObject pageable: Pageable,
        @ParameterObject condition: DisplayPageSearchCondition
    ): Response<Page<DisplayPageDto>> {
        return Response(displayPageApplicationService.findAll(mallId, condition, pageable))
    }
}
